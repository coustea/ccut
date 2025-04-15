<template>
  <v-snackbar
      v-model="show"
      :color="color"
      :timeout="timeout"
      location="bottom right"
  >
    {{ message }}

    <template v-slot:actions>
      <v-btn
          variant="text"
          @click="show = false"
      >
        关闭
      </v-btn>
    </template>
  </v-snackbar>
</template>

<script setup>
import { ref } from 'vue';

const show = ref(false);
const message = ref('');
const color = ref('info');
const timeout = ref(3000);

const showAlert = (payload) => {
  message.value = payload.message;
  color.value = payload.color || 'info';
  timeout.value = payload.timeout || 3000;
  show.value = true;
};

defineExpose({ showAlert });
</script>