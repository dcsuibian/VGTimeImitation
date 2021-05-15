<template>
  <div>
    <p>测试页面</p>
  </div>
</template>

<script>
import moment from "moment";
import axios from "axios";
export default {
  name: "Test",
  components: {},
  mounted() {
    axios.get("/test").then((res) => {
      console.log('接收到了信息：',res)
    });
  },
};
</script>
