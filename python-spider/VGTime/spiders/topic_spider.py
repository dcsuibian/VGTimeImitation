import json
import scrapy
import re
from ..items import Topic, User
import time
from tqdm import tqdm


class TopicSpider(scrapy.Spider):
    name = 'topics'
    allowed_domains = ['vgtime.com']
    start_urls = []
    custom_settings = {'DOWNLOAD_DELAY': 0.2, 'CONCURRENT_REQUESTS_PER_IP': 4, }

    def __init__(self, source='rss', *args, **kwargs):
        super(TopicSpider, self).__init__(*args, **kwargs)
        # 目前从支持rss订阅、主页和sitemap三种来源寻找topic
        if 'rss' == source:
            pass
        elif 'index' == source:
            pass
        elif 'sitemap' == source:
            pass
        else:
            raise Exception('不支持的来源类型')
        self.logger.info('topic的来源：' + source)
        self.logger.info('topic的来源：' + source)
        self.source = source

    def start_requests(self):
        self.__cnt = 0
        self.tqdm = None
        self.user_dict = {}

        source = self.source
        if 'rss' == source:
            yield scrapy.Request('https://www.vgtime.com/rss.jhtml',
                                 callback=self.parse_rss)
        elif 'index' == source:
            # 请求首页上所有topic
            yield scrapy.Request('https://www.vgtime.com/',
                                 callback=self.parse_home_page)
        elif 'sitemap' == source:
            # 请求sitemap上所有topic
            yield scrapy.Request('http://www.vgtime.com/sitemap.txt',
                                 callback=self.parse_sitemap)

    # 过滤URL，保证无论是rss、主页还是sitemap都只会使用topic的链接，避免forum之类的混入
    def filter_urls(self, urls):
        if urls is None:
            return None
        return filter(lambda url: bool(re.match(r'.*/topic/\d+\.jhtml', url)),
                      urls)

    def parse_rss(self, response):
        # 使用针对RSS专有的库可能更好，但我偷懒了
        urls = self.filter_urls(response.xpath('//item/link/text()').getall())
        yield from map(lambda url: scrapy.Request(url, callback=self.parse),
                       urls)

    def parse_sitemap(self, response):
        urls = self.filter_urls(response.text.split())
        yield from map(lambda url: scrapy.Request(url, callback=self.parse),
                       urls)

    def parse_home_page(self, response):
        hrefs = response.xpath('//a/@href').getall()
        urls = map(lambda href: response.urljoin(href), hrefs)
        urls = self.filter_urls(urls)
        yield from map(lambda url: scrapy.Request(url, callback=self.parse),
                       urls)

    def parse(self, response):
        self.logger.info('解析topic:'+response.url)

        topic = Topic()
        topic['id'] = int(response.xpath('//input[@id="topicId"]/@value').get())
        topic['title'] = response.xpath('//h1[@class="art_tit"]/text()').get()
        topic['abstract'] = response.xpath('//div[@class="abstract"]/p/text()').get()
        topic['content'] = response.css('.topicContent').get()
        topic['cover']=response.xpath('//input[@id="wxshare_imageurl"]/@value').get()

        # 日期字符串转时间戳
        time_string = response.css(
            'div.editor_name span.time_box::text').get().strip()
        time_array = time.strptime(time_string, '%Y-%m-%d %H:%M:%S')
        topic['time'] = int(time.mktime(time_array)) * 1000


        author = topic['author'] = User()
        editor = topic['editor'] = User()
        author['name'] = response.css(
            'div.editor_name span:first-child::text').get()
        editor['name'] = response.css(
            'div.editor_name span:nth-child(2)::text').get()
        if (not author['name']) or not (editor['name']):
            self.logger.info('topic {0} 缺少作者或编辑，不采用'.format(topic['id']))
            return None
        if self.user_dict.get(author['name']):  # 如果作者存在
            topic['author'] = self.user_dict[author['name']]
            yield from self.parse_author(None, topic)
        else:
            # 要求补全作者和编辑（编辑的请求在回调函数里）
            yield scrapy.FormRequest(
                'https://www.vgtime.com/other/user.jhtml',
                formdata={'username': author['name']},
                callback=self.parse_author,
                dont_filter=True,
                cb_kwargs={'topic': topic},
            )

    # 工具方法
    # 从'https://www.vgtime.com/other/user.jhtml'返回的JSON数据中提取用户信息
    def __attach_user(self, response):
        json_response = json.loads(response.text)
        user = User()
        user_info = json_response['data']['user_info']
        user['id'] = user_info['id']
        user['name'] = user_info['user_name']
        user['avatar'] = user_info['avatar_url']
        user['level'] = user_info['level']
        # 记录此用户
        self.user_dict[user['name']] = user
        return user

    def parse_author(self, response, topic):
        if response:
            topic['author'] = self.__attach_user(response)

        editor = topic['editor']
        if self.user_dict.get(editor['name']):  # 如果编辑存在
            topic['editor'] = self.user_dict[editor['name']]
            yield from self.parse_editor(None, topic)
        else:
            # 要求补全编辑
            yield scrapy.FormRequest(
                'https://www.vgtime.com/other/user.jhtml',
                formdata={'username': topic['editor']['name']},
                callback=self.parse_editor,
                dont_filter=True,
                cb_kwargs={'topic': topic},
            )

    def parse_editor(self, response, topic):
        if response:
            topic['editor'] = self.__attach_user(response)
        yield topic
