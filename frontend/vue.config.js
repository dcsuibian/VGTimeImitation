module.exports={
    devServer:{
        port:8000,
    },
    chainWebpack: config => {
        config
          .plugin('html')
          .tap(args => {
            args[0].title= 'VGTimeImitation - 游戏时光模仿项目'
            return args
          })
      }
}