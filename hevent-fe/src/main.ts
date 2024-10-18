import '@/assets/scss/main.scss';
import '@/assets/scss/icon.scss';
import 'element-plus/dist/index.css';

import App from './App.vue';

import router from '@/routers';
import { createApp } from 'vue';
import { createPinia } from 'pinia';
import ElementPlus from 'element-plus';
import * as ElementPlusIconsVue from '@element-plus/icons-vue';
import piniaPluginPersist from 'pinia-plugin-persistedstate';


const app = createApp(App);
const pinia = createPinia();

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component);
}

pinia.use(piniaPluginPersist);

app.use(router);
app.use(pinia);
app.use(ElementPlus);
app.mount('#app')