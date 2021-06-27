import Vue from 'vue'
import VueRouter from 'vue-router'
import BasicLayout from '@/layouts'
import Test from '@/views/Test'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Test,
    // redirect:'/',
    children:[
      {
        path: '/topic/:id',
        name: 'TopicTemplate',
        component: () => import(/* webpackChunkName: "topic-template" */'../views/topic/Template'),
        props:route=>{
          return {
            id:Number(route.params.id),//交给组件之前就把id转成数字
          }
        }
      }
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  /* 路由发生变化修改页面title */
  if (to.meta.title) {
    document.title = to.meta.title;
  }
  next();
})

export default router
