<template>
    <el-row class="login-page">
        <el-col :span="12" class="bg">
        </el-col>
        <el-col :span="6" :offset="3" class="form">
            <el-form ref="registerForm" size="large" autocomplete="off" v-if="isRegister" :rules="rules" :model="registerData" @submit.prevent="register">
                <el-form-item>
                    <h1>Register</h1>
                </el-form-item>

                <el-form-item prop="username">
                    <el-input 
                        :prefix-icon="User"
                        placeholder="Please enter your username" 
                        v-model="registerData.username"
                    ></el-input>
                </el-form-item>

                <el-form-item prop="password">
                    <el-input 
                        :prefix-icon="Lock"
                        type="password" 
                        placeholder="Please enter your password" 
                        v-model="registerData.password"
                    ></el-input>
                </el-form-item>

                <el-form-item prop="rePassword">
                    <el-input 
                        :prefix-icon="Lock" 
                        type="password" 
                        placeholder="Please re-enter your password" 
                        v-model="registerData.rePassword"
                    ></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click="register">
                        Register
                    </el-button>
                </el-form-item>

                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isRegister = false">
                        ← Return
                    </el-link>
                </el-form-item>
            </el-form>
            
            <el-form ref="loginForm" size="large" v-else :model="loginData" :rules="rules">
                <el-form-item>
                    <h1>Login</h1>
                </el-form-item>

                <el-form-item prop="username">
                    <el-input :prefix-icon="User" placeholder="Enter your username" v-model="loginData.username"></el-input>
                </el-form-item>

                <el-form-item prop="password">
                    <el-input name="password" :prefix-icon="Lock" type="password" placeholder="Enter your password" v-model="loginData.password"></el-input>
                </el-form-item>

                <el-form-item class="flex">
                    <div class="flex">
                        <el-checkbox>Remember Me</el-checkbox>
                        <el-link type="primary" :underline="false">Forgot password?</el-link>
                    </div>
                </el-form-item>

                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click="login">
                        Login
                    </el-button>
                </el-form-item>

                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isRegister = true" >
                        Register →
                    </el-link>
                </el-form-item>
            </el-form>
        </el-col>
    </el-row>
</template>

<script setup lang="ts">
    import { Lock, User } from "@element-plus/icons-vue";
    import { reactive, ref } from "vue";
    import type { LoginData, RegisterData} from '@/types';
    import { ElMessage, type FormRules, type FormInstance } from "element-plus";
    import {type InternalRuleItem } from "async-validator";
    import { loginService, registerService } from '../apis/auth';
    import { useTokenStore } from "@/stores/token";
    import { useRouter } from 'vue-router';

    const isRegister = ref(false);

    const registerData = reactive<RegisterData>({
        username: '',
        password: '',
        rePassword: ''
    });

    const loginData = reactive<LoginData>({
        username: '',
        password: '',
    })

    const notBlank =  (rule: InternalRuleItem, value: string, callback: (error?: Error | string) => void) => {
            if (value.trim() === '') {
                callback(new Error('The field must not be blank'));
            } else {
                callback();
            }
        };

    const rules: FormRules = {
        username: [
            {
                required: true,
                message: 'Please enter your username',
                trigger: 'blur',
            },
            {
                min: 3, max: 20, message: 'Username must be between 3 and 20 characters', 
                trigger: 'blur'
            },
            {
                validator: notBlank, trigger: 'blur'
            }
        ],

        password: [
            {
                required: true,
                message: 'Please enter your password',
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
                validator: (rule, value, callback) => {
                    if (value !== registerData.password) {
                        callback(new Error('The two passwords do not match'));
                    } else {
                        callback();
                    }
                }
            },
            {
                validator: notBlank, trigger: ['blur', 'change']
            }
        ]
    }

    const registerForm = ref<FormInstance | null>();

    const register = async () => {
        if (!registerForm.value) return;

        registerForm.value.validate(async (valid: boolean) =>{
            if (valid) {
                const result = await registerService(registerData); 
        
                ElMessage.success(result.data.message ? result.data.message : 'Registered successfully');

                isRegister.value = false;
            }
        })
    }

    
    const router = useRouter();
    const tokenStore = useTokenStore();
    const loginForm = ref<FormInstance | null>();

    const login = async () => {
        if (!loginForm.value) return;

        loginForm.value.validate(async (valid: boolean) =>{
            if (valid) {
                const result =  await loginService(loginData);

                ElMessage.success('Logged successfully');

                tokenStore.setToken(result.data.jwt);

                router.push('/');
            }
        })
    }
</script>

<style lang="scss" scoped>
    .login-page {
        height: 100vh;
        background-color: #fff;

        .bg {
            background: url('@/assets/img/logo.png') no-repeat 60% center / 500px auto,
            url('@/assets/img/login_bg.jpg') no-repeat center / cover;
            border-radius: 0 20px 20px 0;
        }

        .icon-container {
            display: flex;
            align-items: center; 
            justify-content: center;
            height: 100%; 
        }

        .logo-text {
            color: white;
            font-size: 30px;
        }

        .centered-icon {
            font-size: 50px; 
            color: white;
        }

        .form {
            display: flex;
            flex-direction: column;
            justify-content: center;
            user-select: none;

            .title {
                margin: 0 auto;
            }

            .button {
                width: 100%;
            }

            .flex {
                width: 100%;
                display: flex;
                justify-content: space-between;
            }
        }
    }
</style>