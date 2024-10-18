import { useTokenStore } from "@/stores/token";
import type { ErrorResponse } from "@/types";
import axios from "axios";
import type { AxiosInstance, AxiosError, AxiosResponse, InternalAxiosRequestConfig } from "axios";

import { ElMessage } from "element-plus";

const instance: AxiosInstance = axios.create({
    baseURL: '/api/v1',
    timeout: 5000
});

instance.interceptors.request.use(
    (config: InternalAxiosRequestConfig) => {
        const tokenStore = useTokenStore();

        if (tokenStore.token) {
            config.headers.Authorization = `Bearer ${tokenStore.token}`;
        }

        return config
    },

    (error: AxiosError) => {
        return Promise.reject(error)
    }
)

instance.interceptors.response.use(
    (response: AxiosResponse) => {
        if (response.status === 200) {
            return response.data
        }

        ElMessage.error(response.data.message ? response.data.message: 'Service exception')

        return Promise.reject(response.data)
    },

    (error: AxiosError<ErrorResponse>) => {
        if (error.status === 401) {
            ElMessage.error("Please login first")
        } else {
            ElMessage.error(error.response?.data?.message ?? 'Service exception');
        }
        return Promise.reject(error)
    }
)

export default instance;