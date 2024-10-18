<template>
    <div class="header">

        <div class="header-left">
            <img class="logo" src="../assets/img/logo2.png" alt="" />

            <div class="collapse-btn" @click="collapseChage">
                <el-icon v-if="sidebar.collapse">
                    <Expand />
                </el-icon>
                <el-icon v-else>
                    <Fold />
                </el-icon>
            </div>
        </div>

        <div class="header-right">
            <div class="header-user-con">
                <el-avatar class="user-avator" :size="30" />
                
                <el-dropdown class="user-name" trigger="click" placement="bottom-end">
                    
                    <span class="el-dropdown-link">
                        {{ '' }}
                        <el-icon class="el-icon--right">
                            <arrow-down />
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
            </div>
        </div>
    </div>

</template>

<script setup lang="ts">
import { User, Crop, EditPen, SwitchButton } from '@element-plus/icons-vue'
import { onMounted } from 'vue';
import { useSidebarStore } from '@/stores/sidebar';

const sidebar = useSidebarStore();

const collapseChage = () => {
    sidebar.handleCollapse();
};

onMounted(() => {
    if (document.body.clientWidth < 1500) {
        collapseChage();
    }
});


</script>

<style lang="scss" scoped>
.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    box-sizing: border-box;
    width: 100%;
    height: 70px;
    color: var(--header-text-color);
    background-color: var(--header-bg-color);
    border-bottom: 1px solid #ddd;

    .header-left {
        display: flex;
        align-items: center;
        padding-left: 20px;
        height: 100%;

        .logo {
            margin-top: 35px;
            width: 225px;
        }

        .collapse-btn {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100%;
            padding: 0 10px;
            cursor: pointer;
            opacity: 0.8;
            font-size: 22px;

            &:hover {
                opacity: 1;
            }
        }
    }

    .header-right {
        float: right;
        padding-right: 50px;

        .header-user-con {
            display: flex;
            height: 70px;
            align-items: center;

            .btn-icon {
                position: relative;
                width: 30px;
                height: 30px;
                text-align: center;
                cursor: pointer;
                display: flex;
                align-items: center;
                color: var(--header-text-color);
                margin: 0 5px;
                font-size: 20px;

                .btn-bell-badge {
                    position: absolute;
                    right: 4px;
                    top: 0px;
                    width: 8px;
                    height: 8px;
                    border-radius: 4px;
                    background: #f56c6c;
                    color: var(--header-text-color);
                }
            }

            .btn-fullscreen {
                transform: rotate(45deg);
                margin-right: 5px;
                font-size: 24px;
            }

            .user-avator {
                margin: 0 10px 0 20px;
            }

            .el-dropdown-link {
                color: var(--header-text-color);
                cursor: pointer;
                display: flex;
                align-items: center;
            }
        }
    }
    
}
</style>