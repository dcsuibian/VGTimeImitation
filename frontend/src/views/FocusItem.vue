<template>
  <div class="wrapper">
    <div class="img-box">
      <a href>
        <img
          :src="topic.cover"
          alt="加载错误"
        />
      </a>
    </div>
    <div class="info-box">
      <div class="fot-box">
        <div class="left">
          <span class="author-name">{{topic.author.name}}</span>
        </div>
        <div class="right">
          <span class="time-box">
            <i class="icon"></i>
            {{topic.time}}
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "FocusItem",
  props:{
      topic:{
          type:Object,
          default:()=>{
              return {
                  cover:'https://img01.vgtime.com/game/cover/2021/06/07/21060716315220_u229514.jpg',
                  author:{
                      name:'果其然'
                  },
                  time:'2021-06-27',
              }
          }
      }
  },
  data() {
    return {};
  },
};
</script>

<style lang="scss" scoped>
.wrapper {
  .img-box {
    width: 100%;
    img {
      width: 100%;
      height: 100%;
    }
  }
  .info-box {
    position: absolute;
    padding: 10px;
    bottom: 0;
    width: 100%;
    box-sizing: border-box;
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