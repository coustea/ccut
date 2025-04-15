import { createRouter, createWebHistory } from 'vue-router';
import AuthPage from '@/views/AuthPage.vue';
import { useAuthStore } from '@/stores/auth';

const routes = [
    {
        path: '/',
        redirect: '/auth'
    },
    {
        path: '/auth',
        name: 'Auth',
        component: AuthPage,
        meta: { hideHeader: true }
    },
    {
        path: '/student',
        name: 'Student',
        component: () => import('@/views/StudentView.vue'),
        meta: { requiresAuth: true, role: 'student' }
    },
    {
        path: '/teacher',
        name: 'Teacher',
        component: () => import('@/views/TeacherView.vue'),
        meta: { requiresAuth: true, role: 'teacher' }
    },
    {
        path: '/admin',
        name: 'Admin',
        component: () => import('@/views/AdminView.vue'),
        meta: { requiresAuth: true, role: 'admin' }
    },
    {
        path: '/:pathMatch(.*)*',
        redirect: '/auth'
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

router.beforeEach((to, from, next) => {
    const authStore = useAuthStore();

    // 检查是否需要认证
    if (to.meta.requiresAuth && !authStore.token) {
        showAlert('请先登录', 'warning');
        return next('/auth');
    }

    // 检查角色权限
    if (to.meta.role && authStore.user?.role !== to.meta.role) {
        showAlert('无权访问该页面', 'error');
        return next(`/${authStore.user?.role || 'auth'}`);
    }

    next();
});

export default router;