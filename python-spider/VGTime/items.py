# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy
from scrapy import Item, Field


class User(scrapy.Item):
    id = Field()
    name = Field()
    avatar = Field()
    level = Field()
    password = Field()


class Topic(scrapy.Item):
    id = Field()
    title = Field()
    abstract = Field()
    content = Field()
    editor = Field()
    author = Field()
    time = Field()


