import Vue from 'vue'
import router from './router'
import store from './store'
import './theme'
import App from '@/layout/App.vue'

new Vue({
    el: '#app',
    render: r => r(App),
    router,
    store
});
