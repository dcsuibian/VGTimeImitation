# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy
from scrapy import Item, Field


class User(scrapy.Item):
    id = Field()
    name = Field()
    password = Field()
    gender = Field()
    avatar = Field()
    level = Field()
    title = Field()
    phone_number = Field()
    email = Field()


class Topic(scrapy.Item):
    id = Field()
    title = Field()
    abstract = Field()
    content = Field()
    editor = Field()
    author = Field()
    time = Field()
    cover = Field()

class Game(scrapy.Item):
    id=Field()
    name=Field()
    genes=Field()
    tags=Field()
    developer=Field()
    publisher=Field()
    introduction=Field()
    editor_comment=Field()
    rate=Field()
    albums=Field()

class Album(scrapy.Item):
    id=Field()
    title=Field()
    pictures=Field()

if __name__ == '__main__':
    print(dir(User()))
