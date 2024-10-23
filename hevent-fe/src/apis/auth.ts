import type { LoginData, RegisterData } from '@/types';
import request from '@/utils/request';

export const registerService = (registerData: RegisterData) => {
    return request({
        url: '/users',
        method: 'post',
        data: registerData
    });
}

export const loginService = (loginData: LoginData) => {
    return request({
        url: '/auth/login',
        method: 'post',
        data: loginData
    });
}

export const logoutService = () => {
    return request({
        url: '/auth/logout',
        method: 'post',
    });
}
