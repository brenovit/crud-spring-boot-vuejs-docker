<template>
  <div>
    <h2>Add new product</h2>
    <form @submit="createProduct">
      <div class="form-group">
        <label for="add-name">Name</label>
        <input class="form-control" id="add-name" v-model="product.name" required />
      </div>
      <div class="form-group">
        <label for="add-description">Description</label>
        <textarea class="form-control" id="add-description" rows="5" v-model="product.description"></textarea>
      </div>
      <div class="form-group">
        <label for="add-price">
          Price
          <span class="glyphicon glyphicon-euro"></span>
        </label>
        <input
          type="number"
          step="0.01"
          pattern="^\d+(?:\.\d{1,2})?$"
          class="form-control"
          id="add-price"
          v-model="product.price"
        />
      </div>
      <button class="btn btn-primary">Create</button>&nbsp;
      <router-link to="/" class="btn btn-secondary">Cancel</router-link>
    </form>
  </div>
</template>

<script>
// @ is an alias to /src
import api from "@/services/api.service";

export default {
  name: "addProduct",
  data() {
    return {
      product: { name: "", descripion: "", price: 0 }
    };
  },
  methods: {
    createProduct() {
      api.create(this.product, r => router.push("/"));
    }
  }
};
</script>
