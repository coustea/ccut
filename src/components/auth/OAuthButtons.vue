<template>
  <div class="text-center">
    <p class="text-caption text-medium-emphasis mb-4">{{ $t('auth.orLoginWith') }}</p>

    <div class="d-flex justify-center gap-4">
      <v-btn
          v-for="provider in providers"
          :key="provider"
          variant="outlined"
          :color="providerColors[provider]"
          @click="loginWith(provider)"
          class="oauth-btn"
      >
        <v-icon :icon="providerIcons[provider]" start></v-icon>
        {{ provider }}
      </v-btn>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue';
import { useAuthStore } from '@/stores/auth';
import { useSnackbar } from '@/composables/useSnackbar';

const authStore = useAuthStore();
const { showSnackbar } = useSnackbar();

const providers = ['Google', 'GitHub', 'Microsoft'];

const providerIcons = {
  Google: 'mdi-google',
  GitHub: 'mdi-github',
  Microsoft: 'mdi-microsoft'
};

const providerColors = {
  Google: 'red',
  GitHub: 'grey-darken-4',
  Microsoft: 'blue'
};

const loginWith = (provider) => {
  const lowerProvider = provider.toLowerCase();
  const width = 600;
  const height = 600;
  const left = window.screen.width / 2 - width / 2;
  const top = window.screen.height / 2 - height / 2;

  const authWindow = window.open(
      `${import.meta.env.VITE_API_BASE_URL}/auth/${lowerProvider}`,
      `${provider}Login`,
      `width=${width},height=${height},left=${left},top=${top}`
  );

  const handleMessage = async (event) => {
    if (event.origin !== window.location.origin) return;
    if (event.data.provider === lowerProvider && event.data.code) {
      try {
        await authStore.oauthLogin(lowerProvider, event.data.code);
        window.removeEventListener('message', handleMessage);
        authWindow.close();
      } catch (error) {
        showSnackbar({ message: error.message || 'OAuth login failed', color: 'error' });
      }
    }
  };

  window.addEventListener('message', handleMessage);
};
</script>