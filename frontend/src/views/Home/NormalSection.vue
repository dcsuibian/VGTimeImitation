<template>
  <div class="wrapper">
    <div class="vg-tit">
      <h2>
        <a href :title="title">{{title}}</a>
      </h2>
      <div class="clear"></div>
    </div>
    <ul class="vg-list">
      <li v-for="topic in topics" :key="topic.id">
        <div class="img-box">
          <a target="_blank" href :title="topic.title">
            <img
              :src="topic.cover"
              :alt="topic.title"
            />
          </a>
        </div>
        <div class="info-box">
          <a target="_blank" href :title="topic.title"
            ><h2>{{topic.title}}</h2></a
          >
          <p>{{topic.abstract}}</p>
          <div class="fot-box">
            <div class="left">
              <span class="user_name">{{''}}</span>
            </div>
            <div class="right">
              <span class="time-box"><i class="icon"></i>{{timeStampToString(topic.time)}}</span>
            </div>
          </div>
        </div>
      </li>
      <li class="clear"></li>
    </ul>
  </div>
</template>

<script>
import moment from 'moment'
export default {
  props: {
    title:{
      type:String,
      default:'攻略资料',
    },
    topics:{
      type:Array,
      default(){
        const res=[]
        for(let i=0;i<4;i++){
          res.push({
            id:i,
            title:'《瑞奇与叮当 时空跳转》全克莱歌熊位置',
            abstract:'一共九个。',
            editor:{
              name:'沁雅畅慧',
            },
            time:1623254400000,
          })
        }
        return res
      }
    },
  },
  methods:{
    timeStampToString(time){
      return moment(time).format('YYYY-MM-DD')
    }
  }
};
</script>

<style lang="scss" scoped>
.wrapper {
  height: 425.75px;
  .vg-tit {
    width: 100%;
    h2 {
      font-size: 30px;
      float: left;
      padding-right: 50px;
      height: 34px;
      line-height: 34px;
      margin-bottom: 20px;
      a {
        text-decoration: none;
        color: #000;
      }
    }
  }
  .vg-list {
    li {
      box-sizing: border-box;
      text-align: left;
      list-style: none;
      width: 19.5%;
      margin-right: 1.5%;
      padding: 0;
      float: left;
      border-radius: 3px;
      box-shadow: 0 1px 2px 0 rgb(0 0 0 / 15%), 0px 2px 4px 0px rgb(0 0 0 / 10%);
      background: #fff;
      .img-box {
        img {
          width: 100%;
        }
      }
      .info-box {
        padding: 10px 10px 0 10px;
        h2 {
          height: 56px;
          font-size: 18px;
          line-height: 28px;
          max-height: 56px;
          font-weight: bold;
          overflow: hidden;
        }
        p {
          font-size: 14px;
          line-height: 20px;
          height: 40px;
          overflow: hidden;
          color: #262626;
        }
        .fot-box {
          width: 100%;
          height: 20px;
          line-height: 20px;
          margin: 10px 0;
          span {
            color: #9497a0;
            font-size: 12px;
            line-height: 22px;
            height: 22px;
          }
          .time-box i {
            width: 12px;
            height: 12px;
            background-position: -101px -33px;
          }
        }
      }
    }
    li:first-child{
      width: 36%;
      box-sizing: border-box;
      margin-right: 1%;
      position: relative;
      .info-box{
        width: 100%;
        box-sizing: border-box;
        position: absolute;
        background: url('https://www.vgtime.com/resources/img/FAFAFA_bg.png') bottom left repeat-x;
        left: 0;
        bottom: 0;
        z-index: 5;
      }
    }
  }
}
.icon {
  background: url("https://www.vgtime.com/resources/img/spri_01.svg") 0 0
    no-repeat;
}
</style>