import Vue from 'vue'
import App from './App.vue'
import router from './router'

// 引入element ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// 引入mavon-editor
import mavonEditor from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';


// use _ element ui
//并设置size属性的组件的默认尺寸均为small
Vue.use(ElementUI);

// use _ mavon-editor
Vue.use(mavonEditor);

// 全局样式
import './assets/all.css'

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
