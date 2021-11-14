import React, { useEffect, useState } from 'react';
import HotTopics from './components/HotTopics';
import styles from './index.less';
import NormalSection from './components/NormalSection';
import { getHomePage } from '@/services/api';
import moment from 'moment'
moment.locale('zh-CN')
type Topic = {
  id: number;
  abstract: string;
  time: number;
  title: string;
  cover: string;
  author: {
    name: string;
  };
};

const HomePage: React.FC = () => {
  const [homePage, setHomePage] = useState({
    hotNews: [],
    news: [],
    guides: [],
    reviews: [],
    cultures: [],
    cartoons: [],
  } as {
    hotNews: Topic[];
    news: Topic[];
    guides: Topic[];
    reviews: Topic[];
    cultures: Topic[];
    cartoons: Topic[];
  });
  useEffect(() => {
    getHomePage().then((res) => {
      if (200 != res.code) {
        throw new Error(res.message);
      }
      setHomePage(res.data!);
    });
  }, []);
  return (
    <div className={styles.homePage}>
      <div className={styles.focus}>
        <HotTopics topics={homePage.hotNews} />
      </div>
      <NormalSection title="新闻资讯" topics={homePage.news} />
      <NormalSection title="攻略资料" topics={homePage.guides} />
      <NormalSection title="深度评测" topics={homePage.reviews} />
      <NormalSection title="游戏文化" topics={homePage.cultures} />
      <NormalSection title="动漫时光" topics={homePage.cartoons} />
    </div>
  );
};
export default HomePage;
