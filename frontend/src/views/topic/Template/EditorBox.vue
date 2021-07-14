<template>
  <div class="editor-box">
    <div class="user-box">
      <span class="user-img-box">
        <img :src="editor.avatar" :alt="editor.name" />
      </span>
    </div>
    <p>{{ editor.description }}</p>
  </div>
</template>
<script>
export default {
  name: "EditorBox",
  props: {
    editor: {
      type: Object,
      default: () => {
        return {
          id: 1,
          name: "沁雅畅慧",
          description:
            "性别男，小透明，FPS游戏忠实爱好者，每年都买COD，有生之年玩到了《死亡搁浅》。",
          avatar: "https://img01.vgtime.com/space/190131161749974.png",
        };
      },
    },
  },
};
</script>

<style lang="scss" scoped>
.editor-box {
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
        height: 40px;
      img {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        margin-right: 10px;
        background-image: linear-gradient(150deg, #a1ddfa 0%, #68b9f4 100%);
      }
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