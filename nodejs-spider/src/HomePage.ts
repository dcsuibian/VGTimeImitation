import * as cheerio from 'cheerio';
import { request } from './util';
request({
  url: 'https://www.vgtime.com/',
  method: 'GET',
}).then(res => {
  if (200 != res.status) {
    throw new Error('请求失败');
  }
  const $ = cheerio.load(res.data as any);
  const idSet: Set<number> = new Set();
  const selectorMap: Map<string, string> = new Map();
  selectorMap.set('新闻资讯', 'section.game_news_box');
  selectorMap.set('攻略资料', 'section.cheat_list_box');
  selectorMap.set('深度评测', 'section.review_list_box:nth-child(7)');
  selectorMap.set('游戏文化', 'section.review_list_box:nth-child(10)');
  selectorMap.set('动漫时光', 'section.review_list_box:nth-child(11)');
  for (const key of selectorMap.keys()) {
    const ids = $(`${selectorMap.get(key)} .img_box a`)
      .get()
      .filter((a: any) => a.attribs['href'].startsWith('/topic'))
      .map((a: any, index) => {
        const href = a.attribs['href'];
        const id = Number(href.slice(7, 14));
        idSet.add(id);
        return id;
      });
    console.log(`${key}:`, ids);
  }
  idSet.add(1139211)
  idSet.add(1138949)
  idSet.add(1138756)
  idSet.add(1138950)
  idSet.add(1138776)
  console.log(idSet);
});
