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
      name: 'list-product',
      component: ListProduct,
    },
    {
      path: '/product/:product_id',
      name: 'view-product',
      component: ViewProduct,
    },
    {
      path: '/product',
      name: 'add-product',
      component: AddProduct,
    },
    {
      path: '/product/:product_id/edit',
      name: 'edit-product',
      component: EditProduct,
    },
    {
      path: '/product/:product_id/delete',
      name: 'delete-product',
      component: DeleteProduct,
    },
  ],
});
