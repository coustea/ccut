import axios from '@/api/index.js'; // 导入配置好的axios实例

export default {
    async login(credentials) {
        const response = await axios.post(' 192.168.1.101:8080/login', credentials);
        return response.data; // 假设后端返回 { user, token }
    },

    async register(userData) {
        const response = await axios.post('/auth/register', userData);
        return response.data;
    },

    async logout() {
        await axios.post('/auth/logout');
    },

    async getUserInfo() {
        const response = await axios.get('/auth/me');
        return response.data;
    }
};