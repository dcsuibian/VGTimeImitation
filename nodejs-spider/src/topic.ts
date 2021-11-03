import * as cheerio from 'cheerio';
import { pool } from './repository';
import { getUser } from './user';
import { request } from './util';

interface Topic {
  id: number;
  title: string;
  abstract: string;
  author: {
    id: number;
    name: string;
  };
  editor: {
    id: number;
    name: string;
  };
  time: number;
  content: string;
  cover: string;
}
async function getTopicFromDB(id: number): Promise<Topic | null> {
  const connection = await pool.getConnection();
  try {
    const [rows, fields] = await connection.execute(
      'SELECT * FROM `topic` WHERE `id` = ?',
      [id]
    );
    if ((rows as any).length > 0) {
      let item = (rows as any)[0];
      return {
        id: item.id,
        title: item.title,
        abstract: item.abstract,
        time: item.time,
        content: item.content,
        cover: item.cover,
        author: (await getUser(item.author_id))!,
        editor: (await getUser(item.editor_id))!,
      };
    } else {
      return null;
    }
  } finally {
    connection.release();
  }
}

async function scrapeTopic(id: number): Promise<Topic | null> {
  const res = await request({
    method: 'GET',
    url: `/topic/${id}.jhtml`,
  });
  if (res.status !== 200) {
    return null;
  }
  const $ = cheerio.load(res.data as any);
  let result = {
    id,
    title: $('article h1.art_tit').text(),
    abstract: $('div.abstract').text().trim(),
    author: {
      id: -1,
      name: $('div.editor_name>span:first-child').text(),
    },
    editor: {
      id: -1,
      name: $('div.editor_name>span:nth-child(2)').text(),
    },
    time: new Date($('div.editor_name .time_box').text()).getTime(),
    content: $('div.topicContent.front_content').html()!,
    cover: $('#wxshare_imageurl').val() as string,
  };
  result.author = (await getUser(result.author.name))!;
  result.editor = (await getUser(result.editor.name))!;
  console.log(
    `scrapeTopic(${id})的结果:`,
    JSON.stringify(result, (key, value) =>
      'content' === key ? undefined : value
    )
  );

  const connection = await pool.getConnection();
  await connection.execute(
    'INSERT INTO `topic`(id,title,abstract,author_id,editor_id,time,content,cover) VALUES(?,?,?,?,?,?,?,?)',
    [
      result.id,
      result.title,
      result.abstract,
      result.author?.id ?? null,
      result.editor?.id ?? null,
      result.time,
      result.content,
      result.cover,
    ]
  );
  connection.release();

  return result;
}

async function getTopic(id: number): Promise<Topic | null> {
  let topic = await getTopicFromDB(id);
  if (topic) {
    console.log(`数据库中有id为${id}的Topic`);
  } else {
    topic = await scrapeTopic(id);

    console.log(
      `getTopic(${id})的结果:`,
      JSON.stringify(topic, (key, value) =>
        'content' === key ? undefined : value
      )
    );
  }
  return topic;
}

export { getTopic };
