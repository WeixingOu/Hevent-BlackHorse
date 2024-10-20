<template>
    <div class="wrapper">
        <v-header />

        <v-sidebar />

        <div class="content-box" :class="{ 'content-collapse': sidebar.collapse }">

            <el-main>
                <router-view/>
            </el-main>

            <el-footer>HEVENT ©2024 Created by Weixing Ou</el-footer>
        </div>
    </div>
</template>

<script setup lang="ts">
    import { useSidebarStore } from '@/stores/sidebar';
    import vHeader from '@/components/HeaderBar.vue';
    import vSidebar from '@/components/SideBar.vue';

    const sidebar = useSidebarStore();


</script>

<style>
    .wrapper {
        height: 100vh;
        overflow: hidden;

        .content-box {
            position: absolute;
            left: 250px;
            right: 0;
            top: 70px;
            bottom: 0;
            padding-bottom: 30px;
            -webkit-transition: left 0.3s ease-in-out;
            transition: left 0.3s ease-in-out;
            background: #eef0fc;
            overflow: hidden;
        }

        .content-collapse {
            left: 65px;
        }

        .el-footer {
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 14px;
            color: #666;
        }
    }
</style>