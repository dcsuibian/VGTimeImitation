// import { getAllTopics, getTopicById } from '@/services';
// import { useState } from 'react';
// import { useHistory, useLocation, useParams, useRouteMatch } from 'umi';
// import styles from './index.less';

// export default () => {
//   // const history=useHistory()
//   // const location=useLocation()
//   const [title,setTitle]=useState('')
//   const [abstract,setAbstract]=useState('')
//   const [__html, set__Html] = useState('');
//   const params: any = useParams();
//   const id: number = Number(params.id);
//   console.log(`topic's id is:`, id);
//   getTopicById(id).then((res) => {
//     if (res.code < 300) {
//       console.log('topic:', res.data);
//       set__Html(res.data.content);
//       setTitle(res.data.title)
//       setAbstract(res.data.abstract)
//     }
//   });
//   // // const routeMatch=useRouteMatch()
//   // // console.log('history:',history)
//   // // console.log('location:',location)
//   // // console.log('params:',params)
//   // // console.log('routeMatch:',routeMatch)
//   // // getAllTopics().then((res) => {
//   // //   console.log(res);
//   // // });
//   return (
//     <div className={styles.center}>
//       <article>
//         <h1 className={styles.title}>
//           {title}
//         </h1>
//         <div className={styles.metaInfo}>
//           <span className={styles.timeBox}>2021-06-28 15:43:08</span>
//         </div>
//         <div className={styles.abstract}>
//           <p>{abstract}</p>
//         </div>

//         <div className={styles.content} dangerouslySetInnerHTML={{ __html }} />
//       </article>
//     </div>
//   );
// };
