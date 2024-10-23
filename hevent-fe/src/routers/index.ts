import ArticleCategory from "@/views/article/ArticleCategory.vue";
import ArticleManage from "@/views/article/ArticleManage.vue";
import LayoutHome from "@/views/LayoutHome.vue";
import LoginForm from "@/views/LoginForm.vue";
import UserAvatar from "@/views/profile/UserAvatar.vue";
import UserPassword from "@/views/profile/UserPassword.vue";
import UserProfile from "@/views/profile/UserProfile.vue";
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
            {path: '/user/profile', component: UserProfile},
            {path: '/user/avatar', component: UserAvatar},
            {path: '/user/reset', component: UserPassword},
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