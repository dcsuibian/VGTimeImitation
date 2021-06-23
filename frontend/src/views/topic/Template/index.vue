<template>
  <div>
    <VGHeader></VGHeader>
    <div class="vg-main">
      <div class="main-center">
        <article>
          <h1 class="article-title">{{ topic.title }}</h1>
          <div class="article-info">
            作者
            <span class="color user-card-toggle">{{ topic.author.name }}</span>
            &nbsp;&nbsp; 编辑
            <span class="color user-card-toggle">{{ topic.editor.name }}</span>
            <span class="time-box">
              <i class="icon"></i>
              {{ topic.time.format("YYYY-MM-DD HH:mm:ss") }}
            </span>
          </div>
          <div class="abstract">
            <p>{{ topic.abstract }}</p>
          </div>
          <div class="topic-content front-content" v-html="topic.content"></div>
        </article>
      </div>
      <div class="clear"></div>
    </div>
  </div>
</template>
<script>
import { getTopic } from "@/api/topic";
import VGHeader from '@/components/VGHeader'
import moment from "moment";
export default {
  name: "Template",
  props: {
    id: {
      type: Number,
      required: true,
    },
  },
  components:{
    VGHeader,
  },
  data() {
    return {
      topic: {
        title: "",
        abstract: "",
        content: "",
        editor: {},
        author: {},
        time: moment(),
      },
    };
  },
  created() {
    this.fetchTopicData();
  },
  watch: {
    $route: "fetchTopicData",
  },
  methods: {
    fetchTopicData() {
      return getTopic(this.id).then((res) => {
        console.debug("请求到的topic：", res.data);
        const topic = JSON.parse(JSON.stringify(res.data)); // 深拷贝
        this.topic = topic;
        this.topic.time = moment(this.topic.time);
        document.title = 'VGTimeImitation - '+this.topic.title
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.clear {
  clear: both;
}
.color {
  color: #2abaca !important;
}
.vg-main {
  width: 100%;
  max-width: 1600px;
  margin: 0 auto;
  padding: 84px 0 0 0;
}
.abstract {
  position: relative;
  padding: 20px;
  border-top: 1px solid rgba(0, 0, 0, 0.05);
  border-bottom: 1px solid rgba(0, 0, 0, 0.05);
  margin-bottom: 30px;
  font-size: 16px;
  line-height: 20px;
  color: #2b2b2b;
  p {
    text-indent: 0;
    margin-bottom: 0;
    font-size: 16px;
    line-height: 24px;
    margin: 0;
    text-align: left;
  }
}
article {
  font-size: 16px;
  line-height: 20px;
  color: #2b2b2b;
  float: left;
  width: 100%;
  p {
    font-size: 16px;
    line-height: 28px;
    background: 0;
    color: rgba(38, 38, 38, 0.9);
  }
  .time-box .icon {
    position: relative;
    margin-right: 6px;
    top: 1px;
    opacity: 0.7;
    background-position: -101px -33px;
    display: inline-block;
    width: 12px;
    height: 12px;
    background: url("/resources/img/spri_01.svg") 0 0 no-repeat;
    z-index: 1;
    font-style: italic;
    margin: 0;
    list-style: none;
    border: 0;
    -webkit-appearance: none;
    outline: 0;
  }
}
.main-center {
  float: none;
  width: 50%;
  max-width: 700px;
  padding-top: 30px;
  margin: 0 auto;
}
.article-title {
  font-size: 48px;
  color: #000;
  line-height: 158%;
  font-weight: bold;
  margin-bottom: 30px;
}
.article-info {
  margin-bottom: 20px;
  text-align: right;
  color: #999;
  font-size: 12px;
}
.user-card-toggle {
  cursor: pointer;
}
.front-content {
  p {
    margin: 10px 0;
  }
}
</style>