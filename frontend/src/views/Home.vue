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
    <section class="game-news-box">
      <div class="vg-tit">
        <h2>
          <a href>新闻资讯</a>
        </h2>
        <div class="clear"></div>
      </div>
    </section>
  </div>
</template>

<script>
import FocusItem from "./FocusItem";
const focusNews = [
  {
    title: "打通《生化危机 村庄》后，我认为恐怖游戏不该迎合大众",
    cover:
      "https://img01.vgtime.com/game/cover/2021/06/07/21060716315220_u229514.jpg?x-oss-process=image/resize,m_pad,color_000000,w_800,h_500",
    author: {
      name: "果其然",
    },
    time: "",
  },
  {
    cover:
      "https://img01.vgtime.com/game/cover/2021/06/27/210627113805836_u93176.jpg?x-oss-process=image/resize,m_pad,color_000000,w_800,h_500",
    title: "VG本周玩家热点：重返PS商店后，夜之城变得更好了吗？",
    author: {
      name: "科泽林雾",
    },
    time: "",
  },
  {
    title: " ",
    cover:
      "https://img01.vgtime.com/game/cover/2021/06/26/21062610385619_u149070.jpg?x-oss-process=image/resize,m_pad,color_000000,w_800,h_500",
    author: {
      name: "秋雨",
    },
    time: "",
  },
  {
    title: "入手国行主机好时机！下半年XGP十大首发作品",
    cover:
      "https://img01.vgtime.com/game/cover/2021/06/24/210624183740219_u980.jpg?x-oss-process=image/resize,m_pad,color_000000,w_800,h_500",
    author: {
      name: "Lost",
    },
    time: "",
  },
  {
    title: "《绯红结系》评测：酷炫超脑力战斗之外，它讲了个好故事",
    cover:
      "https://img01.vgtime.com/game/cover/2021/06/23/21062322212726_u524.jpg?x-oss-process=image/resize,m_pad,color_000000,w_800,h_500",
    author: {
      name: "苏活",
    },
    time: "",
  },
];
export default {
  name: "Home",
  components: {
    FocusItem,
  },
  data() {
    return {
      focusNews,
    };
  },
};
</script>
<style lang="scss" scoped>
.home {
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