<template>
  <div id="wrapper">
    <div class="vg-head-message">
      <div class="hd-top-left">
        <a target="_blank" href>UCG商城</a>
        <a target="_blank" href>网站功能反馈QQ群566282315</a>
      </div>
    </div>
    <nav>
      <ul class="vg-list">
        <li>
          <a>动漫</a>
        </li>
        <li>
          <a>游戏</a>
        </li>
        <li>
          <a>攻略评测</a>
        </li>
        <li>
          <a>新闻资讯</a>
        </li>
        <li>
          <a>专题专栏</a>
        </li>
        <li>
          <a>视频节目</a>
        </li>
        <li>
          <a>俱乐部</a>
        </li>
        <li>
          <a>UCG商城</a>
        </li>
      </ul>
    </nav>
  </div>
</template>
<script>
export default {
  name: "Navbar",
};
</script>

<style lang="scss" scoped>
#wrapper {
  width: 100%;
  height: 80px;
  background-color: #fff;
  box-shadow: 0 3px 8px 0 rgb(0 0 0 / 12%), 0px 6px 16px 0px rgb(0 0 0 / 15%);
  border-bottom: 1px solid rgba(85, 85, 85, 0.05);
  .vg-head-message {
    width: 100%;
    height: 40%;
    line-height: 32px;
    background-color: #131313;
    padding-left: 188px;
    box-sizing: border-box;
    .hd-top-left {
      float: left;
      height: 100%;
      overflow: hidden;
      a {
        margin-left: 22px;
        font-size: 12px;
        color: #a5aab8;
        text-decoration: none;
      }
    }
  }
  nav {
    float: left;
    height: 60%;
    padding-left: 188px;
    overflow: hidden;
    width: 988px;
    .vg-list {
      float: left;
      width: 100%;
      margin-bottom: 0;
      li {
        width: auto;
        border: 0;
        box-shadow: none;
        padding: 0;
        float: left;
        list-style: none;
        display: list-item;
        line-height: 48px;
        font-size: 14px;
        color: #aab2bd;
        a{
            width: 100px;
            display: block;
            border-right: 1px solid rgba(85,85,85,0.05);
        }
        &:first-child {
          font-weight: bold;
          color: #000;
        }
      }
    }
  }
}
</style>