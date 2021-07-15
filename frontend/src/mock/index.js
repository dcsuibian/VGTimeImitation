// 如果是生产模式，不开启Mockjs
let enableMock=process.env.NODE_ENV !== 'production'
// 只在直接访问webpack dev server时才启用（也就是说nginx环境下不会启用）
enableMock=enableMock?/https?:\/\/localhost:20119/.test(document.URL):false

if(enableMock){
    console.log('Mock.js 挂载中')
    const Mock=require('mockjs')
    require('./services/topic')
    require('./services/test')
    require('./services/homepage')
    console.log('完成 Mock.js 挂载')
}