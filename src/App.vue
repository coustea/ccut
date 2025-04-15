<template>
  <v-app>
    <AppHeader v-if="authStore.user" />
    <AppAlert ref="alert" />
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useAuthStore } from '@/stores/auth';
import AppHeader from '@/components/AppHeader.vue';
import AppAlert from '@/components/AppAlert.vue';

const authStore = useAuthStore();
const alert = ref(null);

// 提供全局alert方法
const showAlert = (message, color = 'info') => {
  alert.value?.showAlert({ message, color });
};

// 暴露给全局
defineExpose({ showAlert });

onMounted(() => {
  // 初始化时检查本地是否有token
  if (authStore.token) {
    console.log('User is logged in');
  }
});
</script>