import * as cheerio from 'cheerio';
import { getTopic } from './topic';
import { request } from './util';
async function scrapeHomePage() {
  const res = await request({
    url: '',
    method: 'GET',
  });
  if (200 != res.status) {
    throw new Error('请求失败');
  }
  const $ = cheerio.load(res.data as any);
  const idSet: Set<number> = new Set();
  const selectorMap: Map<string, string> = new Map();
  selectorMap.set('热点新闻', 'section.foc_box');
  selectorMap.set('新闻资讯', 'section.game_news_box .vg_list:nth-child(2)');
  selectorMap.set('攻略资料', 'section.cheat_list_box');
  selectorMap.set('深度评测', 'section.review_list_box:nth-child(7)');
  selectorMap.set('游戏文化', 'section.review_list_box:nth-child(10)');
  selectorMap.set('动漫时光', 'section.review_list_box:nth-child(11)');
  for (const key of selectorMap.keys()) {
    const ids: number[] = [];
    $(`${selectorMap.get(key)} .img_box a`)
      .get()
      .filter((a: any) => a.attribs['href'].includes('/topic/'))
      .forEach((a: any, index) => {
        const href: string = a.attribs['href'];
        const id = Number(href.slice(href.lastIndexOf('/topic/')+7, href.lastIndexOf('.')));
        idSet.add(id);
        if (-1 === ids.indexOf(id)) {
          ids.push(id);
        }
      });
    console.log(`${key}:`, ids);
  }
  const preset = [
  1139211, 1138949, 1138756, 1138950, 1138776,
   1136155, 1136014, 1135950,1135946, 
   1138756, 1138950, 1138784, 1138766, 
   1138949, 1136112, 1138423,1137825, 
   1065667, 1065666, 1065665, 1065644,
  ];
  preset.forEach(id=>idSet.add(id))
  for (let id of idSet) {
    await getTopic(id);
  }
}

export { scrapeHomePage };
