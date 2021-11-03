import { getTopicById } from '@/services/api';
import moment from 'moment';
import { useEffect, useState } from 'react';
import { useHistory, useLocation, useParams, useRouteMatch } from 'umi';
import styles from './index.less';

type Topic = {
  id: number;
  abstract: string;
  content: string;
  time: number;
  title: string;
  author: {
    name: string;
  };
};

export default () => {
  const [topic, setTopic]: [Topic, React.Dispatch<Topic>] = useState({
    id: 0,
    abstract: '',
    time: 0,
    title: '',
    content: '',
    author: {
      name: '',
    },
  });
  const params = useParams<{ id: string }>();
  const id: number = Number(params.id);
  useEffect(() => {
    getTopicById(id).then((res) => {
      if (res.code != 200) {
        throw new Error(res.message);
      }
      setTopic(res.data!);
    });
  });
  return (
    <div className={styles.center}>
      <article>
        <h1 className={styles.title}>{topic.title}</h1>
        <div className={styles.metaInfo}>
          <span className={styles.timeBox}>
            {moment(topic.time).format('YYYY-MM-DD HH:mm:ss')}
          </span>
        </div>
        <div className={styles.abstract}>
          <p>{topic.abstract}</p>
        </div>

        <div
          className={styles.content}
          dangerouslySetInnerHTML={{ __html: topic.content }}
        />
      </article>
    </div>
  );
};
