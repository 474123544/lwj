import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/assets/css/iconfont.css'
import '@/assets/css/style.scss'
const app = createApp(App)
app.config.warnHandler = () => null;
//element-plus
import 'element-plus/theme-chalk/dark/css-vars.css';
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import ElementPlus from 'element-plus'
app.use(ElementPlus,{
	locale:zhCn
})

//地图
import VueAMap, { initAMapApiLoader } from "@vuemap/vue-amap";
import "@vuemap/vue-amap/dist/style.css"
initAMapApiLoader({
  key: "c4ae6ed30bc3f01acf60971dd5d65e7b",//高德key
  securityJsCode:'4d49f68235e05c86c862eeb230ddfc05',//高德安全密钥
  plugins: [
    "AMap.Autocomplete",
    "AMap.PlaceSearch",
    "AMap.Scale",
    "AMap.OverView",
    "AMap.ToolBar",
    "AMap.MapType",
    "AMap.PolyEditor",
    "AMap.CircleEditor",
    "AMap.Geocoder",
    "AMap.Geolocation",
	"AMap.Marker"
  ],
});
app.use(VueAMap)

//富文本
import Editor from "@/components/Editor";
app.component('editor', Editor)
//上传组件
import upload from "@/components/upload";
app.component('uploads', upload)
//轮播图
import mySwiper from "@/components/mySwiper/index";
app.component('mySwiper', mySwiper)
import http from './utils/http.js'
// 基础配置
import config from './utils/config'
//公共方法
import toolUtil from './utils/toolUtil.js'
app.config.globalProperties.$config = config.get()
app.config.globalProperties.$project = config.getProjectName()
app.config.globalProperties.$toolUtil = toolUtil
app.config.globalProperties.$http = http // ajax请求方法

app.use(store)
app.use(router)
app.mount('#app')
