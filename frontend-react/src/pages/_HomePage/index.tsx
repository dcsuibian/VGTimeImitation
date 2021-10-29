import React from 'react';
import HotTopics from './components/HotTopics';
import styles from './index.less';

const HomePage: React.FC = () => {
  const hotNews = [
    {
      id: 1138423,
      title: '《FIFA22》评测：是别无选择，还是天作之合',
      abstract: '如何评价年货体育游戏？这是个问题。',
      cover:
        'https://img01.vgtime.com/game/cover/2021/10/29/211029094813653_u54.jpg',
      author: {
        name: 'Gouki',
      },
      editor: {
        name: 'lv6',
      },
      time:1635464948000,
    },
  ];
  for (let i = 0; i < 4; i++) {
    hotNews.push({...hotNews[0]});
    hotNews[i+1].id = 1138423 + i + 1;
  }
  return (
    <div className={styles.homePage}>
      <div className={styles.focus}>
        <HotTopics topics={hotNews}/>
      </div>
      <h1>首页</h1>
    </div>
  );
};

export default HomePage;
