import scrapy
import time
import json
import re
from VGTime.items import User, Topic, Album, Game


class AllSpider(scrapy.Spider):
    name = 'all'
    allowed_domains = ['vgtime.com']
    custom_settings = {'DOWNLOAD_DELAY': 0.2, 'CONCURRENT_REQUESTS_PER_IP': 4, }

    def __init__(self, *args, **kwargs):
        super(AllSpider, self).__init__(*args, **kwargs)
        self.__count=0
        self.__maximum=100

    def start_requests(self):
        yield scrapy.Request('https://www.vgtime.com/',
                             callback=self.parse_web_page)

    def parse_web_page(self, response, **kwargs):
        for a in response.css('a[href]'):
            href = a.attrib['href']
            url = response.urljoin(href)
            types = ['topic', 'game', 'album']
            for type in types:
                pattern = r'.*vgtime\.com/' + type + r'/(\d+)\.jhtml.*'
                result = re.match(pattern, url)
                if not result:
                    continue
                id = int(result[1])
                cb_kwargs = {
                    'source': self.parse_web_page,
                    'type': type,
                    'id': id,
                }
                if 'topic' == type:
                    callback = self.parse_topic
                elif 'game' == type:
                    callback = self.parse_game
                elif 'album' == type:
                    callback = self.parse_album
                if self.__count>=self.__maximum:
                    return None
                yield scrapy.Request(
                    url,
                    callback=callback,
                    dont_filter=True,
                    cb_kwargs=cb_kwargs
                )
                self.__count+=1
                self.logger.info('parse_web_page的请求数为：{}'.format(self.__count))


    def parse_topic(self, response, **kwargs):
        yield from self.parse_web_page(response)
        self.logger.info('解析topic:' + response.url)

        topic = Topic()
        topic['id'] = int(response.xpath('//input[@id="topicId"]/@value').get())
        topic['title'] = response.xpath('//h1[@class="art_tit"]/text()').get()
        topic['abstract'] = response.xpath(
            '//div[@class="abstract"]/p/text()').get()
        topic['content'] = response.css('.topicContent').get()
        topic['cover'] = response.xpath(
            '//input[@id="wxshare_imageurl"]/@value').get()

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

        # 填充作者和编辑信息，填充编辑的部分在parse_user回调函数里
        yield scrapy.FormRequest(
            'https://www.vgtime.com/other/user.jhtml',
            formdata={'username': author['name']},
            callback=self.parse_user,
            dont_filter=True,
            cb_kwargs={
                'source': self.parse_topic,
                'topic': topic,
                'type': 'author',
                'topicResponse': response,
                'chain': kwargs,
            },
        )

        # 爬取评论，不过目前先不管评论内容，其实要的只是评论里的用户信息而已
        page = 1
        page_size = 10
        yield scrapy.Request(
            'https://www.vgtime.com/comments/list.jhtml?page={}&pageSize={}&objectId={}&level=1&type=1'.format(
                page, page_size, id),
            callback=self.parse_topic_comment,
            dont_filter=True,
            cb_kwargs={
                'source': self.parse_topic,
                'topic': topic,
                'topicResponse': response,
                'page': page,
                'pageSize': page_size,
                'objectId': topic['id'],
            }
        )

    def parse_game(self, response, **kwargs):
        yield from self.parse_web_page(response)
        self.logger.info('解析game:' + response.url)
        game = Game()
        game['id'] = int(response.css('input#open_in_app_id').attrib['value'])
        game['name'] = response.xpath('/html/body/section/div[1]/p/text()').get(
            default=response.xpath(
                '/html/body/section/div[1]/h2/a/text()').get())
        game['genes'] = response.css('.game_gene span::text').getall()
        game['tags'] = response.css(
            '.game_gene span[data-tid] a::text').getall()
        game['developer'] = response.xpath(
            '//div[@class="descri_box"]/p[contains(text(),"开发商")]/..').css(
            "div :last-child::text").get()
        game['publisher'] = response.xpath(
            '//div[@class="descri_box"]/p[contains(text(),"发行商")]/..').css(
            "div :last-child::text").get()
        game['introduction'] = response.xpath(
            '//h2[contains(text(),"关于游戏")]/../..').css(
            'p.game_description').get()
        game['editor_comment'] = response.xpath(
            '//h2[contains(text(),"编辑的话")]/../..').css(
            'p.game_description').get()
        game['rate'] = response.css('.game_score::text').get()
        game['rate']=float(game['rate']) if game['rate'] else None
        game['albums'] = []
        ablist_url = response.urljoin(
            response.xpath('//a[contains(text(),"游戏相册")]').attrib['href'])

        cb_kwargs = {
            'source': self.parse_game,
            'game': game,
            'chain': kwargs,
        }

        yield scrapy.Request(
            ablist_url,
            dont_filter=True,
            callback=self.parse_ablist,
            cb_kwargs=cb_kwargs,
        )

    def parse_ablist(self, response, **kwargs):
        game = kwargs['game']
        cb_kwargs = {
            'source': self.parse_ablist,
            'chain': kwargs,
            'albumsSize': len(response.css('.album_item .album_name a')),
            'albumsProcessed': 0,
        }
        for item in response.css('.album_item .album_name a'):
            url = response.urljoin(item.attrib['href'])
            album = Album()
            album['id'] = int(re.match(r'.*/album/(\d+)\.jhtml', url)[1])
            game['albums'].append(album)

            cb_kwargs_copy = cb_kwargs.copy()
            cb_kwargs_copy['album']=album
            yield scrapy.Request(url,
                                 dont_filter=True,
                                 callback=self.parse_album,
                                 cb_kwargs=cb_kwargs_copy, )

    def parse_album(self, response, **kwargs):
        if self.parse_ablist!=kwargs.get('source'):
            raise Exception('暂不支持这种调用parse_album的方法')
        album = kwargs['album']
        game = kwargs['chain']['game']
        album['title'] = response.css('.vg_tit h2 a::text').get()
        pictures = album['pictures'] = []
        for img in response.css('ul.game_focus_list li img'):
            picture = re.match(r'(.*)\?', img.attrib['src'])[1]
            pictures.append(picture)
        kwargs['albumsProcessed'] += 1
        if kwargs['albumsProcessed'] == kwargs['albumsSize']:
            self.logger.info('游戏{}的所有相册解析完成'.format(game['id']))
            yield game

    def parse_topic_comment(self, response, **kwargs):
        self.logger.info('解析topic的评论:'+response.url)
        page = kwargs['page']
        page_size = kwargs['pageSize']
        topic_id = kwargs['objectId']
        cb_kwargs = {
            'source': self.parse_topic_comment,
        }
        json_response = json.loads(response.text)
        comments = json_response['data']
        for comment in comments:
            yield scrapy.FormRequest(
                'https://www.vgtime.com/other/user.jhtml',
                formdata={'u': str(comment['userId'])},
                callback=self.parse_user,
                dont_filter=True,
                cb_kwargs=cb_kwargs,
            )
        if page_size == len(comments):
            cb_kwargs['page'] = page + 1
            cb_kwargs['pageSize'] = page_size
            cb_kwargs['objectId'] = topic_id
            yield scrapy.Request(
                'https://www.vgtime.com/comments/list.jhtml?page={}&pageSize={}&objectId={}&level=1&type=1'.format(
                    page, page_size, id),
                callback=self.parse_topic_comment,
                dont_filter=True,
                cb_kwargs=cb_kwargs
            )

    def parse_user(self, response, **kwargs):
        json_response = json.loads(response.text)
        user = User()
        user_info = json_response['data']['user_info']
        user['id'] = user_info['id']
        user['name'] = user_info['user_name']
        user['avatar'] = user_info['avatar_url']
        user['level'] = user_info['level']
        user['title'] = user_info['user_title']
        self.logger.info('解析user完成，其id为:' + str(user['id']))
        source = kwargs.get('source')  # 用get主要是担心根本就没有的情况，下面的就假设一定有
        if self.parse_topic == source:
            topic = kwargs['topic']
            type = kwargs['type']
            topic[type] = user
            if 'author' == type:
                yield scrapy.FormRequest(
                    'https://www.vgtime.com/other/user.jhtml',
                    formdata={'username': topic['editor']['name']},
                    callback=self.parse_user,
                    dont_filter=True,
                    cb_kwargs={
                        'source': self.parse_topic, # 可以看到，这里为了方便，没有真的将source设为parse_user
                        'topic': topic,
                        'type': 'editor',
                        'topicResponse': response,
                    },
                )
            elif 'editor' == type:  # 目前来说，假设一个Topic先解析作者、再解析编辑、然后就结束了
                self.logger.info('解析topic完成，其id为:' + str(topic['id']))
                yield topic
            else:
                raise Exception('未曾设想的topic与用户的关联')
        else:
            yield user
