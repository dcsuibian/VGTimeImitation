<template>
  <div class="user-nav">
    <div class="user-img">
      <i class="iconfont">&#xe6cf;</i>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserNav",
};
</script>

<style lang="scss" scoped>
.user-nav {
  position: fixed;
  top: 81px;
  left: 0;
  bottom: 0;
  width: 50px;
  background: #f0f0f0
    url("https://www.vgtime.com/resources/img/vertical_nav_bg.png");
  div.user-img {
    width: 100%;
    height: 50px;
    i{
      display: block;
      text-align: center;
      line-height: 50px;
      font-weight: bold;
      width: 100%;
      height: 100%;
      background-color: #f0f0f0;
      border: none;
    }
  }
}
</style>