import Header from '@/components/Header';
import React from 'react';
import styles from './index.less';
import Footer from '@/components/Footer';
const Layout: React.FC = ({ children }) => {
  return (
    <div className={styles.layout}>
      <div className={styles.main}>
        <Header />
        {children}
      </div>
      <Footer/>
    </div>
  );
};
export default Layout;
