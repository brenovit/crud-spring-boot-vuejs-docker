import Vue from 'vue';
import Router from 'vue-router';
import ListProduct from './views/ListProduct.vue';
import AddProduct from './views/AddProduct.vue';
import ViewProduct from './views/ViewProduct.vue';
import EditProduct from './views/EditProduct.vue';
import DeleteProduct from './views/DeleteProduct.vue';


Vue.use(Router);

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
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
  ],
});
