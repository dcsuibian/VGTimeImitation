# Don't forget to add your pipeline to the ITEM_PIPELINES setting

# useful for handling different item types with a single interface
from itemadapter import ItemAdapter

import pymysql
from repository import UserRepository,TopicRepository
import logging

class MySQLPipeline(object):
    def __init__(self):
        self.logger=logging.getLogger(__name__)
        # 建立连接
        self.conn = pymysql.connect(
            host='localhost',
            user='root',
            password='password',
            database='vgtime_imitation',
            charset='utf8mb4',
            autocommit=True,
        )
        # 创建游标
        self.cursor = self.conn.cursor()
        self.user_repository=UserRepository(self.cursor)
        self.topic_repository=TopicRepository(self.cursor)

    def process_item(self, item, spider):
        for user in (item['author'], item['editor']):
            try:
                self.user_repository.save(user)
                self.logger.info('插入user {0}成功'.format(user['id']))
            except Exception as err:  # 应该是用户已存在
                self.logger.debug('插入user时出现异常：', err)
        try:
            topic = item
            self.topic_repository.save(topic)
            self.logger.info('插入topic {0}成功'.format(topic['id']))
        except Exception as err:  # 应该是topic已存在
            self.logger.debug('插入topic时出现异常：', err)
        return item

    def close_spider(self, spider):
        # 关闭游标和连接
        self.cursor.close()
        self.conn.close()
