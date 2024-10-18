<script setup lang="ts">
import { CaretBottom, Crop, EditPen, Management, Promotion, SwitchButton, User, UserFilled } from '@element-plus/icons-vue';
import avatar from '@/assets/default.png'
</script>

<template>
    <el-container class="layout-container">

        <el-aside width="200px">
            <div class="el-aside__logo"></div>
            <el-menu active-text-color="#ffd04b" background-color="#232323" text-color="#fff" router>
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

                <el-sub-menu>
                    <template #title>
                        <el-icon>
                            <UserFilled/>
                        </el-icon>

                        <span>User</span>
                    </template>

                    <el-menu-item index="/user/info">
                        <el-icon>
                            <User/>
                        </el-icon>

                        <span>Information</span>
                    </el-menu-item>

                    <el-menu-item index="/user/avatar">
                        <el-icon>
                            <Crop/>
                        </el-icon>
                        <span>Change Avatar</span>
                    </el-menu-item>

                    <el-menu-item index="/user/resetPassword">
                        <el-icon>
                            <EditPen/>
                        </el-icon>
                        <span>Reset Password</span>
                    </el-menu-item>
                </el-sub-menu>
            </el-menu>
        </el-aside>

        <el-container>
            <el-header>
                <div>User: <strong>{{  }}</strong></div>

                <el-dropdown placement="bottom-end">
                    <span class="el-dropdown__box">
                        <el-avatar :src="avatar"/>
                        <el-icon>
                            <CaretBottom/>
                        </el-icon>
                    </span>

                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item command="info" :icon="User">Information</el-dropdown-item>
                            <el-dropdown-item command="avatar" :icon="Crop">Change Avatar</el-dropdown-item>
                            <el-dropdown-item command="resetPassword" :icon="EditPen">Reset Password</el-dropdown-item>
                            <el-dropdown-item command="logout" :icon="SwitchButton">Log Out</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </el-header>

            <el-main>
                <router-view/>
            </el-main>

        <el-footer>Hevent ©2024 Created by Weixing Ou</el-footer>
        </el-container>

    </el-container>
</template>

<style lang="scss" scoped>
    .layout-container {
        height: 100vh;

        .el-aside {
            background-color: #232323;

            &__logo {
                height: 120px;
                background: url('@/assets/logo.png') no-repeat center / 200px auto;
            }

            .el-menu {
                border-right: none;
            }
        }

    .el-header {
        background-color: #fff;
        display: flex;
        align-items: center;
        justify-content: space-between;

        .el-dropdown__box {
            display: flex;
            align-items: center;

            .el-icon {
                color: #999;
                margin-left: 10px;
            }

            &:active,
            &:focus {
                outline: none;
            }
        }
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