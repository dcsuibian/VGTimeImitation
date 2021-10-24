import React from 'react';
import styles from './index.less';
const Header: React.FC = () => {
  const fakeTitle = '只是照抄了原网页罢了，不要点';
  const navItems = [
    '动漫',
    '游戏',
    '攻略评测',
    '新闻资讯',
    '专题专栏',
    '视频节目',
    '俱乐部',
    'UCG商城',
  ];
  const activeItemIndex = 0;
  return (
    <header className={styles.header}>
      <div className={styles.message}>
        <span>
          <a href={''} title={fakeTitle}>
            UCG商城
          </a>
          <a href={''} title={fakeTitle}>
            网站功能反馈QQ群566282315
          </a>
        </span>
        <span className={'right'}>{/* TODO 知乎、微博等图标  */}</span>
      </div>
      <div className={styles.navAndSearch}>
        <nav>
          <ul className={styles.list}>
            {navItems.map((item, index) => {
              return (
                <li className="left">
                  <a
                    href={''}
                    title={fakeTitle}
                    className={
                      activeItemIndex === index ? styles.active : undefined
                    }
                  >
                    {item}
                  </a>
                </li>
              );
            })}
          </ul>
        </nav>
      </div>
    </header>
  );
};
export default Header;
