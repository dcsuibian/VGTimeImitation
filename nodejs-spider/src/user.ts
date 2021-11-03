import FormData from 'form-data';
import { pool } from './repository';
import { request } from './util';
import Chance from 'chance';
let chance = Chance();
type User = {
  id: number;
  name: string;
  avatar: string;
  level: number;
  title: string;
};

async function getUserFromDB(id: number): Promise<User | null>;
async function getUserFromDB(name: string): Promise<User | null>;
async function getUserFromDB(idOrName: number | string): Promise<User | null>;
async function getUserFromDB(idOrName: number | string) {
  const connection = await pool.getConnection();
  try {
    const [rows, fields] = await connection.execute(
      'SELECT * FROM `user` WHERE `' +
        (typeof idOrName === 'number' ? 'id' : 'name') +
        '` = ?',
      [idOrName]
    );
    let result: User | null;
    if ((rows as any).length > 0) {
      let item = (rows as any)[0];
      result = item;
    } else {
      result = null;
    }
    return result;
  } finally {
    connection.release();
  }
}

async function scrapeUser(id: number): Promise<User | null>;
async function scrapeUser(name: string): Promise<User | null>;
async function scrapeUser(idOrName: number | string): Promise<User | null>;
async function scrapeUser(idOrName: number | string) {
  let id: number | undefined;
  let name: string | undefined;
  if (typeof idOrName === 'number') {
    id = idOrName;
  } else {
    name = idOrName;
  }
  const formData = new FormData();
  formData.append('u', id ? String(id) : '');
  formData.append('username', name || '');
  const res = await request({
    method: 'POST',
    url: '/other/user.jhtml',
    headers: {
      ...formData.getHeaders(),
    },
    data: formData,
  });
  // console.debug('res:',res)
  const { data, retcode }: { data: any; retcode: number } = res.data as any;
  if (200 != res.status) {
    throw new Error(res.statusText);
  }
  if (200 !== retcode || 0 === data.user_info.id) {
    return null;
  }
  type user_infoType = {
    id: number;
    user_name: string;
    avatar_url: string;
    cover_url: string;
    level: number;
    user_title: string;
    verify_type: number;
    verify_info: unknown;
    user_remark: unknown;
    level_type: number;
    medal_list: unknown[];
    _max_can_equip_count: number;
  };
  const user_info = data.user_info as user_infoType;
  const { avatar_url: avatar, level, user_title: title } = user_info;
  let user = {
    id: user_info.id,
    name: user_info.user_name,
    avatar,
    level,
    title,
    gender: chance.bool() ? '男' : '女',
    phoneNumber: chance.phone(),
    email: chance.email({ domain: 'gmail.com' }),
  };
  console.log(`scrapeUser(${idOrName})的结果:`, JSON.stringify(user));
  const connection = await pool.getConnection();
  await connection.execute(
    'INSERT INTO `user`(id,name,gender,avatar,level,title,phone_number,email) VALUES(?,?,?,?,?,?,?,?)',
    [
      user.id,
      user.name,
      user.gender,
      user.avatar,
      user.level,
      user.title,
      user.phoneNumber,
      user.email,
    ]
  );
  connection.release();
  return user;
}

async function getUser(id: number): Promise<User | null>;
async function getUser(name: string): Promise<User | null>;
async function getUser(idOrName: number | string) {
  let user = await getUserFromDB(idOrName);
  if (null === user) {
    console.log('数据库中无此用户，去网页接口上找');
    return await scrapeUser(idOrName);
  } else {
    console.log('数据库中有对应用户');
    return user;
  }
}

export { getUser };
