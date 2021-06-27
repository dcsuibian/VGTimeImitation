<template>
  <div class="main-body">
    <div class="header">
      <Navbar></Navbar>
    </div>
    <div class="vg-main">
      <section class="foc-box">
        <router-view/>
      </section>
    </div>
  </div>
</template>

<script>
import Navbar from "./Navbar";

export default {
  name: "BasicLayout",
  components: {
    Navbar,
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
    box-sizing: border-box;
    .foc-box{
      margin: 30px;
    }
  }
}
</style>