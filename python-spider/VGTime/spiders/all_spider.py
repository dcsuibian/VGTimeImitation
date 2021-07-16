import json

import scrapy
import re

from VGTime.items import User


class AllSpider(scrapy.Spider):
    name = 'all'
    allowed_domains = ['vgtime.com']
    custom_settings = {'DOWNLOAD_DELAY': 0.2, 'CONCURRENT_REQUESTS_PER_IP': 4, }

    def start_requests(self):
        yield scrapy.Request('https://www.vgtime.com/',
                             callback=self.parse_home_page)

    def parse_home_page(self, response):
        topics=[]
        for a in response.css('a[href]'):
            href=a.attrib['href']
            url=response.urljoin(href)
            types=['topic','game','album']
            for type in types:
                pattern=r'.*vgtime\.com/'+type+r'/(\d+)\.jhtml.*'
                result=re.match(pattern,url)
                if not result:
                    continue
                id=result[1]
                if 'topic'==type:
                    yield scrapy.Request(url,callback=self.parse_topic,dont_filter=True,cb_kwargs={'id':id})
                elif 'game'==type:
                    pass
                elif 'album'==type:
                    pass

    def parse_topic(self,response,id):
        page=1
        pageSize=10
        yield scrapy.Request(
            'https://www.vgtime.com/comments/list.jhtml?page={}&pageSize={}&objectId={}&level=1&type=1'.format(page,pageSize,id),
            callback=self.parse_topic_comment,
            dont_filter=True,
            cb_kwargs={
                'page':page,
                'pageSize':pageSize,
            }
        )

    def parse_topic_comment(self,response,page,pageSize):
        json_response = json.loads(response.text)
        comments=json_response['data']
        for comment in comments:
            yield scrapy.FormRequest(
                'https://www.vgtime.com/other/user.jhtml',
                formdata={'u': str(comment['userId'])},
                callback=self.parse_user,
                dont_filter=True,
            )
        if pageSize==len(comments):
            yield scrapy.Request(
                'https://www.vgtime.com/comments/list.jhtml?page={}&pageSize={}&objectId={}&level=1&type=1'.format(
                    page, pageSize, id),
                callback=self.parse_topic_comment,
                dont_filter=True,
                cb_kwargs={
                    'page': page,
                    'pageSize': pageSize,
                }
            )

    def parse_user(self,response):
        json_response = json.loads(response.text)
        user = User()
        user_info = json_response['data']['user_info']
        user['id'] = user_info['id']
        user['name'] = user_info['user_name']
        user['avatar'] = user_info['avatar_url']
        user['level'] = user_info['level']
        print(user)
        return user


    def parse(self, response):
        pass
