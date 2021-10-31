import React from 'react';
import styles from './index.less';
import moment from 'moment';

const NormalSection: React.FC<{
  title: string;
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
}> = ({ title, topics }) => {
  console.log('title:',topics)
  return (
    <div className={styles.normalSection}>
      <h2>{title}</h2>
      <ul>
        {topics.map((topic) => {
          return (
            <li key={topic.id}>
              <div className={styles.imgBox}>
                <a href={'/topics/' + topic.id}>
                  <img src={topic.cover} alt="加载失败" />
                </a>
              </div>
              <div className={styles.infoBox}>
                <h2>{topic.title}</h2>
                <p>{topic.abstract}</p>
                <div className={styles.fotBox}>
                  <span className={'left'}>{topic.author.name}</span>
                  <span className={'right'}>
                    {moment(topic.time).format('YYYY-MM-DD')}
                  </span>
                </div>
              </div>
            </li>
          );
        })}
      </ul>
    </div>
  );
};
export default NormalSection;
