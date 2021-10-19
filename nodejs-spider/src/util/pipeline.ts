import mysql from 'mysql';
import Chance from 'chance';
import { UserVO } from '../models/user';
const chance=Chance()
type PipeLineItem = {
  type: 'UserVO';
  payload: UserVO;
};
export class PipeLine {
  constructor(private connection: mysql.Connection) {}
  push(item: PipeLineItem) {
    switch (item.type) {
      case 'UserVO':
        {
          const user = item.payload;
          this.connection.query(
            `INSERT INTO \`user\`(id,name,avatar,level,title,gender,phone_number,email) VALUES (${user.id},'${user.name}','${user.avatar}',${user.level},'${user.title}','${chance.bool()?'男':'女'}','${chance.phone()}','${chance.email({domain:'gmail.com'})}')`,
            function (error, results, fields) {
              console.debug('插入%d条数据',results.affectedRows)
            }
          );
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
