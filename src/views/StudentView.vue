<template>
  <v-container>
    <v-row>
      <v-col cols="12">
        <v-card>
          <v-card-title class="text-h5">
            <v-icon color="blue" class="mr-2">mdi-account-school</v-icon>
            学生面板
          </v-card-title>
          <v-divider></v-divider>
          <v-card-text>
            <p class="text-h6">欢迎回来，{{ authStore.user?.name }}同学！</p>
            <v-list lines="two" class="mt-4">
              <v-list-item
                  v-for="item in studentFeatures"
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

const studentFeatures = [
  {
    title: '我的课程',
    subtitle: '查看已选课程和学习进度',
    icon: 'mdi-book-open-variant'
  },
  {
    title: '作业提交',
    subtitle: '上传作业和查看作业反馈',
    icon: 'mdi-note-text'
  },
  {
    title: '成绩查询',
    subtitle: '查看各科考试成绩',
    icon: 'mdi-chart-line'
  }
];

const logout = () => {
  authStore.logout();
  router.push('/auth');
};
</script>