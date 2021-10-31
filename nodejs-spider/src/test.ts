import { AxiosResponse } from "axios";
import { request } from "./util";
import * as cheerio from 'cheerio';
request({
    url:'https://www.vgtime.com/',
    method:'GET',
}).then(res=>{
    const $=cheerio.load(res.data as any)
    const ids=$('section.game_news_box .img_box a').get().map((a,index)=>{
        const href=a.attribs['href']
        if(!href.startsWith('/topic')){
            console.log(`检测到第${index+1}个是俱乐部:`,href,'，跳过')
        }
        return Number(href.slice(7,14))
    })
    console.log(ids)
    
})