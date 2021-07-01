import Mock from "mockjs";
import { builder } from "../util";
import topics from '@/../../python-spider/out/topics.json'

export function findTopicById(id){
  let res=topics.find(item=>item.id===id)
  return res?res:null
}

Mock.mock(/\/homepage/, "get", (options) => {
  console.debug("接收到首页数据访问请求，options：", options);
  const res={}
  res.focus=[1127684,1127473,1127561,1127002,1125185]
  res.news=[]
  res.cheat=[1124746,1124745,1124744,1124680]
  res.review=[1127473,1127165,1127002,1126671]
  res.culture=[1127737,1127684,1127651,1127571]
  res.comic=[1065667,1065666,1065665,1065644]
  for(const key in res){
      res[key]=res[key].map(id=>{
          return findTopicById(id)
      })
  }
  return builder(res, "首页数据", 200);
})