import { createApp } from 'vue';
import { createPinia } from 'pinia';
import { createVuetify } from 'vuetify';
import * as components from 'vuetify/components';
import * as directives from 'vuetify/directives';
import '@mdi/font/css/materialdesignicons.css';
import 'vuetify/styles';
import App from './App.vue';
import router from './router';

const vuetify = createVuetify({
    components,
    directives,
    theme: {
        defaultTheme: 'light',
        themes: {
            light: {
                colors: {
                    primary: '#1867C0',
                    secondary: '#5CBBF6',
                }
            }
        }
    }
});

const app = createApp(App);

app.use(createPinia());
app.use(router);
app.use(vuetify);

// 挂载app
app.mount('#app');

// 使showAlert在组件外可用
window.showAlert = (message, color) => {
    app._instance?.exposed?.showAlert(message, color);
};