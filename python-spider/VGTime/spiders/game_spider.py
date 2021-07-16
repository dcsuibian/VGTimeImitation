import scrapy
import re

from VGTime.items import Game, Album


class GameSpider(scrapy.Spider):
    name = 'games'
    allowed_domains = ['vgtime.com']
    start_urls = []
    custom_settings = {'DOWNLOAD_DELAY': 0.2, 'CONCURRENT_REQUESTS_PER_IP': 4, }

    def start_requests(self):
        for i in range(1, 10):
            yield scrapy.Request(
                'https://www.vgtime.com/game/search/load.jhtml?total=1165&page=' + str(i) + '&pageSize=5',
                callback=self.parse_search_result)

    def parse_search_result(self, response):
        hrefs = response.xpath('//li/div/h2/a/@href').getall()
        urls = map(lambda href: response.urljoin(href), hrefs)
        yield from map(lambda url: scrapy.Request(url, callback=self.parse),
                       urls)

    def parse(self, response):
        game=Game()
        game['id']=int(response.css('input#open_in_app_id').attrib['value'])
        game['name']=response.xpath('/html/body/section/div[1]/p/text()').get(default=response.xpath('/html/body/section/div[1]/h2/a/text()').get())
        game['genes']=response.css('.game_gene span::text').getall()
        game['tags']=response.css('.game_gene span[data-tid] a::text').getall()
        game['developer']=response.xpath('//div[@class="descri_box"]/p[contains(text(),"开发商")]/..').css("div :last-child::text").get()
        game['publisher']=response.xpath('//div[@class="descri_box"]/p[contains(text(),"发行商")]/..').css("div :last-child::text").get()
        game['introduction']=response.xpath('//h2[contains(text(),"关于游戏")]/../..').css('p.game_description').get()
        game['editor_comment']=response.xpath('//h2[contains(text(),"编辑的话")]/../..').css('p.game_description').get()
        game['rate']=float(response.css('.game_score::text').get())
        game['albums']=[]
        ablist_url = response.urljoin(response.xpath('//a[contains(text(),"游戏相册")]').attrib['href'])
        yield scrapy.Request(ablist_url,dont_filter=True,callback=self.parse_ablist,cb_kwargs={'game': game},)

    def parse_ablist(self,response,game):
        tmp={
            'albumsSize':len(response.css('.album_item .album_name a')),
            'albumsProcessed':0,
        }
        for item in response.css('.album_item .album_name a'):
            url=response.urljoin(item.attrib['href'])
            album=Album()
            album['id']=int(re.match(r'.*/album/(\d+)\.jhtml', url)[1])
            game['albums'].append(album)
            yield scrapy.Request(url,
                                 dont_filter=True,
                                 callback=self.parse_album,
                                 cb_kwargs={'album': album,
                                            'game':game,
                                            'tmp':tmp},)
    def parse_album(self,response,album,game,tmp):
        album['title']=response.css('.vg_tit h2 a::text').get()
        pictures=album['pictures']=[]
        for img in response.css('ul.game_focus_list li img'):
            picture=re.match(r'(.*)\?',img.attrib['src'])[1]
            pictures.append(picture)
        tmp['albumsProcessed']+=1
        if tmp['albumsProcessed']==tmp['albumsSize']:
            return game