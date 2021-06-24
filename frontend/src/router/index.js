import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    redirect:'/topic/1126958', // 开发时临时使用
  },
  {
    path: '/topic/:id',
    name: 'TopicTemplate',
    component: () => import('../views/topic/Template'),
    props:route=>{
      return {
        id:Number(route.params.id),//交给组件之前就把id转成数字
      }
    }
  }
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
