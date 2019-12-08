<template>
  <div>
		<div class="row mb-2">
			<router-link :to="{name: 'product-add'}" class="btn btn-success col-sm-2">				
				Add product
			</router-link>
		</div>
		<div class="row" v-if="products.length === 0">
			<div class="card col-sm-12">
				<div class="card-body">
				There is no product registered!
				</div>
			</div>
		</div>
		<div v-if="products.length > 0">
			<div class="row mb-2">
				<input placeholder="Search" v-model="searchKey" class="form-control col-sm-12" id="search-element" required/>
			</div>
			<div class="row">
				<table class="table table-sm table-hover">
					<thead class="thead-light">
						<tr>
							<th scope="col">#</th>
							<th scope="col">Name</th>
							<th scope="col">Description</th>
							<th scope="col">Price</th>
							<th scope="col">Actions</th>
				        </tr>
				    </thead>
				    <tbody>
				       	<tr v-for="(product,index) in products">
				       		<th scope="row">{{index+1}}</th>			       	
				        	<td><router-link :to="{name: 'product', params: {product_id: product.id}}">{{ product.name }}</router-link></td>
				         	<td>{{ product.description }}</td>
				         	<td>{{ product.price }}</td>
				         	<td>
				         		<router-link :to="{name: 'product-edit', params: {product_id: product.id}}" class="btn btn-warning btn-sm">Edit</router-link>				         		
				          		<router-link :to="{name: 'product-delete', params: {product_id: product.id}}" class="btn btn-danger btn-sm">Delete</router-link>
				          		<button type="button" class="btn btn-danger" @click="showModalDelete(product)" data-toggle="modal" data-target="#modalDelete">
  									Delete
								</button>
				        	</td>			         	
				       	</tr>
					</tbody>
				</table>
			</div>
			<div class="modal fade" id="modalDelete" tabindex="-1" role="dialog" aria-labelledby="modalDelete" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Delete product {{ selectedProduct.name }}</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span></button>
						</div>
						<div class="modal-body">
							<p>The action cannot be undone.</p>
						</div>
						<div class="modal-footer">
							<button class="btn btn-danger" data-dismiss="modal" @click="deleteProduct">Delete</button>
							<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue';
import api from '@/components/api-service'

export default {
  name: 'listProduct',
  components: {
    HelloWorld,
  },
  data: function(){
		return {
			products: [],
			productsFixed: [],
			searchKey: '',
			selectedProduct: {name:'', descripion:'', price:0}
		}
	},
	watch: {
		searchKey: function (newQuestion, oldQuestion){			
			productsSearch = this.productsFixed.filter((product) => {
				return product.name.indexOf(this.searchKey) > -1
				|| product.description.indexOf(this.searchKey) > -1
				|| product.price.toString().indexOf(this.searchKey) > -1
			});
			if(productsSearch == null || productsSearch.length === 0 || this.searchKey.length === 0){
				this.products = this.productsFixed;
			} else {			
				this.products = productsSearch;
			}
		},
	},
	methods : {
		showModalDelete(product){
			this.selectedProduct = product;
		},
		deleteProduct() {
	    	api.deleteProduct(this.selectedProduct.id, r => console.log(r));
	    	api.findAll(r => { this.products = r.data, this.productsFixed = r.data});
	    }
	},
	mounted (){
		api.findAll(r => { this.products = r.data, this.productsFixed = r.data});
	},
};
</script>
