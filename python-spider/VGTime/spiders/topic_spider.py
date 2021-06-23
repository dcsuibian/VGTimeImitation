import scrapy
import re

class TopicSpider(scrapy.Spider):
    name = 'topics'
    allowed_domains = ['vgtime.com']
    start_urls = []
    custom_settings = {'DOWNLOAD_DELAY': 0.2, 'CONCURRENT_REQUESTS_PER_IP': 4, }

    def start_requests(self):
        yield scrapy.Request('https://www.vgtime.com/sitemap.txt',
                              callback=self.parse_sitemap)

    def parse_sitemap(self, response):
        lines=bytes.decode(response.body,'UTF-8').splitlines()
        for line in lines:
            if re.match(r'.*/topic/.*',line):
                yield scrapy.Request(line,callback=self.parse)

    def parse(self, response):
        id=response.xpath('//input[@id="topicId"]/@value').get()
        title=response.xpath('//h1[@class="art_tit"]/text()').get()
        abstract = response.xpath('//div[@class="abstract"]/p/text()').get()

