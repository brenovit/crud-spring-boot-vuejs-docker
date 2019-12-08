<template>
  <div>
		  <h2>Delete product {{ product.name }}</h2>
		  <div>
		  	<p>The action cannot be undone.</p>
		   	<button class="btn btn-danger" @click="deleteProduct">Delete</button>
			<router-link to="/" class="btn btn-secondary">Cancel</router-link>
		  </div>
		</div>
</template>

<script>
import api from '@/components/api-service';

export default {
  name: 'deleteProduct',
  data: function() {
	    return {
	    	product: []
	    };
	},
	methods: {
    deleteProduct: function () {
      api.deleteProduct(this.product.id, r => router.push('/'));
    }
	},
	mounted (){
		api.findById(this.$route.params.product_id, r => this.product = r.data);
	},
};
</script>
