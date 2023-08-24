
import VueRouter from 'vue-router';

const routes = [
    {
        path: '/',
        name: 'login',
        component: () => import('../components/Login')
    },
    {
        path: '/Index',
        name: 'index',
        component: () => import('../components/Index'),
        meta: {
            requireAuth: true
        },
        children: [
            {
                path: '/Calendar',
                name: 'calendar',
                meta: {
                    title: '首页',

                },
                component: () => import('../components/Calendar.vue')
            }, {
                path: '/Home',
                name: 'home',
                meta: {
                    title: '首页'
                },
                component: () => import('../components/Home.vue')
            },
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    routes
})

export function resetRouter() {
    router.matcher = new VueRouter({
        mode: 'history',
        routes: []
    }).matcher
}
const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(to) {
    return VueRouterPush.call(this, to).catch(err => err)
}

router.beforeEach((to, from, next) => {
    if (to.path == '/' || sessionStorage.getItem('CurUser')) {
        next();
    } else {
        alert('请重新登录');
        next('/');
    }

})


export default router;