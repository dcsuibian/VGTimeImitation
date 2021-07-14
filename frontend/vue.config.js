module.exports = {
  devServer: {
    port: 20119, // 119是“网络新闻传输协议 - 用来收取新闻组的消息”的东西，其实我不了解这个东西，只是单纯地想用一个不容易重复的端口而已
  },
  chainWebpack: (config) => {
    config.plugin("html").tap((args) => {
      args[0].title = "VGTimeImitation - 游戏时光模仿项目";
      return args;
    });
  },
};
