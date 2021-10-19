import mysql from 'mysql';
import { getUserVOById } from './user';
import { PipeLine, request } from './util';

const connection = mysql.createConnection({
  host: 'localhost',
  user: 'dcsuibian',
  password: 'password',
  database: 'vgtime_imitation',
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
        resolve(null)
      }
    );
  });

  for (let i = maxUserId + 1; i < 10000; i++) {
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

main();
