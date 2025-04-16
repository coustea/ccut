import { createRouter, createWebHistory } from 'vue-router';
import { useAuthStore } from '@/stores/auth';

const routes = [
    {
        path: '/',
        redirect: () => {
            const authStore = useAuthStore();
            // 根据用户角色动态重定向
            return authStore.user?.role ? `/${authStore.user.role}` : '/auth';
        }
    },
    {
        path: '/auth',
        name: 'Auth',
        component: () => import('@/views/AuthPage.vue'),
        meta: {
            hideHeader: true,
            guestOnly: true // 新增：仅允许未登录用户访问
        }
    },
    {
        path: '/student',
        name: 'Student',
        component: () => import('@/views/StudentView.vue'),
        meta: {
            requiresAuth: true,
            role: 'student',
            title: '学生面板' // 新增：路由标题
        }
    },
    {
        path: '/teacher',
        name: 'Teacher',
        component: () => import('@/views/TeacherView.vue'),
        meta: {
            requiresAuth: true,
            role: 'teacher',
            title: '教师面板'
        }
    },
    {
        path: '/admin',
        name: 'Admin',
        component: () => import('@/views/AdminView.vue'),
        meta: {
            requiresAuth: true,
            role: 'admin',
            title: '管理员面板'
        }
    },
    {
        path: '/:pathMatch(.*)*',
        redirect: '/'
    }
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
    // 新增：路由切换时滚动到顶部
    scrollBehavior(to, from, savedPosition) {
        return savedPosition || { top: 0 };
    }
});

// 增强的路由守卫
router.beforeEach(async (to, from, next) => {
    const authStore = useAuthStore();

    // 1. 如果token存在但用户数据未加载，先获取用户信息
    if (authStore.token && !authStore.user) {
        try {
            await authStore.fetchUser();
        } catch (error) {
            await authStore.logout();
            return next('/auth');
        }
    }

    // 2. 检查是否仅允许游客访问（如登录/注册页）
    if (to.meta.guestOnly && authStore.token) {
        return next('/');
    }

    // 3. 检查是否需要认证
    if (to.meta.requiresAuth && !authStore.token) {
        // 存储原始目标路径，便于登录后跳转
        authStore.returnUrl = to.fullPath;
        showAlert('请先登录', 'warning');
        return next('/auth');
    }

    // 4. 检查角色权限
    if (to.meta.role && authStore.user?.role !== to.meta.role) {
        showAlert('无权访问该页面', 'error');
        return next(`/${authStore.user?.role || 'auth'}`);
    }

    // 5. 设置页面标题
    if (to.meta.title) {
        document.title = `${to.meta.title} - 校园管理系统`;
    }

    next();
});

export default router;