import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

// mock
// WARNING: `mockjs` NOT SUPPORT `IE` PLEASE DO NOT USE IN `production` ENV.
import './mock'
import './global.css'
import '@/assets/iconfont/iconfont.css'


Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
