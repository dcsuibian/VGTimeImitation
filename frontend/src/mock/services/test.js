import Mock from 'mockjs'
Mock.mock(/\/test/,'get',options=>{
    console.debug('"/test"接收到请求')
    return {
        code:200,
        result:'Hello World',
    }
})