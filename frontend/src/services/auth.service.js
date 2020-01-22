import axios from "axios";

const baseApi = axios.create({
  baseURL: process.env.VUE_APP_ROOT_API + "/store/api/auth/"
});

class AuthService {
  login(user) {
    return baseApi
      .post("signin", {
        username: user.username,
        password: user.password
      })
      .then(this.handleResponse)
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
  }

  register(user) {
    return baseApi.post("signup", {
      username: user.username,
      password: user.password,
      email: user.email
    });
  }

  handleResponse(response) {
    if (response.status === 401) {
      this.logout();
      location.reload(true);

      const error = response.data && response.data.message;
      return Promise.reject(error);
    }

    return Promise.resolve(response);
  }
}

export default new AuthService();
