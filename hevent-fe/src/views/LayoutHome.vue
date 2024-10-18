<template>
    <div class="wrapper">
        <v-header />

        <div class="content-box" :class="{ 'content-collapse': sidebar.collapse }"></div>
    </div>
</template>
<script setup lang="ts">
import { useSidebarStore } from '@/stores/sidebar';
import vHeader from '@/components/HeaderBar.vue';


const sidebar = useSidebarStore();
</script>

<style>
    .wrapper {
        height: 100vh;
        overflow: hidden;
    }
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

    .content {
        width: auto;
        height: 100%;
        padding: 20px;
        overflow-y: scroll;
        box-sizing: border-box;
    }

    .content::-webkit-scrollbar {
        width: 0;
    }

    .content-collapse {
        left: 65px;
    }
</style>