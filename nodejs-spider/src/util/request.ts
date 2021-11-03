import axios from 'axios';
import PubSub from 'pubsub-js';

const REQUESTS_PER_SECOND = 2;
const ALLOW_NEW_REQUEST_TOPIC = 'allow new request' + new Date().getTime();
const MAX_IDLE_TIME = 3000;

console.log('初始化request模块中，当前时间为：', new Date());
console.log('每秒允许请求数：', REQUESTS_PER_SECOND);
console.log('最大空闲时间：%f秒', MAX_IDLE_TIME / 1000);
console.log();

let allowance = 1;
const queue: number[] = [];
let idleTime = 0;
// 每隔一段时间增加请求余量
const timer = setInterval(() => {
  // 保证当前余量不超过REQUESTS_PER_SECOND
  allowance = Math.min(REQUESTS_PER_SECOND, allowance + 1);
  if (queue.length === 0) {
    // 至少(1000 / REQUESTS_PER_SECOND)秒没新的请求了
    idleTime += 1000 / REQUESTS_PER_SECOND;
    if (idleTime >= MAX_IDLE_TIME) {
      clearInterval(timer);
      console.log('到达最大空闲时间，request计时器停止');
    } else {
      const leftTime = (MAX_IDLE_TIME - idleTime) / 1000;
      const closedInt =
        Math.abs(leftTime - parseInt(String(leftTime))) <
        Math.abs(leftTime - parseInt(String(leftTime + 1)))
          ? parseInt(String(leftTime))
          : parseInt(String(leftTime + 1));
      if (Math.abs(leftTime - closedInt) < 1e-6) {
        console.log('举例request销毁还有%d秒', closedInt);
      }
    }
    return;
  }
  idleTime = 0;
  const id = queue.shift();
  PubSub.publish(ALLOW_NEW_REQUEST_TOPIC, id);
}, 1000 / REQUESTS_PER_SECOND);

const inner = axios.create({
  baseURL: 'https://www.vgtime.com/',
});

let count: number = 1; // 相当于数据库的自增id
export const request = new Proxy(inner, {
  apply(target, thisArg, argArray) {
    // 如果还有请求余量，则直接发出请求
    if (allowance > 0) {
      allowance--;
      return Reflect.apply(target, thisArg, argArray);
    }
    // 无请求余量，则进入队列等候
    const id = count++;
    queue.push(id);
    return new Promise((resolve, reject) => {
      // 监听余量增加事件
      const token = PubSub.subscribe(ALLOW_NEW_REQUEST_TOPIC, (_, data) => {
        if (data !== id) {
          return;
        }
        // 轮到此请求，停止监听，发出请求
        PubSub.unsubscribe(token);
        resolve(Reflect.apply(target, thisArg, argArray));
      });
    });
  },
});
