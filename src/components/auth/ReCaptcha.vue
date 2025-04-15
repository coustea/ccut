<template>
  <div class="my-4">
    <vue-recaptcha
        v-if="siteKey"
        ref="recaptcha"
        :sitekey="siteKey"
        :loadRecaptchaScript="true"
        size="normal"
        theme="light"
        @verify="handleVerify"
        @expire="handleExpire"
        @error="handleError"
    ></vue-recaptcha>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import VueRecaptcha from 'vue3-recaptcha2';

const props = defineProps({
  modelValue: String
});

const emit = defineEmits(['update:modelValue']);

const siteKey = ref(import.meta.env.VITE_RECAPTCHA_SITE_KEY);
const recaptcha = ref(null);

const handleVerify = (response) => {
  emit('update:modelValue', response);
};

const handleExpire = () => {
  emit('update:modelValue', '');
};

const handleError = (error) => {
  console.error('reCAPTCHA error:', error);
  emit('update:modelValue', '');
};

const reset = () => {
  recaptcha.value?.reset();
};

defineExpose({ reset });
</script>