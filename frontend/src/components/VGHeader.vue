<template>
  <div class="head-box">
    <header>
      <nav class="left">
        <h1 class="vg-logo left">
          <a href="/">游戏时光</a>
        </h1>
      </nav>
    </header>
  </div>
</template>

<script>
export default {
  name: "VGHeader",
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.outer {
  margin: 0 auto;
}
.head-box{
    position: absolute;
    width: 100%;
    margin: 0 auto;
}
header {
  width: 100%;
  max-width: 1600px;
  margin: 0 auto;
  background-color: black;
  height: 80px;
  line-height: 48px;
  box-shadow: 0 3px 8px 0 rgb(0 0 0 / 12%), 0px 6px 16px 0px rgb(0 0 0 / 15%);
  border-bottom: 1px solid rgba(85, 85, 85, 0.05);
  background-color: #fff;
  position: absolute;
  z-index: 600;
  box-sizing: content-box;
  left: 50%;
  margin-left: 25px;
  float: left;
}
.vg-logo {
  background: url("/resources/img/logo_bg.svg") left bottom no-repeat;
  a {
    float: left;
    background: url("/resources/img/logo.svg") center no-repeat;
    text-indent: -9999px;
  }
}
</style>
