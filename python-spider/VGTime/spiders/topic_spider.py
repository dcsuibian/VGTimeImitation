import scrapy
import re
from ..items import Topic,User
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
        hrefs=response.xpath('//a/@href').getall()
        for href in hrefs:
            if re.match(r'.*/topic/\d+\.jhtml', href):
                url=response.urljoin(href)
                self.logger.debug('一个topic：{0}'.format(url))
                yield scrapy.Request(url, callback=self.parse)

    def parse(self, response):
        topic=Topic()
        topic['id']= int(response.xpath('//input[@id="topicId"]/@value').get())
        topic['title'] = response.xpath('//h1[@class="art_tit"]/text()').get()
        topic['abstract'] = response.xpath('//div[@class="abstract"]/p/text()').get()
        topic['content']=response.css('.topicContent').get()
        time_string=response.css('div.editor_name span.time_box::text').get().strip()
        self.logger.debug('日期字符串："'+str(time_string)+'"')
        time_array =time.strptime(time_string, '%Y-%m-%d %H:%M:%S')
        topic['time']=int(time.mktime(time_array))*1000
