import axios from 'axios';
import { useAuthStore } from '@/stores/auth';

const api = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL || '/api',
    timeout: 10000,
});

// 请求拦截器
api.interceptors.request.use(config => {
    const authStore = useAuthStore();
    if (authStore.token) {
        config.headers.Authorization = `Bearer ${authStore.token}`;
    }
    return config;
}, error => {
    return Promise.reject(error);
});

// 响应拦截器
api.interceptors.response.use(response => {
    return response.data;
}, error => {
    if (error.response?.status === 401) {
        const authStore = useAuthStore();
        authStore.logout();
        // 可以在这里跳转到登录页面
    }
    return Promise.reject(error.response?.data || error.message);
});

export default api;