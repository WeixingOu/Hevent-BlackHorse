<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>Profile</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-form :model="userInfo" :rules="rules" label-width="100px" size="large" ref="form">
                    <el-form-item label="Username">
                        <el-input v-model="userInfo.username" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="Nickname" prop="nickname">
                        <el-input v-model="userInfo.nickname"></el-input>
                    </el-form-item>
                    <el-form-item label="Email" prop="email">
                        <el-input v-model="userInfo.email"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="updateUserInfo">Submit</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>

<script lang="ts" setup>
    import { reactive, ref } from 'vue'
    import { ElMessage, type FormInstance, type FormRules } from "element-plus";
    import type { Profile } from '@/types';
    import { useProfileStore } from '@/stores/profile';
    import { updateProfileService } from '@/apis/user';

    const profileStore = useProfileStore();

    const userInfo = reactive<Profile>({
        id: profileStore.profile?.id || null,
        username: profileStore.profile?.username || '',
        nickname: profileStore.profile?.nickname || '',
        email: profileStore.profile?.email || '',
    });

    const rules: FormRules = {
        nickname: [
            { required: true, message: 'Please enter your username', trigger: 'blur' },
            {
                pattern: /^\S{2,10}$/,
                message: 'Nickname must be a non-empty string between 2 and 10 characters',
                trigger: 'blur'
            }
        ],
        email: [
            { required: true, message: 'Please enter your email', trigger: 'blur' },
            { type: 'email', message: 'The email format is incorrect', trigger: 'blur' }
        ]
    }

    const form = ref<FormInstance | null>();

    const updateUserInfo = async () => {
        if (!form.value) return;
        
        form.value.validate(async (valid: boolean) => {
            if (valid) {
                await updateProfileService(userInfo);

                ElMessage.success('Modified successfully');

                profileStore.setProfile({
                    ...profileStore.profile,
                    nickname: userInfo.nickname,
                    email: userInfo.email
                } as Profile);
            }
        })
    }
</script>