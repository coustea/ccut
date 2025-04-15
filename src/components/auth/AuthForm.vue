<template>
  <v-form ref="form" @submit.prevent="submit">
    <v-text-field
        v-model="form.email"
        :label="$t('auth.email')"
        prepend-icon="mdi-email"
        type="email"
        :rules="[validators.required, validators.email]"
        required
    ></v-text-field>

    <v-text-field
        v-model="form.password"
        :label="$t('auth.password')"
        prepend-icon="mdi-lock"
        :type="showPassword ? 'text' : 'password'"
        :append-inner-icon="showPassword ? 'mdi-eye-off' : 'mdi-eye'"
        @click:append-inner="showPassword = !showPassword"
        :rules="[validators.required, validators.minLength(8)]"
        required
    ></v-text-field>

    <template v-if="mode === 'register'">
      <v-text-field
          v-model="form.confirmPassword"
          :label="$t('auth.confirmPassword')"
          prepend-icon="mdi-lock-check"
          type="password"
          :rules="[validators.required, passwordMatch]"
          required
      ></v-text-field>

      <v-text-field
          v-model="form.name"
          :label="$t('auth.name')"
          prepend-icon="mdi-account"
          :rules="[validators.required]"
          required
      ></v-text-field>
    </template>

    <v-radio-group v-model="form.role" row class="mt-4" :rules="[validators.required]">
      <v-radio :label="$t('roles.student')" value="student"></v-radio>
      <v-radio :label="$t('roles.teacher')" value="teacher"></v-radio>
      <v-radio :label="$t('roles.admin')" value="admin"></v-radio>
    </v-radio-group>

    <template v-if="mode === 'login'">
      <div class="d-flex justify-space-between align-center mb-4">
        <v-checkbox v-model="form.remember" :label="$t('auth.rememberMe')"></v-checkbox>
        <router-link :to="{ name: 'ForgotPassword' }" class="text-caption">
          {{ $t('auth.forgotPassword') }}
        </router-link>
      </div>
    </template>

    <ReCaptcha v-if="enableCaptcha" v-model="form.captcha" />

    <v-btn
        color="primary"
        type="submit"
        block
        :loading="loading"
        size="large"
        class="mt-2"
    >
      {{ $t(`auth.${mode}`) }}
    </v-btn>

    <OAuthButtons v-if="mode === 'login'" class="mt-4" />
  </v-form>
</template>

<script setup>
import { ref, computed } from 'vue';
import { useI18n } from 'vue-i18n';
import validators from '@/utils/validators';
import ReCaptcha from './ReCaptcha.vue';
import OAuthButtons from './OAuthButtons.vue';

const props = defineProps({
  mode: {
    type: String,
    required: true,
    validator: value => ['login', 'register'].includes(value)
  },
  loading: Boolean,
  enableCaptcha: Boolean
});

const emit = defineEmits(['submit']);

const { t } = useI18n();
const form = ref({
  email: '',
  password: '',
  confirmPassword: '',
  name: '',
  role: 'student',
  remember: false,
  captcha: ''
});
const showPassword = ref(false);

const passwordMatch = () =>
    form.value.password === form.value.confirmPassword || t('auth.passwordsNotMatch');

const submit = async () => {
  const { valid } = await form.value.validate();
  if (valid) {
    emit('submit', form.value);
  }
};
</script>