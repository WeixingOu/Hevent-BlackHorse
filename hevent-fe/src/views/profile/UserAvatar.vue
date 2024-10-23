<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>Change Avatar</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-upload ref="uploadRef" class="avatar-uploader" :show-file-list="false" :auto-upload="true"
                    action="/api/v1/upload" name="file" :headers="uploadHeaders"
                    :data="{ publicId: profileStore.profile?.id }"
                    @success="uploadSuccess">
                    <el-image v-if="imgUrl" :src="imgUrl" class="avatar" fit="cover" />
                    <el-image v-else :src="avatar" width="278" fit="cover"/>
                </el-upload>
                <br />
                <el-button type="primary" :icon="Plus" size="large"
                    @click="uploadRef.$el.querySelector('input').click()">
                    Choose Image
                </el-button>
                <el-button type="success" :icon="Upload" size="large" @click="updateAvatar">
                    Upload Avatar
                </el-button>
            </el-col>
        </el-row>
    </el-card>
</template>

<script lang="ts" setup>
    import { Plus, Upload } from '@element-plus/icons-vue'
    import { ref } from 'vue'
    import { ElMessage } from "element-plus";
    import { useProfileStore } from '@/stores/profile';
    import { useTokenStore } from '@/stores/token';
    import type { AxiosResponse } from 'axios';
    import { updateProfileAvatarService } from '@/apis/user';
    import type { Profile } from '@/types';
    import avatar from '@/assets/img/default.png';

    const uploadRef = ref();

    const tokenStore = useTokenStore();

    const uploadHeaders = {
        Authorization: `Bearer ${tokenStore.token}`
    };

    const profileStore = useProfileStore();

    const imgUrl = ref(profileStore.profile?.userPic);

    const uploadSuccess = (res: AxiosResponse) => {
        imgUrl.value = res.data.coverImgUrl;
        ElMessage.success("Image uploaded successfully");
    }

    const updateAvatar = async () => {
        await updateProfileAvatarService(imgUrl.value || '');

        ElMessage.success("Avatar updated successfully");

        profileStore.setProfile({
            ...profileStore.profile,
            userPic: imgUrl.value
        } as Profile);
    }
</script>

<style lang="scss" scoped>
    .avatar-uploader {
        :deep {
            .avatar {
                width: 278px;
                height: 278px;
                display: block;
            }

            .el-upload {
                border: 1px dashed var(--el-border-color);
                border-radius: 6px;
                cursor: pointer;
                position: relative;
                overflow: hidden;
                transition: var(--el-transition-duration-fast);
            }

            .el-upload:hover {
                border-color: var(--el-color-primary);
            }

            .el-icon.avatar-uploader-icon {
                font-size: 28px;
                color: #8c939d;
                width: 278px;
                height: 278px;
                text-align: center;
            }
        }
    }
</style>