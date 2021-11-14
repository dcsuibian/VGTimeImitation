import React from 'react';
import styles from './index.less';
import moment from 'moment';
import { history } from 'umi';
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
  return (
    <div className={styles.normalSection}>
      <h2>{title}</h2>
      <ul className={styles.big}>
        {topics.slice(0, 4).map((topic) => {
          return (
            <li key={topic.id}>
              <div className={styles.imgBox}>
                <a
                  href={'/topics/' + topic.id}
                  onClick={(event) => {
                    history.push('/topics/' + topic.id);
                    event.preventDefault();
                  }}
                >
                  <img src={topic.cover} alt="加载失败" />
                </a>
              </div>
              <div className={styles.infoBox}>
                <h2
                  onClick={(event) => {
                    history.push('/topics/' + topic.id);
                    event.preventDefault();
                  }}
                >
                  {topic.title}
                </h2>
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
      {topics.length <= 4 ? undefined : (
        <ul className={styles.small}>
          {topics.slice(4).map((topic) => (
            <li key={topic.id}>
              <div className={styles.imgBox}>
                <img src={topic.cover} alt="加载失败" />
              </div>
              <div className={styles.infoBox}>
                <span>新闻</span>
                <h2
                  onClick={(event) => {
                    history.push('/topics/' + topic.id);
                    event.preventDefault();
                  }}
                >
                  {topic.title}
                </h2>
                <div className={styles.fotBox}>
                  <span className="left">{topic.author.name}</span>
                  <span className="right">{moment(topic.time).fromNow()}</span>
                </div>
              </div>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};
export default NormalSection;
