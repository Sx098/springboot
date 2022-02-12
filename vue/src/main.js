import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import VueRouter from 'vue-router';
import router from './router';
import axios from 'axios';
import { validate } from 'vee-validate';
import 'element-ui/lib/theme-chalk/index.css';
import "./assets/css/global.css"
Vue.use(ElementUI)
Vue.use(VueRouter)
Vue.use(validate)
Vue.prototype.axios = axios;
Vue.config.productionTip = false
axios.defaults.withCredentials=true
new Vue({
  router:router,
  render: h => h(App),
}).$mount('#app')
