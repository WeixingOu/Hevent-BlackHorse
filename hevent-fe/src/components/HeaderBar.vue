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
                <div class="btn-icon" @click="toggleFullScreen ">
                    <el-tooltip effect="dark" content="Full Screen" placement="bottom">
                        <template v-if="!isFullScreen">
                                <FullScreen class="btn-fullscreen"/>
                            </template>
                            <template v-else>
                                <FullScreen class="btn-fullscreen-close"/>
                            </template>
                    </el-tooltip>
                </div>

                <el-avatar class="user-avator" :size="30" :src="profileStore.profile?.userPic || avatar"  />
                
                <el-dropdown class="user-name" trigger="click" placement="bottom-end" @command="handleCommand">
                    
                    <span class="el-dropdown-link">
                        {{ profileStore.profile?.nickname }}

                        <el-icon class="el-icon--right" style="margin-left: 10px;">
                            <arrow-down />
                        </el-icon>
                    </span>

                    <template #dropdown>
                        <el-dropdown-menu placement="bottom-end">
                            <el-dropdown-item command="profile" :icon="User">Profile</el-dropdown-item>
                            <el-dropdown-item command="avatar" :icon="Crop">Avatar</el-dropdown-item>
                            <el-dropdown-item command="reset" :icon="EditPen">Reset</el-dropdown-item>
                            <el-dropdown-item command="logout" :icon="SwitchButton">Log Out</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
    import { User, Crop, EditPen, SwitchButton, Expand, Fold, FullScreen } from '@element-plus/icons-vue'
    import { onMounted, ref, onUnmounted } from 'vue';
    import { useSidebarStore } from '@/stores/sidebar';
    import { useProfileStore } from '@/stores/profile';
    import { getProfileService } from '@/apis/user';
    import { ElMessage, ElMessageBox } from 'element-plus';
    import { useRouter } from 'vue-router';
    import { useTokenStore } from '@/stores/token';
    import { logoutService } from '@/apis/auth';

    const avatar = '@/assets/default.png';

    const sidebar = useSidebarStore();
    const isFullScreen = ref(false);

    const collapseChage = () => {
        sidebar.handleCollapse();
    };

    onMounted(() => {
        if (document.body.clientWidth < 1500) {
            collapseChage();
        }

        document.addEventListener('fullscreenchange', onFullScreenChange);
    });

    onUnmounted(() => {
        document.removeEventListener('fullscreenchange', onFullScreenChange);
    });

    const onFullScreenChange = () => {
        isFullScreen.value = !!document.fullscreenElement;
    };

    const toggleFullScreen = () => {
        if (isFullScreen.value) {
            document.exitFullscreen();
        } else {
            document.body.requestFullscreen();
        }
    };

    const profileStore = useProfileStore();
    const tokenStore = useTokenStore();

    const getProfile = async () => {
        const res = await getProfileService();

        profileStore.setProfile(res.data)
    } 
    getProfile();

    const router = useRouter();

    const handleCommand = (command: unknown) => {
        if (command === 'logout') {
            ElMessageBox.confirm(
                'Are you sure you want to log out?',
                'REMINDER',
                {
                confirmButtonText: 'Confirm',
                cancelButtonText: 'Cancel',
                type: 'warning'
                }
            ).then(
                async () => {
                    await logoutService();

                    profileStore.removeProfile();
                    tokenStore.removeToken();
                    ElMessage.success("Logged out successfully");
                    await router.push('/login');
                }
            ).catch(()=> {
                ElMessage({
                    type: 'info',
                    message: 'Cancelled logout'
                })
            })
        } else {
            router.push('/user/' + command)
        }
    }


</script>

<style scoped lang="scss">
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

                .btn-fullscreen-close {
                    transform: rotate(45deg);
                    margin-right: 5px;
                    font-size: 24px;
                }

                .btn-fullscreen {
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