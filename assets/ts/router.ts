import Router from 'vue-router'
import Vue from 'vue';
import routes from './routes'
import store from "~/store";
import axios, {UserApiResponse} from "~/http";
import {AxiosResponse} from "axios";

Vue.use(Router);

const router = new Router({
    mode: 'history',
    routes,
    base: '/'
});

router.beforeEach((to, from, next) => {
    if (to.name === 'login' || to.name === 'signup') {
        next();
        return;
    }

    axios.get('/api/user')
        .then((response: AxiosResponse<UserApiResponse>) => {
            store.commit('authenticate', {user: response.data.user})
        })
        .catch(() => {
            store.commit('logout');
            router.push({name: 'login'});
        })

    next();
})

export default router;
