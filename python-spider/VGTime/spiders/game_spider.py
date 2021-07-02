import scrapy


class GameSpider(scrapy.Spider):
    name = 'game_spider'
    allowed_domains = ['vgtime.com']
    start_urls = []

    def start_requests(self):
        for i in range(5000):
            yield scrapy.Request('https://www.vgtime.com/game/%d.jhtml',
                                 callback=self.parse)
    def parse(self, response):
        pass