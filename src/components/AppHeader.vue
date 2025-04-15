<template>
  <v-app-bar color="primary" density="compact">
    <template v-slot:prepend>
      <v-app-bar-nav-icon></v-app-bar-nav-icon>
    </template>

    <v-app-bar-title>校园管理系统</v-app-bar-title>

    <template v-slot:append>
      <v-btn v-if="authStore.user" @click="logout">
        <v-icon start>mdi-logout</v-icon>
        退出
      </v-btn>
    </template>
  </v-app-bar>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth';
import { useRouter } from 'vue-router';

const authStore = useAuthStore();
const router = useRouter();

const logout = () => {
  authStore.logout();
  router.push('/auth');
};
</script>