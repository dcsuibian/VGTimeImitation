import mysql from 'mysql';
import Chance from 'chance';
import { UserVO } from '../models/user';
import { TopicVO } from '../models/topic';
import { getUserIdByName } from '../user';
const chance = Chance();
type PipeLineItem =
  | {
      type: 'UserVO';
      payload: UserVO;
    }
  | {
      type: 'TopicVO';
      payload: TopicVO;
    };
export class PipeLine {
  constructor(private connection: mysql.Connection) {}
  push(item: PipeLineItem) {
    switch (item.type) {
      case 'UserVO':
        {
          const user = item.payload;
          this.connection.query({
            sql: `INSERT INTO \`user\`(id,name,avatar,level,title,gender,phone_number,email) VALUES (?,?,?,?,?,?,?,?)`,
            values: [
              user.id,
              user.name,
              user.avatar,
              user.level,
              user.title,
              chance.bool() ? '男' : '女',
              chance.phone(),
              chance.email({ domain: 'gmail.com' }),
            ],
          });
        }
        break;
      case 'TopicVO':
        {
          const topic = item.payload;
          (async () => {
            const authorId = await new Promise(resolve => {
              this.connection.query(
                {
                  sql: `SELECT id FROM \`user\` WHERE \`name\`='?'`,
                  values: [topic.author?.name],
                },
                (_, results) => {
                  if ((!results) || (!results[0])||!(results[0].id)) {
                    resolve(getUserIdByName(topic.author!.name));
                  } else {
                    resolve(results[0].id);
                  }
                }
              );
            });
            const editorId = await new Promise(resolve => {
              this.connection.query(
                {
                  sql: `SELECT id FROM \`user\` WHERE \`name\`='?'`,
                  values: [topic.editor?.name],
                },
                (_, results) => {
                  if ((!results) || (!results[0])||!(results[0].id)) {
                    resolve(getUserIdByName(topic.author!.name));
                  } else {
                    resolve(results[0].id);
                  }
                }
              );
            });
            // INSERT INTO topic(id,title,abstract,author_id,editor_id,time,content,cover) VALUES (1,'','',2,3,4,'','')
            await new Promise(resolve=>{
              this.connection.query({
                sql: 'INSERT INTO topic(id,title,abstract,author_id,editor_id,time,content,cover) VALUES (?,?,?,?,?,?,?,?)',
                values: [
                  topic.id,
                  topic.title,
                  topic.abstract,
                  authorId,
                  editorId,
                  topic.time,
                  topic.content,
                  topic.cover,
                ],
              },(_,results)=>{

                resolve(null)
              });
            })
            
          })();
        }
        break;
      default:
        break;
    }
  }
  close() {
    this.connection.end();
  }
}
