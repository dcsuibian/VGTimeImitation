import json

import scrapy
import re
from ..items import Topic, User
import datetime
import time


class TopicSpider(scrapy.Spider):
    name = 'topics'
    allowed_domains = ['vgtime.com']
    start_urls = []
    custom_settings = {'DOWNLOAD_DELAY': 0.2, 'CONCURRENT_REQUESTS_PER_IP': 4, }

    def start_requests(self):
        yield scrapy.Request('https://www.vgtime.com/',
                             callback=self.parse_home_page)

    def parse_home_page(self, response):
        hrefs = response.xpath('//a/@href').getall()
        for href in hrefs:
            if re.match(r'.*/topic/\d+\.jhtml', href):
                url = response.urljoin(href)
                self.logger.debug('一个topic：{0}'.format(url))
                yield scrapy.Request(url, callback=self.parse)

    def parse(self, response):
        count=0
        topic = Topic()
        topic['id'] = int(response.xpath('//input[@id="topicId"]/@value').get())
        topic['title'] = response.xpath('//h1[@class="art_tit"]/text()').get()
        topic['abstract'] = response.xpath(
            '//div[@class="abstract"]/p/text()').get()
        topic['content'] = response.css('.topicContent').get()
        time_string = response.css(
            'div.editor_name span.time_box::text').get().strip()
        self.logger.debug('日期字符串："' + str(time_string) + '"')
        time_array = time.strptime(time_string, '%Y-%m-%d %H:%M:%S')
        topic['time'] = int(time.mktime(time_array)) * 1000
        author = topic['author'] = User()
        editor = topic['editor'] = User()
        author['name'] = response.css(
            'div.editor_name span:first-child::text').get()
        editor['name'] = response.css(
            'div.editor_name span:nth-child(2)::text').get()
        count+=1
        self.logger.debug('yield a user topic {0}'%(count))
        yield scrapy.FormRequest(
            'https://www.vgtime.com/other/user.jhtml',
            formdata={'username': author['name']},
            callback=self.parse_author,
            cb_kwargs={'topic':topic},
        )
    def __attach_user(self,response):
        json_response = json.loads(response.text)
        user = User()
        user_info=json_response['data']['user_info']
        user['id'] = user_info['id']
        user['name'] = user_info['user_name']
        user['avatar'] = user_info['avatar_url']
        user['level'] = user_info['level']
        return user

    def parse_author(self, response, topic):
        self.logger.debug('parse_author')
        topic['author']=self.__attach_user(response)
        return scrapy.FormRequest(
            'https://www.vgtime.com/other/user.jhtml',
            formdata={'username': topic['editor']['name']},
            callback=self.parse_editor,
            cb_kwargs={'topic': topic},
        )

    def parse_editor(self, response, topic):
        self.logger.debug('parse_editor')
        topic['editor'] = self.__attach_user(response)
        return topic
