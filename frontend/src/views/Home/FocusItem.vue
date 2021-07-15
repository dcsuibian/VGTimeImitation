<template>
  <div class="focus-item">
    <div class="img-box">
      <a target="_blank" :href="'/topic/' + topic.id">
        <img :src="topic.cover" alt="加载错误" />
      </a>
    </div>
    <!-- <div class="info-box">
      <a :href="'/topic/'+topic.id">
        <h2>{{ topic.title }}</h2>
      </a>
      <div class="fot-box">
        <div class="left">
          <span class="author-name">{{ topic.author.name }}</span>
        </div>
        <div class="right">
          <span class="time-box">
            <i class="icon"></i>
            {{ timeString }}
          </span>
        </div>
      </div>
    </div> -->
  </div>
</template>

<script>
import moment from "moment";
export default {
  name: "FocusItem",
  props: {
    topic: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {};
  },
  computed: {
    timeString() {
      return moment(this.time).format("YYYY-MM-DD");
    },
  },
};
</script>

<style lang="scss" scoped>
.focus-item {
  width:100%;
  height: 100%;
  .img-box {
    width: 100%;
    height: 100%;
    a {
      display: inline-block;
      width: 100%;
      height: 100%;
      img {
        width: 100%;
        height: 100%;
      }
    }
  }
  .info-box {
    position: absolute;
    padding: 10px;
    left: 0;
    bottom: 0;
    width: 100%;
    box-sizing: border-box;
    background: url("https://www.vgtime.com/resources/img/black_cover.png") left
      center repeat-x;
    background-size: 100% 100%;
    a {
      text-decoration: none;
      h2 {
        color: #fff;
        height: auto;
        font-size: 18px;
        line-height: 28px;
        max-height: 56px;
        font-weight: bold;
        margin-bottom: 10px;
        overflow: hidden;
        text-align: left;
      }
    }
    .fot-box {
      width: 100%;
      span {
        color: #9497a0;
        font-size: 12px;
        line-height: 22px;
        height: 22px;
      }
      .time-box {
        i.icon {
          position: relative;
          top: 1px;
          margin-right: 5px;
          display: inline-block;
          width: 12px;
          height: 12px;
          background: url("https://www.vgtime.com/resources/img/spri_01.svg") 0
            0 no-repeat;
          background-position: -101px -33px;
        }
      }
    }
  }
}
</style>