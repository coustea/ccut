import { defineStore } from 'pinia';
import mockAuth from '@/api/mockAuth';

export const useAuthStore = defineStore('auth', {
    state: () => ({
        user: null,
        token: localStorage.getItem('token') || null
    }),
    actions: {
        async login(credentials) {
            const response = await mockAuth.login(credentials);
            this.user = response.user;
            this.token = response.token;
            localStorage.setItem('token', this.token);
        },
        async register(userData) {
            const response = await mockAuth.register(userData);
            this.user = response.user;
            this.token = response.token;
            localStorage.setItem('token', this.token);
        },
        logout() {
            this.user = null;
            this.token = null;
            localStorage.removeItem('token');
        }
    }
});