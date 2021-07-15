<template>
  <div class="author-box">
    <div class="user-box">
      <span class="guanzhu"> 关注 </span>
      <span class="user-img-box">
        <img :src="author.avatar" :alt="author.name" />
      </span>
      <span class="user-name">
        {{ author.name }}
      </span>
      <span class="user-title">
        {{ author.title }}
      </span>
    </div>
  </div>
</template>
<script>
export default {
  name: "AuthorBox",
  props: {
    author: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style lang="scss" scoped>
.author-box {
  width: 100%;
  padding: 20px;
  margin-bottom: 20px;
  border: 1px solid rgba(0, 0, 0, 0.05);
  box-shadow: 0 1px 2px 0 rgb(0 0 0 / 15%), 0 2px 4px 0 rgb(0 0 0 / 10%);
  border-radius: 3px;
  background: url("https://www.vgtime.com/resources/img/author_bg.svg") right
    bottom no-repeat;
  .user-box {
    width: 100%;
    height: 40px;
    span.user-img-box {
      float: left;
      height: 40px;
      img {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        margin-right: 10px;
        background-image: linear-gradient(150deg, #a1ddfa 0%, #68b9f4 100%);
      }
    }
    span.user-name {
      font-size: 14px;
      color: #000;
      font-weight: bold;
      margin: 3px 0;
      float: left;
      width: auto;
    }
    span.user-title {
      height: 16px;
      width: calc(100% - 50px);
      float: left;
      color: #2abaca !important;
    }
    span.guanzhu {
      // 应该把中文去掉的
      float: right;
      border: 2px solid #2abaca;
      border-radius: 3px;
      color: #2abaca;
      cursor: pointer;
      font-size: 12px;
      line-height: 24px;
      right: 20px;
      text-align: center;
      top: 20px;
      width: 60px;
      transition: all 0.2s ease;
      -webkit-transition: all 0.2s ease;
    }
  }
  > p {
    width: 100%;
    color: #414141;
    margin-bottom: 0;
    margin-top: 20px;
    font-size: 14px;
  }
}
</style>