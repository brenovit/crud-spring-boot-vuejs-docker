<template>
  <div>
    <h2>View product</h2>
    <div class="row" v-if="product === null">
      <div class="card col-sm-12">
        <div class="card-body">There is no product registered!</div>
      </div>
    </div>
    <div v-if="product">
      <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name" v-model="product.name" readonly />
      </div>
      <div class="form-group">
        <label for="description">Description</label>
        <textarea
          type="text-area"
          class="form-control"
          id="description"
          v-model="product.description"
          rows="5"
          readonly
        ></textarea>
      </div>
      <div class="form-group">
        <label for="price">
          Price
          <span class="glyphicon glyphicon-euro"></span>
        </label>
        <input type="text" class="form-control" id="price" v-model="product.price" readonly />
      </div>
      <router-link
        :to="{name: 'product-edit', params: {product_id: product.id}}"
        class="btn btn-warning btn-xs"
      >Edit</router-link>&nbsp;
      <router-link to="/" class="btn btn-secondary">Back</router-link>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import api from "@/services/api.service";

export default {
  name: "viewProduct",
  data() {
    return {
      product: []
    };
  },
  mounted() {
    api.findById(this.$route.params.product_id, r => (this.product = r.data));
  }
};
</script>
