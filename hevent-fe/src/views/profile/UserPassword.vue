<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>重置密码</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-form :model="passwordData" :rules="rules" ref="form" label-width="200px" size="large">
                    <el-form-item label="Old Password" prop="currentPassword">
                        <el-input v-model="passwordData.currentPassword" type="password" placeholder="Please enter the old password"></el-input>
                    </el-form-item>

                    <el-form-item label="New Password" prop="newPassword">
                        <el-input v-model="passwordData.newPassword" type="password" placeholder="Please enter the new password"></el-input>
                    </el-form-item>

                    <el-form-item label="Confirm New Password" prop="rePassword">
                        <el-input v-model="passwordData.rePassword" type="password" placeholder="Please confirm the new password"></el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="updatePwdData">Submit</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>

<script lang="ts" setup>
    import { ref } from 'vue'
    import {  } from "@/apis/auth";
    import { ElMessage, type FormInstance, type FormRules } from "element-plus";
    import {type InternalRuleItem } from "async-validator";
    import type { PasswordData } from '@/types';
    import { updatedPwdUpdateService } from '@/apis/user';

    const passwordData = ref<PasswordData>({
        currentPassword: '',
        newPassword: '',
        rePassword: ''
    });

    const form = ref<FormInstance | null>();

    const notBlank =  (rule: InternalRuleItem, value: string, callback: (error?: Error | string) => void) => {
        if (value.trim() === '') {
            callback(new Error('The field must not be blank'));
        } else {
            callback();
        }
    };

    const rules: FormRules = {
        currentPassword: [
            {
                required: true,
                message: 'Please enter the old password',
                trigger: 'blur',
            },
            {
                min: 5, max: 16, message: 'Password must be between 5 and 16 characters',
                trigger: 'blur'
            },
            {
                validator: notBlank, trigger: 'blur'
            }
        ],
        newPassword: [
            {
                required: true,
                message: 'Please enter the new password',
                trigger: 'blur',
            },
            {
                min: 5, max: 16, message: 'Password must be between 5 and 16 characters',
                trigger: 'blur'
            },
            {
                validator: notBlank, trigger: 'blur'
            }
        ],
        rePassword: [
            {
                required: true,
                message: 'Please confirm your password',
                trigger: 'blur',
            },
            {
                min: 5, max: 16, message: 'Password must be between 5 and 16 characters',
                trigger: 'blur'
            },
            {
                validator: (rule, value, callback) => {
                    if (value !== passwordData.value.newPassword) {
                        callback(new Error('The two passwords do not match'));
                    } else {
                        callback();
                    }
                }
            },
            {
                validator: notBlank, trigger: 'blur'
            }
        ]
    }

    const updatePwdData = async () => {
        if (!form.value) return;

        form.value.validate(async (valid: boolean) => {
            if (valid) {
                await updatedPwdUpdateService(passwordData.value);
                ElMessage.success('Password updated successfully');
                passwordData.value = {
                    currentPassword: '',
                    newPassword: '',
                    rePassword: ''
                }
            }
        })
    }
</script>