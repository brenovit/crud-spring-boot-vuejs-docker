<template>
  <div id="app">
    <div class="container">
      <h2>Store
        <router-link to="/">Home</router-link>
      </h2>
      <router-view/>
    </div>
  </div>
</template>

<style lang="scss">
@import './assets/styles/variables';
@import './assets/styles/bootstrap';
</style>
