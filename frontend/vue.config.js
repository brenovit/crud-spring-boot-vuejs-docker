// vue.config.js
module.exports = {
  publicPath: '/',
  outputDir: 'dist',
  // proxy all webpack dev-server requests starting with /api
  // to our Spring Boot backend (localhost:8088) using http-proxy-middleware
  // see https://cli.vuejs.org/config/#devserver-proxy
  /*devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8181', // this configuration needs to correspond to the Spring Boot backends' application.properties server.port
        ws: true,
        changeOrigin: true,
      },
    },
  },*/
};
