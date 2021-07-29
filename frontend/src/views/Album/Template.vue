<template>
  <div class="album-template">
    <h2><a href>《最终幻想7 重制版 间奏》图集</a></h2>
    <section>
      <div class="big-picture">
        <img
          src="https://img01.vgtime.com/game/cover/2021/02/26/210226113831682_u77470.jpg?x-oss-process=image/resize,m_pad,color_000000,w_1400,h_880"
          alt="图片加载失败"
        />
        <div class="big-picture left"></div>
        <div class="big-picture right"></div>
      </div>
      <div class="carousel">
        <ul>
          <li>
              <img src="https://img01.vgtime.com/game/cover/2021/02/26/210226113831682_u77470.jpg?x-oss-process=image/resize,m_pad,color_000000,w_104,h_59">
          </li>
        </ul>
      </div>
      <div class="scroll"></div>
      <div class="info">
          <span>1/4 张图片</span>
          <span>游戏时光图库</span>
      </div>
    </section>
  </div>
</template>
<script>
export default {
  name: "AlbumTemplate",
};
</script>

<style lang="scss" scoped>
.album-template {
  width: 100%;
  max-width: 1030px;
  margin: 20px auto 0 auto;
  h2 {
    margin-top: 17px;
  }
  section {
    .big-picture {
      width: 100%;
      text-align: center;
      border-radius: 5px;
      background-color: #000;
      position: relative;
      height: auto;
      img {
        width: 100%;
      }
      .big-picture {
        position: absolute;
        top: 0;
        width: 50%;
        height: 100%;
        background-color: transparent;
        z-index: 2;
        cursor: pointer;
      }
      .big-picture.right {
        right: 0;
      }
    }
    .carousel {
      width: 100%;
      height: 73px;
      margin: 0 0 10px 0;
      padding-top: 10px;
      text-align: left;
      ul {
        list-style: none;
        li{
            display: inline-block;
            width: 94px;
            height: 63px;
        }
      }
    }
  }
}
</style>