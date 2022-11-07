import Vue from 'vue'
import Manager from '../views/Manager.vue'
import VueRouter from "vue-router"

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Manager',
        component: Manager,
        redirect: '/index',
        children:[
            {
                path: 'index',
                name: 'Index',
                component: () => import('../views/Index')
            },
            {
                path: 'Manager/user',
                name: 'User',
                component: () => import('../views/User')
            },
            {
                path: 'Manager/blog',
                name: 'Blog',
                component: () => import('../views/Blog')
            },
            {
                path: 'Manager/category',
                name: 'Category',
                component: () => import('../views/Category')
            },
            {
                // 定义:id占位符，根据博客id来指定动态路由
                path: 'detail/:id',
                name: 'BlogDetail',
                component: () => import('../views/BlogDetail')
            }
        ]
    },
]

//history 模式不仅可以在url里放参数，还可以将数据存放在一个特定的对象中。
const router = new VueRouter({
    mode:"history",
    base: process.env.BASE_URL,
    routes
})

export default router
