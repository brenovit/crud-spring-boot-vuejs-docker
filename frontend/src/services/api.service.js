import axios from "axios";
import authHeader from "@/services/auth-header";
//import NProgress from "nprogress";

const baseApi = axios.create({
  baseURL: process.env.VUE_APP_ROOT_API + "/store/api/v1/products"
});

/*
baseApi.interceptors.request.use(config => {
  NProgress.start();
  return config;
});

baseApi.interceptors.response.use(config => {
  NProgress.done();
  return config;
});
*/

class ApiService {
  findAll(fn) {
    baseApi
      .get("/", { headers: authHeader() })
      .then(response => fn(response))
      .catch(error => console.log(error));
  }

  findById(id, fn) {
    baseApi
      .get(`/${id}`, { headers: authHeader() })
      .then(response => fn(response))
      .catch(error => console.log(error));
  }

  create(product, fn) {
    baseApi
      .post("", product, { headers: authHeader() })
      .then(response => fn(response))
      .catch(error => console.log(error));
  }

  update(id, product, fn) {
    baseApi
      .put(`/${id}`, product, { headers: authHeader() })
      .then(response => fn(response))
      .catch(error => console.log(error));
  }

  deleteProduct(id, fn) {
    baseApi
      .delete(`/${id}`, { headers: authHeader() })
      .then(response => fn(response))
      .catch(error => console.log(error));
  }
}
export default new ApiService();
