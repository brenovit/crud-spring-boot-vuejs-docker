import axios from "axios";
import authHeader from "./auth-header";

const baseApi = axios.create({
  baseURL: process.env.VUE_APP_ROOT_API + "/store/api/test/"
});

class UserService {
  getPublicContent() {
    return baseURL.get("all");
  }

  getUserBoard() {
    return baseURL.get("user", { headers: authHeader() });
  }

  getModeratorBoard() {
    return baseURL.get("mod", { headers: authHeader() });
  }

  getAdminBoard() {
    return baseURL.get("admin", { headers: authHeader() });
  }
}

export default new UserService();
