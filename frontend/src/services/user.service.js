import axios from "axios";
import authHeader from "./auth-header";

const baseApi = axios.create({
  baseURL: process.env.VUE_APP_ROOT_API + "/store/api/test/"
});

class UserService {
  getPublicContent() {
    return baseApi.get("all");
  }

  getUserBoard() {
    return baseApi.get("user", { headers: authHeader() });
  }

  getModeratorBoard() {
    return baseApi.get("mod", { headers: authHeader() });
  }

  getAdminBoard() {
    return baseApi.get("admin", { headers: authHeader() });
  }
}

export default new UserService();
