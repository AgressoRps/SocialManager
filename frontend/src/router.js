import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Service from '@/components/Service'
import Bootstrap from '@/components/Bootstrap'
import User from '@/components/User'
import Login from '@/components/Login'
import Protected from '@/components/Protected'

import store from './store'

Vue.use(Router);

const router = new Router({
    mode: 'history', // uris without hashes #, see https://router.vuejs.org/guide/essentials/history-mode.html#html5-history-mode
    routes: [
        { path: '/', name: 'Index', component: Index },
        { path: '/user', name: 'User', component: User},
        //{ path: '/login', component: Login },
        /*{
            path: '/protected',
            component: Protected,
            meta: {
                requiresAuth: true
            }
        },*/

        // otherwise redirect to home
        { path: '*', redirect: '/' }
    ]
});

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // this route requires auth, check if logged in
        // if not, redirect to login page.
        if (!store.getters.isLoggedIn) {
            next({
                path: '/login'
            })
        } else {
            next();
        }
    } else {
        next(); // make sure to always call next()!
    }
});

export default router;