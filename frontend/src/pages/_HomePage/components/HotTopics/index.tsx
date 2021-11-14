import React from 'react';
import styles from './index.less';
import { history } from 'umi';
import moment from 'moment';
const HotTopics: React.FC<{
  topics: {
    id: number;
    title: string;
    abstract: string;
    cover: string;
    author: {
      name: string;
    };
    time: number;
  }[];
}> = ({ topics }) => {
  return (
    <ul className={styles.hotTopics}>
      {topics.map((topic, index) => {
        return (
          <li key={topic.id}>
            <a
              href={'/topics/' + topic.id}
              onClick={(event) => {
                history.push('/topics/' + topic.id);
                event.preventDefault();
              }}
            >
              {
                // 给第一个加logo和“游戏时光”标题
                0 === index ? (
                  <div className={styles.shadow}>
                    <img
                      src="https://www.vgtime.com/resources/img/logo.svg"
                      alt="加载失败"
                    />
                    <img
                      src="https://www.vgtime.com/resources/img/logo_03.svg"
                      alt="加载失败"
                    />
                  </div>
                ) : undefined
              }
              <div className={styles.imgLayer}>
                <img src={topic.cover} alt="加载失败" />
              </div>
              <div className={styles.infoLayer}>
                <p title={topic.abstract}>{topic.abstract}</p>
                <h2 title={topic.title}>{topic.title}</h2>
                <div className={styles.fotBox}>
                  <span className={'left'}>{topic.author.name}</span>
                  <span className={'right'}>
                    {moment(topic.time).format('YYYY-MM-DD')}
                  </span>
                </div>
              </div>
            </a>
          </li>
        );
      })}
    </ul>
  );
};
export default HotTopics;
