import Vue from 'vue'
import VueRouter from 'vue-router'
import {BasicLayout} from '@/layouts'
import Home from '@/views/Home'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: BasicLayout,
    children:[
      {
        path:'/',
        component:Home,
      },
      {
        path: '/test',
        component: ()=>import('../views/Test'),
      },
      {
        path: '/topic/:id',
        component: () => import('../views/topic/Template'),
        props:route=>{
          return {
            id:Number(route.params.id),//交给组件之前就把id转成数字
          }
        }
      }
    ]
  },
  {
    path: '*',
    redirect: '/404',
  }
]

console.log(routes)
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  /* 路由发生变化修改页面title */
  if (to?.meta?.title??false) {
    document.title = to.meta.title;
  }else{
    document.title='VGTimeImitation - 游戏时光模仿项目'
  }
  next();
})

export default router
