<template>
  <div class="vg-header" :class="{ fixed: needFixed }">
    <div class="logo left">
      <a href></a>
    </div>
    <div class="message">
      <div class="left">
        <a target="_blank" href>UCG商城</a>
        <a target="_blank" href>网站功能反馈QQ群566282315</a>
      </div>
      <div class="right">
        <a class="iconfont" href>&#xe615;</a>
        <a class="iconfont" href>&#xea8c;</a>
        <a class="iconfont" href>&#xe64d;</a>
        <a class="iconfont" href>&#xe600;</a>
        <a class="iconfont" href>&#xe69a;</a>
      </div>
    </div>
    <nav class="left">
      <ul>
        <li><a class="active" href>动漫</a></li>
        <li><a href>游戏</a></li>
        <li><a href>攻略评测</a></li>
        <li><a href>新闻资讯</a></li>
        <li><a href>专题专栏</a></li>
        <li><a href>视频节目</a></li>
        <li><a href>俱乐部</a></li>
        <li><a href>UCG商城</a></li>
      </ul>
    </nav>
  </div>
</template>

<script>
export default {
  name: "VGHeader",
  data() {
    return {
      scrollTop: 0,
      scrollEventListener:null,
    };
  },
  computed: {
    needFixed() {
      return this.scrollTop >= 32;
    },
  },
  mounted() {
    this.scrollEventListener=window.addEventListener("scroll", () => {
      this.scrollTop = document.documentElement.scrollTop;
    });
  },
  beforeDestroy(){
    window.removeEventListener('scroll',this.scrollEventListener)
    console.debug('VGHeader的窗口滚动监听器已注销')
  }
};
</script>

<style lang="scss" scoped>
.vg-header {
  box-sizing: content-box;
  width: 1600px;
  height: 80px;
  border-bottom: 1px solid rgba(85, 85, 85, 0.05);
  box-shadow: 0 3px 8px 0 rgb(0 0 0 / 12%), 0px 6px 16px 0px rgb(0 0 0 / 15%);
  background-color: #fff;
  .logo {
    width: 188px;
    height: 100%;
    background: url("https://www.vgtime.com/resources/img/logo_bg.svg") center
      no-repeat;
    a {
      display: block;
      width: 100%;
      height: 100%;
      background: url("https://www.vgtime.com/resources/img/logo.svg") center
        no-repeat;
    }
  }
  .message {
    width: auto;
    height: 40%;
    padding-left: 188px;
    background-color: #131313;
    color: #a5aab8;
    line-height: 32px;
    a {
      height: 100%;
      color: #a5aab8;
    }
    .left {
      height: 100%;
      a {
        float: left;
        display: block;
        margin-left: 22px;
        font-size: 12px;
      }
    }
    .right {
      height: 100%;
      a {
        width: 28px;
        margin: 0 10px;
        display: inline-block;
        text-align: center;
        font-size: 18px;
        color: #a5aab8;
      }
    }
  }
  nav {
    box-sizing: content-box;
    width: 800px;
    height: 60%;
    ul {
      width: 100%;
      height: 100%;
      list-style: none;
      li {
        width: auto;
        height: 100%;
        display: inline-block;
        a {
          box-sizing: border-box;
          display: inline-block;
          width: 100px;
          height: 100%;
          line-height: 48px;
          text-align: center;
          color: #aab2bd;
          font-size: 14px;
          border-right: 1px solid rgba(85,85,85,0.05);
        }
        a.active {
          font-weight: bold;
          color: #000;
        }
      }
    }
  }
}
.vg-header.fixed{
  position: fixed;
  top: 0;
  width: 1600px;
  height: 48px;
  z-index: 10;
  .message{
    display: none;
  }

}
</style>
