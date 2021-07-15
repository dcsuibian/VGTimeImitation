<template>
  <div class="home">
    <section class="focus-box">
      <ul>
        <li v-for="item in focus" :key="item.id">
          <FocusItem :topic="item"></FocusItem>
        </li>
      </ul>
      <div class="clear"></div>
    </section>
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
  },
};
</script>
<style lang="scss" scoped>
.home {
  section.focus-box {
    margin-top: 30px;
    margin-bottom: 30px;
    ul {
      list-style: none;
      li {
        box-sizing: border-box;
        float: left;
        width: calc(25% - 12px);
        height: 236.25px;
        margin: 0 16px 10px 0;
      }
      li:first-child{
        width: calc(50% - 8px);
        height: 482.5px;
      }
      li:nth-child(3),li:last-child{
        margin-right: 0;
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