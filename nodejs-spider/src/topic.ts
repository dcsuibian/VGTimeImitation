import { TopicVO } from './models/topic';
import { request } from './util';
import * as cheerio from 'cheerio';

export function getTopicVOById(id: number): Promise<TopicVO | null> {
  return request({
    method: 'get',
    url: `/topic/${id}.jhtml`,
  }).then(res => {
    if (res.status !== 200) {
      return null;
    }
    const $ = cheerio.load(res.data as any);
    // 若为404页面，则返回null
    if (0 != $('img[src="/resources/img/404_01.svg"]').first().length) {
      return null;
    }
    let result: TopicVO = {
      id,
      title: $('article h1.art_tit').text(),
      abstract: $('div.abstract').text().trim(),
      author: {
        id: null,
        name: $('div.editor_name>span:first-child').text(),
      },
      editor: {
        id: null,
        name: $('div.editor_name>span:nth-child(2)').text(),
      },
      time: new Date($('div.editor_name .time_box').text()).getTime(),
      content: $('div.topicContent.front_content').html(),
      cover: $('#wxshare_imageurl').val() as string,
    };
    return result;
  });
}
