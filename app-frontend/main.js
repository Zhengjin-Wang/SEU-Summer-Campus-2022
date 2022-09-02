import App from './App'
import {myRequest} from './utils/api.js'

// #ifndef VUE3
import Vue from 'vue'
import uView from "uview-ui"
import {calTime} from './utils/calTime.js'
Vue.use(uView)
Vue.config.productionTip = false
Vue.prototype.$BASE_URL="http://101.132.107.178:8089"
Vue.prototype.$ISAUTHORIZATION=true
Vue.prototype.$myRequest=myRequest
Vue.prototype.$calTime=calTime
App.mpType = 'app'
const app = new Vue({
    ...App
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif