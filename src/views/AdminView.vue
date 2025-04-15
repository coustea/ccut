<template>
  <v-container>
    <v-row>
      <v-col cols="12">
        <v-card>
          <v-card-title class="text-h5">
            <v-icon color="red" class="mr-2">mdi-shield-account</v-icon>
            管理员面板
          </v-card-title>
          <v-divider></v-divider>
          <v-card-text>
            <p class="text-h6">管理员 {{ authStore.user?.name }}，欢迎您！</p>
            <v-list lines="two" class="mt-4">
              <v-list-item
                  v-for="item in adminFeatures"
                  :key="item.title"
                  :prepend-icon="item.icon"
              >
                <v-list-item-title>{{ item.title }}</v-list-item-title>
                <v-list-item-subtitle>{{ item.subtitle }}</v-list-item-subtitle>
              </v-list-item>
            </v-list>
          </v-card-text>
          <v-card-actions>
            <v-btn color="error" variant="outlined" @click="logout">
              <v-icon start>mdi-logout</v-icon>
              退出登录
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth';
import { useRouter } from 'vue-router';

const authStore = useAuthStore();
const router = useRouter();

const adminFeatures = [
  {
    title: '用户管理',
    subtitle: '管理系统所有用户账户',
    icon: 'mdi-account-group'
  },
  {
    title: '系统设置',
    subtitle: '配置系统参数和选项',
    icon: 'mdi-cog'
  },
  {
    title: '数据统计',
    subtitle: '查看系统使用情况统计',
    icon: 'mdi-chart-pie'
  }
];

const logout = () => {
  authStore.logout();
  router.push('/auth');
};
</script>