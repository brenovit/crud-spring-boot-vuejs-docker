<template>
  <div>
			<h2>Edit product</h2>
			<div v-if="product">
				<form @submit="updateProduct">
					<div class="form-group">
						<label for="edit-name">Name</label>
						<input class="form-control" id="edit-name" v-model="product.name" required/>
					</div>
					<div class="form-group">
						<label for="edit-description">Description</label>
						<textarea class="form-control" id="edit-description" rows="5" v-model="product.description"></textarea>
					</div>
					<div class="form-group">
						<label for="edit-price">Price <span class="glyphicon glyphicon-euro"></span></label>
						<input type="number" id="edit-price" step="0.01" pattern="^\d+(?:\.\d{1,2})?$" class="form-control" v-model="product.price"/>
					</div>
					<button class="btn btn-primary">Save</button>&nbsp;
					<router-link to="/" class="btn btn-secondary">Cancel</router-link>
				</form>
			</div>
		</div>
</template>

<script>
// @ is an alias to /src
import api from '@/components/api-service';

export default {
  name: 'editProduct',
  data() {
    return {
      product: [],
    };
  },
  methods: {
    updateProduct() {
      api.update(this.product.id, this.product, r => router.push('/'));
    },
  },
  mounted() {
    api.findById(this.$route.params.product_id, r => this.product = r.data);
  },
};
</script>
