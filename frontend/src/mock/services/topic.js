import Mock from "mockjs";
import { builder } from "../util";
import topics from '@/../../python-spider/out/topics.json'

export function findTopicById(id){
  let res=topics.find(item=>item.id===id)
  return res?res:null
}

Mock.mock(/\/topics\/(\d+)/, "get", (options) => {
  console.debug("接收到topic访问请求，options：", options);
  const id=Number(options.url.match(/\/topics\/(\d+)/)[1])
  console.debug('查询topic的ID:',id)
  return builder(findTopicById(id), "topic数据", 200);
});
Mock.mock(/\/topics$/,'get',(options)=>{
  return builder(topics,'给你所有topic',200)
})