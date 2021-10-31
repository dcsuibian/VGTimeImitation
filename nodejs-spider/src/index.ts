import mysql from 'mysql';
import { getTopicVOById } from './topic';
import { getUserVOById } from './user';
import { PipeLine } from './util';

const connection = mysql.createConnection({
  host: 'localhost',
  user: 'dcsuibian',
  password: 'password',
  database: 'vgtime_imitation',
  charset: 'utf8mb4',
});

const pipe = new PipeLine(connection);

async function main() {
  let promises: Promise<any>[] = [];
  let maxUserId = 0;
  await new Promise((resolve, reject) => {
    connection.query(
      `SELECT MAX(id) FROM \`user\` `,
      function (error, results) {
        maxUserId = results[0]['MAX(id)'] || 0;
        console.log('目前用户的最大id是：', maxUserId);
        resolve(null);
      }
    );
  });

  for (let i = maxUserId + 1; i < 500000; i++) {
    promises.push(
      getUserVOById(i).then(user => {
        console.log('用户%d：', i, JSON.stringify(user));
        if (null === user) {
          return;
        }
        pipe.push({
          type: 'UserVO',
          payload: user,
        });
      })
    );
  }
  await Promise.all(promises);
  pipe.close();
}
async function main2() {
  let promises: Promise<any>[] = [];
  let minTopicId = 0;
  await new Promise((resolve, reject) => {
    connection.query(
      `SELECT MIN(id) FROM \`topic\` `,
      function (error, results) {
        minTopicId = results[0]['MIN(id)'] || 1138376;
        console.log('目前Topic的最小id是：', minTopicId);
        resolve(null);
      }
    );
  });

  for (let i = minTopicId - 1; i >= 1; i--) {
    promises.push(
      getTopicVOById(i).then(topic => {
        console.log(
          'Topic%d：',
          i,
          JSON.stringify(topic, (key, value) => {
            if (key !== 'content') {
              return value;
            }
          })
        );
        if (null === topic) {
          return;
        }
        pipe.push({
          type: 'TopicVO',
          payload: topic,
        });
      })
    );
  }
  return await Promise.all(promises).then(res=>{
    pipe.close();
  })
}

function continueWork(work: () => Promise<void>) {
  work().catch(err => {
    console.log('出错了，错误信息：', err);
    console.log('重启');
    continueWork(work);
  });
}

// continueWork(main)
continueWork(main2);
