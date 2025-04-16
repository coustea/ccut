import axios from 'axios';

const axiosInstance = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL, // 从环境变量获取
    timeout: 10000
});

// 请求拦截器 - 添加token
axiosInstance.interceptors.request.use(config => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

// 响应拦截器 - 错误处理
axiosInstance.interceptors.response.use(
    response => response.data,
    error => {
        if (error.response?.status === 401) {
            // token过期时清除存储
            localStorage.removeItem('token');
            window.location.href = '/login';
        }
        return Promise.reject(error.response?.data || error.message);
    }
);

export default axiosInstance;