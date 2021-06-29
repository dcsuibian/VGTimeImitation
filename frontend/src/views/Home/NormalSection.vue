<template>
  <div class="wrapper">
    <div class="vg-tit">
      <h2>
        <a href title="攻略资料">攻略资料</a>
      </h2>
      <div class="clear"></div>
    </div>
    <ul class="vg-list">
      <li>
        <div class="img-box">
          <a target="_blank" href title="《瑞奇与叮当 时空跳转》全克莱歌熊位置">
            <img
              src="https://img01.vgtime.com/game/cover/2021/06/02/21060216013458_u2505.jpg?x-oss-process=image/resize,m_pad,color_000000,w_640,h_400"
              alt="《瑞奇与叮当 时空跳转》全克莱歌熊位置"
            />
          </a>
        </div>
        <div class="info-box">
          <a target="_blank" href title="《瑞奇与叮当 时空跳转》全克莱歌熊位置"
            ><h2>《瑞奇与叮当 时空跳转》全克莱歌熊位置</h2></a
          >
          <p>一共九个。</p>
          <div class="fot-box">
            <div class="left">
              <span class="user_name">沁雅畅慧</span>
            </div>
            <div class="right">
              <span class="time-box"><i class="icon"></i>2021-06-10</span>
            </div>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: {
    title: {
      type: String,
      default: "攻略资料",
    },
  },
};
</script>

<style lang="scss" scoped>
.wrapper {
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
      text-align: left;
      list-style: none;
      width: 20%;
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
        p{
          font-size: 14px;
          line-height: 20px;
          height: 40px;
          overflow: hidden;
          color: #262626;
        }
        .fot-box{
          width: 100%;
          height: 20px;
          line-height: 20px;
          margin: 10px 0;
          span{
            color: #9497a0;
            font-size: 12px;
            line-height: 22px;
            height: 22px;

          }
          .time-box i{
            width: 12px;
            height: 12px;
            background-position: -101px -33px;
          }
        }
      }
    }
  }
}
.icon{
  background: url('https://www.vgtime.com/resources/img/spri_01.svg') 0 0 no-repeat;
}
</style>