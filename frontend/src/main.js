import Vue from 'vue'
import App from './App.vue'
import './plugins/element.js'
import VueCookies from 'vue-cookies';
import router from './router'
import store from './store'

Vue.config.productionTip = false
Vue.use(VueCookies);

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
}).$mount('#app')
