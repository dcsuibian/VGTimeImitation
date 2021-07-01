# TODO 删除这个文件，不在python里访问数据库

from VGTime.items import User, Topic
import pymysql


class UserRepository(object):
    def __init__(self, cursor):
        self.cursor = cursor

    def save(self, user):
        sql = "INSERT INTO `user`(`id`,`name`,`avatar`,`level`,`password`) VALUES(%s,%s,%s,%s,%s)"
        self.cursor.execute(
            sql,
            (user['id'], user['name'], user['avatar'], user['level'],
             None,)
        )
        return None

    def find_by_id(self, id):
        sql = """
        SELECT `id`,`name`,`avatar`,`level`,`password` FROM `user` WHERE `id`=%s
        """
        self.cursor.execute(sql, (id,))
        result = self.cursor.fetchone()
        return self.__result_to_user(result)

    def find_by_name(self, name):
        sql = """
        SELECT `id`,`name`,`avatar`,`level`,`password` FROM `user` WHERE `name`=%s
        """
        self.cursor.execute(sql, (name,))
        result = self.cursor.fetchone()
        return self.__result_to_user(result)

    def __result_to_user(self, result):
        if not result:
            return None
        user = User()
        array = ['id', 'name', 'avatar', 'level', 'password']
        for i in range(len(array)):
            user[array[i]] = result[i]
        return user


class TopicRepository(object):
    def __init__(self,cursor):
        self.cursor = cursor

    def find_by_id(self, id):
        sql = """
        SELECT `id`,`title`,`abstract`,`time`,`content`,`author_id`,`editor_id` FROM `topic` WHERE `id`=%s
        """
        self.cursor.execute(sql, (id,))
        result = self.cursor.fetchone()
        return self.__result_to_topic(result)

    def save(self, topic):
        sql = "INSERT INTO `topic`(`id`,`title`,`abstract`,`time`,`content`,`author_id`,`editor_id`,`cover`) VALUES(%s,%s,%s,%s,%s,%s,%s,%s)"
        self.cursor.execute(sql,
            (topic['id'],
             topic['title'],
             topic['abstract'],
             topic['time'],
             topic['content'],
             topic['author']['id'],
             topic['editor']['id'],
             topic['cover'],),
        )
        return None

    def __result_to_topic(self, result):
        if not result:
            return None
        topic = Topic()
        array = ['id', 'title', 'abstract', 'time', 'content']
        for i in range(len(array)):
            topic[array[i]] = result[i]
        topic['author']=User()
        topic['author']['id']=result['author_id']
        topic['editor']=User()
        topic['editor']['id'] = result['editor_id']
        return topic


if __name__ == '__main__':
    # 建立连接
    conn = pymysql.connect(
        host='localhost',
        user='root',
        password='password',
        database='vgtime_imitation',
        charset='utf8mb4',
        autocommit=True,
    )
    # 创建游标
    cursor = conn.cursor()
    user_repository = UserRepository(cursor)
    user = user_repository.find_by_id(2505)
    print(user)
