import {RouteConfig} from "vue-router";
const Login = () => import(/* webpackChunkName: "auth" */ '@/pages/Login.vue');
const Signup = () => import(/* webpackChunkName: "auth" */ '@/pages/Signup.vue');
const MainLayout = () => import(/* webpackChunkName: "main" */ '@/layout/MainLayout.vue');
const Index = () => import(/* webpackChunkName: "main" */ '@/pages/Index.vue');
const Products = () => import(/* webpackChunkName: "main" */ '@/pages/Products.vue');
const CreateProduct = () => import(/* webpackChunkName: "main" */ '@/pages/CreateProduct.vue');
const UpdateProduct = () => import(/* webpackChunkName: "main" */ '@/pages/UpdateProduct.vue');
const Users = () => import(/* webpackChunkName: "main" */ '@/pages/Users.vue');
const Brands = () => import(/* webpackChunkName: "main" */ '@/pages/Brands.vue');
const CreateBrand = () => import(/* webpackChunkName: "main" */ '@/pages/CreateBrand.vue');
const UpdateBrand = () => import(/* webpackChunkName: "main" */ '@/pages/UpdateBrand.vue');

const routes: RouteConfig[] = [
    {
        name: 'login',
        path: '/login',
        component: Login
    },
    {
        name: 'signup',
        path: '/signup',
        component: Signup
    },
    {
        path: '/',
        component: MainLayout,
        children: [
            {
                path: '',
                name: 'index',
                component: Index
            }
        ]
    },
    {
        path: '/brands',
        component: MainLayout,
        children: [
            {
                path: '',
                name: 'brands.list',
                component: Brands,
                meta: {
                    title_primary: 'Brands',
                    title_secondary: 'List'
                }
            },
            {
                path: 'create',
                name: 'brands.create',
                component: CreateBrand,
                meta: {
                    title_primary: 'Brands',
                    title_secondary: 'Create'
                }
            },
            {
                path: ':brand',
                name: 'brands.update',
                component: UpdateBrand,
                meta: {
                    title_primary: 'Brands',
                    title_secondary: 'Update'
                }
            }
        ],
    },
    {
        path: '/products',
        component: MainLayout,
        children: [
            {
                path: '',
                name: 'products.list',
                component: Products,
                meta: {
                    title_primary: 'Products',
                    title_secondary: 'List'
                }
            },
            {
                path: 'create',
                name: 'products.create',
                component: CreateProduct,
                meta: {
                    title_primary: 'Products',
                    title_secondary: 'Create'
                }
            },
            {
                path: ':product',
                name: 'products.update',
                component: UpdateProduct,
                meta: {
                    title_primary: 'Products',
                    title_secondary: 'Update'
                }
            }
        ],
    },
    {
        path: '/users',
        component: MainLayout,
        meta: {
            title_primary: 'Users'
        },
        children: [
            {
                path: '',
                name: 'users.list',
                component: Users,
                meta: {
                    title_primary: 'Users',
                    title_secondary: 'List'
                }
            }
        ]
    }
];

export default routes;
