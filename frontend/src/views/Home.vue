<template>
  <div class="home">
    <section class="foc-box">
      <ul>
        <li v-for="item in focusNews" :key="item.cover">
          <FocusItem :topic="item"></FocusItem>
        </li>
      </ul>
      <div class="clear"></div>
    </section>
  </div>
</template>

<script>
import FocusItem from './FocusItem'
const focusNews=[
  {
    cover:'https://img01.vgtime.com/game/cover/2021/06/07/21060716315220_u229514.jpg?x-oss-process=image/resize,m_pad,color_000000,w_800,h_500',
    author:{
      name:'果其然'
    },
    time:'',
  },
  {
    cover:'https://img01.vgtime.com/game/cover/2021/06/27/210627113805836_u93176.jpg?x-oss-process=image/resize,m_pad,color_000000,w_800,h_500',
    author:{
      name:'科泽林雾'
    },
    time:'',
  },
  {
    cover:'https://img01.vgtime.com/game/cover/2021/06/26/21062610385619_u149070.jpg?x-oss-process=image/resize,m_pad,color_000000,w_800,h_500',
    author:{
      name:'秋雨'
    },
    time:'',
  },
  {
    cover:'https://img01.vgtime.com/game/cover/2021/06/24/210624183740219_u980.jpg?x-oss-process=image/resize,m_pad,color_000000,w_800,h_500',
    author:{
      name:'Lost'
    },
    time:'',
  },
  {
    cover:'https://img01.vgtime.com/game/cover/2021/06/23/21062322212726_u524.jpg?x-oss-process=image/resize,m_pad,color_000000,w_800,h_500',
    author:{
      name:'苏活'
    },
    time:'',
  },
]
export default {
  name: "Home",
  components: {
    FocusItem,
  },
  data(){
    return {
      focusNews,
    }
  }
};
</script>
<style lang="scss" scoped>
.home {
  padding: 0 20px 50px 20px;
  section.foc-box {
    margin-top: 30px;
    background-color: pink;
    ul{
      li{
        position: relative;
        box-sizing: content-box;
        width: calc(25% - 12px);
        list-style: none;
        float: left;
        margin: 0 16px 10px 0;
        &:first-child{
          float: left;
          width: calc(50% - 8px);
        }
        &:last-child,&:nth-child(3){
          margin-right: 0;
        }
      }
    }
  }
}
</style>