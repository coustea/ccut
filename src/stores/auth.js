import { defineStore } from 'pinia';
import authApi from '@/api/auth';

export const useAuthStore = defineStore('auth', {
    state: () => ({
        user: null,
        token: localStorage.getItem('token') || null
    }),

    actions: {
        async login(credentials) {
            const { user, token } = await authApi.login(credentials);
            this.setAuth(user, token);
        },

        async register(userData) {
            const { user, token } = await authApi.register(userData);
            this.setAuth(user, token);
        },

        async logout() {
            await authApi.logout();
            this.clearAuth();
        },

        async fetchUser() {
            if (this.token) {
                this.user = await authApi.getUserInfo();
            }
        },

        // 辅助方法
        setAuth(user, token) {
            this.user = user;
            this.token = token;
            localStorage.setItem('token', token);
        },

        clearAuth() {
            this.user = null;
            this.token = null;
            localStorage.removeItem('token');
        }
    }
});