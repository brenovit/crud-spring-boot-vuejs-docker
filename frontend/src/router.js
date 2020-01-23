import Vue from 'vue';
import Router from 'vue-router';
import Home from '@/views/main/Home'
import Login from '@/views/auth/Login'
import Register from '@/views/auth/Register'
import ListProduct from '@/views/product/ListProduct.vue';
import AddProduct from '@/views/product/AddProduct.vue';
import ViewProduct from '@/views/product/ViewProduct.vue';
import EditProduct from '@/views/product/EditProduct.vue';
import DeleteProduct from '@/views/product/DeleteProduct.vue';


Vue.use(Router);

export const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/home',
      name: 'home',
      component: Home,
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
    },
    {
      path: '/register',
      name: 'register',
      component: Register,
    },
    {
      path: '/products',
      name: 'product-list',
      component: ListProduct,
    },
    {
      path: '/product/:product_id',
      name: 'product-view',
      component: ViewProduct,
    },
    {
      path: '/product',
      name: 'product-add',
      component: AddProduct,
    },
    {
      path: '/product/edit/:product_id',
      name: 'product-edit',
      component: EditProduct,
    },
    {
      path: '/product/delete/:product_id',
      name: 'product-delete',
      component: DeleteProduct,
    },
    {
      path: '/profile',
      name: 'profile',
      // lazy-loaded
      component: () => import('@/views/profile/Profile.vue')
    },
    {
      path: '/admin',
      name: 'admin',
      // lazy-loaded
      component: () => import('@/views/main/BoardAdmin.vue')
    },
    {
      path: '/mod',
      name: 'moderator',
      // lazy-loaded
      component: () => import('@/views/main/BoardModerator.vue')
    },
    {
      path: '/user',
      name: 'user',
      // lazy-loaded
      component: () => import('@/views/main/BoardUser.vue')
    }
  ],
});

router.beforeEach((to, from, next) => {
  const publicPages = ['/login', '/home'];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem('user');

  if (authRequired && !loggedIn) {
    return next('/login');
  }

  next();
});