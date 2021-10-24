import Header from '@/components/Header';
import React from 'react';
import styles from './index.less';
const Layout: React.FC = ({ children }) => {
  return (
    <div className={styles.layout}>
      <div className={styles.main}>
        <Header />
        {children}
      </div>
    </div>
  );
};
export default Layout;
