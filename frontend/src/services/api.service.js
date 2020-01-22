import axios from "axios";
import NProgress from "nprogress";

const baseApi = axios.create({
  baseURL: process.env.VUE_APP_ROOT_API + "/store/api/v1/products"
});

baseApi.interceptors.request.use(config => {
  NProgress.start();
  return config;
});

baseApi.interceptors.response.use(config => {
  NProgress.done();
  return config;
});

class ApiService {
  findAll(fn) {
    baseApi
      .get("/")
      .then(response => fn(response))
      .catch(error => console.log(error));
  }

  findById(id, fn) {
    baseApi
      .get(`/${id}`)
      .then(response => fn(response))
      .catch(error => console.log(error));
  }

  create(product, fn) {
    baseApi
      .post("", product)
      .then(response => fn(response))
      .catch(error => console.log(error));
  }

  update(id, product, fn) {
    baseApi
      .put(`/${id}`, product)
      .then(response => fn(response))
      .catch(error => console.log(error));
  }

  deleteProduct(id, fn) {
    baseApi
      .delete(`/${id}`)
      .then(response => fn(response))
      .catch(error => console.log(error));
  }
}
export default new ApiService();
