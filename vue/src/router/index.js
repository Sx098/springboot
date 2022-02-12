import VueRouter from "vue-router";
import Login from '../components/Login'
import Home from '../components/Home'
import Menu from '../components/Menu'
import verificationcode from '../components/verificationcode'
import Chat from '../components/Chat'
import Too from '../components/Too'
const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes:[
        {
            path:'/Home',
            component:Home
        },
        {
          path: '/login',
          // route level code-splitting
          // this generates a separate chunk (about.[hash].js) for this route
          // which is lazy-loaded when the route is visited.
          component: Login
        },
        {
            path:'/Menu',
            component:Menu
        },
        {
            path:'/verificationcode',
            component:verificationcode
        },
        {
            path:'/Chat',
            component:Chat
        },
        {
            path:'/Too',
            component:Too
        }
    ]
})
export default router