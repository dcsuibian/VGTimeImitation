import scrapy


class TestSpider(scrapy.Spider):
    name = 'test'
    allowed_domains = ['vgtime.com']
    start_urls = ['http://vgtime.com/']

    def parse(self, response):
        print(response.body)
