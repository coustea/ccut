import api from './index';

export default {
    login(credentials) {
        return api.post('/auth/login', credentials);
    },
    register(userData) {
        return api.post('/auth/register', userData);
    },
    forgotPassword(email) {
        return api.post('/auth/forgot-password', { email });
    },
    resetPassword(data) {
        return api.post('/auth/reset-password', data);
    },
    getMe() {
        return api.get('/auth/me');
    },
    // 第三方登录
    oauthLogin(provider, code) {
        return api.post(`/auth/${provider}`, { code });
    }
};