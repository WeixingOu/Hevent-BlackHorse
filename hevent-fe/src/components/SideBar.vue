<template>
    <div class="sidebar">
        <el-menu active-text-color="#ffd04b" background-color="#232323" text-color="#fff" :collapse="sidebar.collapse"  class="sidebar-el-menu" router>
                <el-menu-item index="/article/category">
                    <el-icon>
                        <Management/>
                    </el-icon>

                    <span>Categories</span>
                </el-menu-item>

                <el-menu-item index="/article/manage">
                    <el-icon>
                        <Promotion/>
                    </el-icon>

                    <span>Management</span>
                </el-menu-item>

                <el-sub-menu index="/user">
                    <template #title>
                        <el-icon>
                            <UserFilled/>
                        </el-icon>

                        <span>User</span>
                    </template>

                    <el-menu-item index="/user/profile">
                        <el-icon>
                            <User/>
                        </el-icon>

                        <span>Profile</span>
                    </el-menu-item>

                    <el-menu-item index="/user/avatar">
                        <el-icon>
                            <Crop/>
                        </el-icon>
                        <span>Avatar</span>
                    </el-menu-item>

                    <el-menu-item index="/user/reset">
                        <el-icon>
                            <EditPen/>
                        </el-icon>
                        <span>Reset</span>
                    </el-menu-item>
                </el-sub-menu>
            </el-menu>
    </div>
</template>

<script setup lang="ts">
    import { useSidebarStore } from '@/stores/sidebar';

    const sidebar = useSidebarStore();
</script>

<style scoped lang="scss">
    .sidebar {
        display: block;
        position: absolute;
        left: 0;
        top: 70px;
        bottom: 0;
        overflow-y: scroll;
    }

    .sidebar::-webkit-scrollbar {
        width: 0;
    }

    .sidebar-el-menu:not(.el-menu--collapse) {
        width: 250px;
    }

    .sidebar-el-menu {
        min-height: 100%;
    }
</style>