<template>
  <v-container>
    <v-row>
      <v-col cols="12">
        <v-card>
          <v-card-title class="text-h5">
            <v-icon color="green" class="mr-2">mdi-teach</v-icon>
            教师面板
          </v-card-title>
          <v-divider></v-divider>
          <v-card-text>
            <p class="text-h6">您好，{{ authStore.user?.name }}老师！</p>
            <v-list lines="two" class="mt-4">
              <v-list-item
                  v-for="item in teacherFeatures"
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

const teacherFeatures = [
  {
    title: '课程管理',
    subtitle: '创建和管理您的课程',
    icon: 'mdi-book-edit'
  },
  {
    title: '作业批改',
    subtitle: '批改学生提交的作业',
    icon: 'mdi-file-check'
  },
  {
    title: '学生成绩',
    subtitle: '录入和管理学生成绩',
    icon: 'mdi-chart-bar'
  }
];

const logout = () => {
  authStore.logout();
  router.push('/auth');
};
</script>