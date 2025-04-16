<template>
  <v-app>
    <v-main class="auth-background">
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="6" lg="4">
            <v-card class="elevation-12" :loading="loading">
              <v-toolbar color="primary" dark flat>
                <v-toolbar-title>{{ isLogin ? '登录' : '注册' }}</v-toolbar-title>
                <v-spacer></v-spacer>
                <v-btn icon @click="toggleMode">
                  <v-icon>{{ isLogin ? 'mdi-account-plus' : 'mdi-login' }}</v-icon>
                </v-btn>
              </v-toolbar>

              <v-card-text>
                <v-form @submit.prevent="submit">
                <!-- 学号登录 -->
                  <v-text-field
                      v-model="form.studentId"
                      label="学号"
                      name="studentId"
                      prepend-icon="mdi-account"
                      type="text"
                      required
                  ></v-text-field>

                  <v-text-field
                      v-model="form.password"
                      label="密码"
                      name="password"
                      prepend-icon="mdi-lock"
                      type="password"
                      required
                  ></v-text-field>

                  <template v-if="!isLogin">
                    <v-text-field
                        v-model="form.confirmPassword"
                        label="确认密码"
                        name="confirmPassword"
                        prepend-icon="mdi-lock-check"
                        type="password"
                        required
                    ></v-text-field>

                    <v-text-field
                        v-model="form.name"
                        label="姓名"
                        name="name"
                        prepend-icon="mdi-account"
                        type="text"
                        required
                    ></v-text-field>
                  </template>

                  <v-radio-group v-model="form.role" row class="mt-4">
                    <v-radio label="学生" value="student"></v-radio>
                    <v-radio label="教师" value="teacher"></v-radio>
                    <v-radio label="管理员" value="admin"></v-radio>
                  </v-radio-group>

                  <v-btn
                      color="primary"
                      type="submit"
                      block
                      :disabled="loading"
                  >
                    {{ isLogin ? '登录' : '注册' }}
                  </v-btn>
                </v-form>
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn text small color="primary" @click="toggleMode">
                  {{ isLogin ? '没有账号？立即注册' : '已有账号？立即登录' }}
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>

</template>

<script setup>
import { ref, reactive } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/auth';
import axios from '@/api/index.js';

const router = useRouter();
const authStore = useAuthStore();

const isLogin = ref(true);
const loading = ref(false);

const form = reactive({
  studentId: '',
  password: '',
  confirmPassword: '',
  name: '',
  role: 'student'
});

const toggleMode = () => {
  isLogin.value = !isLogin.value;
};

const submit = async () => {
  loading.value = true;

  try {
    if (isLogin.value) {
      console.log(1)
      // 登录请求
      const response = await axios.post('http://192.168.1.101:8080/login', {
        studentId: form.studentId,
        password: form.password,
        role: form.role
      }
      );
      console.log(response.data)
      // await axios.post('http://192.168.100.100:8000/login',form)
      // 保存token和用户信息
      authStore.setAuth(response.data.user, response.data.token);

      // 显示登录成功提示
      alert('登录成功');
    } else {
      // 注册请求
      if (form.password !== form.confirmPassword) {
        throw new Error('两次输入的密码不一致');
      }

      const response = await axios.post('/auth/register', {
        studentId: form.studentId,
        password: form.password,
        name: form.name,
        role: form.role
      });

      // 保存token和用户信息
      authStore.setAuth(response.data.user, response.data.token);

      // 显示注册成功提示
      alert('注册成功');
    }

    // 根据角色跳转到不同页面
    await router.push(`/${form.role}`);
  } catch (error) {
    // 更详细的错误处理
    const errorMessage = error.response?.data?.message || error.message || '操作失败';
    alert(errorMessage);

    // 可以在这里添加更友好的错误提示方式
    // 例如使用Toast或Snackbar组件
  } finally {
    loading.value = false;
  }
};
</script>

<style scoped>
.auth-background {
  background: url('@/assets/ccut.jpg') no-repeat center center fixed;
  background-size: cover;
  height: 100vh;
}

.v-card {
  opacity: 0.95;
}
</style>