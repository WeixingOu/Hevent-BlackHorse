import ArticleCategory from "@/views/article/ArticleCategory.vue";
import LayoutHome from "@/views/LayoutHome.vue";
import LoginForm from "@/views/LoginForm.vue";
import {createRouter, createWebHistory, type RouteRecordRaw} from "vue-router";

const routes: RouteRecordRaw[] = [
    {
        path: '/',
        component: LayoutHome,
        redirect: '/article/category',

        children: [
            {
                path: '/article/category',
                component: ArticleCategory
            }
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