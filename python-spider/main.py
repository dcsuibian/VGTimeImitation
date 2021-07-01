# 无用，只是方便设立IDE调试配置的罢了
from scrapy import cmdline

if __name__ == '__main__':
    cmdline.execute('scrapy crawl topics -O out/topics.json -a source=index'.split())
