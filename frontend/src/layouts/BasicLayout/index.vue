<template>
  <div class="main-body">
    <div class="header">
      <Navbar></Navbar>
    </div>
    <div class="vg-main">
        <router-view/>
    </div>
    <UserNav class="user-nav">

    </UserNav>
  </div>
</template>

<script>
import Navbar from "./Navbar";
import UserNav from './UserNav.vue';

export default {
  name: "BasicLayout",
  components: {
    Navbar,
    UserNav,
  },
  data() {
    return {};
  },
};
</script>


<style lang="scss" scoped>
.main-body {
  padding-left: 50px;
  width: 100%;
  box-sizing: border-box;
  .header {
    width: 100%;
    max-width: 1600px;
    margin: 0 auto;
  }
  .vg-main {
    width: 100%;
    max-width: 1600px;
    margin: 0 auto;
    padding: 0 20px 50px 20px;
    box-sizing: content-box;
  }
  .user-nav{
    background-color: red;
    position: absolute;
    left: 0;
    bottom: 0;
    width: 50px;
    top: 81px;
  }
}
</style>