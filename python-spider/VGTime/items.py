# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy
from scrapy import Item, Field


class Topic(scrapy.Item):
    id = Field()
    title = Field()
    abstract = Field()
    content = Field()
    editor_name = Field()
    author_name = Field()
    time = Field()
