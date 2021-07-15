<template>
  <div class="basic-layout">
    <div class="header">
      <VGHeader></VGHeader>
    </div>
    <div class="content">
        <router-view/>
    </div>
    <UserNav class="user-nav">
    </UserNav>
  </div>
</template>

<script>
import VGHeader from '@/components/VGHeader'
import Navbar from "./Navbar";
import UserNav from './UserNav.vue';

export default {
  name: "BasicLayout",
  components: {
    VGHeader,
    UserNav,
  },
  data() {
    return {};
  },
};
</script>


<style lang="scss" scoped>
.basic-layout {
  box-sizing: border-box;
  padding-left: 50px;
  width: 100%;
  .header {
    width: 1600px;
    margin: 0 auto;
  }
  .content {
    width: 100%;
    max-width: 1600px;
    margin: 0 auto;
    padding: 0 20px 50px 20px;
    box-sizing: content-box;
  }
  
}
</style>