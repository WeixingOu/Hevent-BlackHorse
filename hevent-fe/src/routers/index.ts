import ArticleCategory from "@/views/article/ArticleCategory.vue";
import ArticleManage from "@/views/article/ArticleManage.vue";
import LayoutHome from "@/views/LayoutHome.vue";
import LoginForm from "@/views/LoginForm.vue";
import {createRouter, createWebHistory, type RouteRecordRaw} from "vue-router";

const routes: RouteRecordRaw[] = [
    {
        path: '/',
        component: LayoutHome,
        redirect: '/article/manage',

        children: [
            {
                path: '/article/manage',
                component: ArticleManage
            },
            {path: '/article/category', component: ArticleCategory},
        ]
    },
    {
        path: '/login',
        component: LoginForm
    },

]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router