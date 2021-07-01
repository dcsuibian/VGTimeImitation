<template>
  <div class="home">
    <section class="foc-box">
      <ul>
        <li v-for="item in focus" :key="item.id">
          <FocusItem :topic="item"></FocusItem>
        </li>
      </ul>
      <div class="clear"></div>
    </section>
    <!-- <section class="game-news-box">
      <div class="vg-tit">
        <h2>
          <a href>新闻资讯</a>
        </h2>
        <div class="clear"></div>
      </div>
    </section> -->
    <NormalSection title="攻略资料" :topics="cheat"></NormalSection>
    <NormalSection title="深度评测" :topics="review"></NormalSection>
    <NormalSection title="游戏文化" :topics="culture"></NormalSection>
    <NormalSection title="动漫时光" :topics="comic"></NormalSection>
  </div>
</template>

<script>
import FocusItem from "./FocusItem";
import NormalSection from "./NormalSection.vue";
import { getAllHomePageData } from "@/api/homepage";
export default {
  name: "Home",
  components: {
    FocusItem,
    NormalSection,
  },
  data() {
    return {
      focus: [],
      news: [],
      cheat: [],
      review: [],
      culture: [],
      comic: [],
    };
  },
  mounted() {
    getAllHomePageData().then((res) => {
      console.debug("接收到首页信息：", res);
      for (const key in res.data) {
        this[key] = res.data[key];
      }
    });
    // getAllTopics().then((res) => {
    //   console.log("所有的topic：", res);
    //   this.focusNews = res.data.slice(0,5).map(item => {
    //     return Object.assign(
    //       {
    //         title: "打通《生化危机 村庄》后，我认为恐怖游戏不该迎合大众",
    //         cover:
    //           "https://img01.vgtime.com/game/cover/2021/06/07/21060716315220_u229514.jpg?x-oss-process=image/resize,m_pad,color_000000,w_800,h_500",
    //         author: {
    //           name: "果其然",
    //         },
    //         time: "",
    //       },
    //       item
    //     );
    //   });
    // });
  },
};
</script>
<style lang="scss" scoped>
.home {
  height: 10000px;
  padding: 0 20px 50px 20px;
  section.foc-box {
    margin-top: 30px;
    ul {
      li {
        position: relative;
        box-sizing: content-box;
        width: calc(25% - 12px);
        list-style: none;
        float: left;
        margin: 0 16px 10px 0;
        &:first-child {
          float: left;
          width: calc(50% - 8px);
        }
        &:last-child,
        &:nth-child(3) {
          margin-right: 0;
        }
      }
    }
  }
  section.game-news-box {
    h2 {
      float: left;
      height: 34px;
      line-height: 34px;
      margin-bottom: 20px;
      a {
        text-decoration: none;
        color: #000;
        font-size: 30px;
      }
    }
  }
}
</style>