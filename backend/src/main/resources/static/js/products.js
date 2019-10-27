var products = [];

const api = axios.create({
	baseURL: '/store/api/v1/products'
});
	
function findProduct(productId) {
	return products[findProductKey(productId)];
}

function findProductKey(productId) {
	for (var key = 0; key < products.length; key++) {
		if (products[key].id == productId) {
			return key;
		}
	}
}

var productService = {
	findAll(fn){
		api
		.get('/')
		.then(response => fn(response))
		.catch(error => console.log(error))		
	},
	findById(id, fn){
		api
		.get('/'+id)
		.then(response => fn(response))
		.catch(error => console.log(error))
	},
	create(product, fn){
		api
		.post('',product)
		.then(response => fn(response))
		.catch(error => console.log(error))
	},
	update(id, product, fn){
		api
		.put('/'+id, { data: product } )
		.then(response => fn(response))
		.catch(error => console.log(error))
	},
	deleteProduct(id, fn){
		api
		.delete('/'+id)
		.then(response => fn(response))
		.catch(error => console.log(error))
	}
};

var List = Vue.extend({
	template: '#product-list',
	componnents: {
		ModalDelete:'modal'
	},
	data: function(){
		return {
			products: [],
			searchKey: '',
			product: {name:'', descripion:'', price:0}
		}
	},
	computed: {
		filteredProducts(){
			if(this.products.length === 0){
				return [];
			}
			return this.products.filter((product) =>{
				return product.name.indexOf(this.searchKey) > -1
				|| product.description.indexOf(this.searchKey) > -1
				|| product.price.toString().indexOf(this.searchKey) > -1
			});
		}
	},
	methods : {
		showModalDelete(product){
			this.product = product;
		},
		deleteProduct() {
	    	productService.deleteProduct(this.product.id, r => console.log(r));
	    	this.searchKey = "";
	    }
	},
	mounted (){
		productService.findAll(r => {
			this.products = r.data; products = r.data
		});
	}
});

var Product = Vue.extend({
	template: '#product',
	data: function(){
		return {
			product: findProduct(this.$route.params.product_id)
		};
	}
});


var ProductAdd = Vue.extend({
	template: '#add-product',
	data: function(){
		return {
			product: {name:'', descripion:'', price:0}
		}
	},
	methods:{
		createProduct(){
			productService.create(this.product, r => router.push('/'));
		}
	}
});


var ProductEdit = Vue.extend({
	template: '#edit-product',
	data: function(){
		return {
			product: findProduct(this.$route.params.product_id)
		}
	},
	methods:{
		updateProduct: function(){
			productService.update(this.product.id, this.product, r => router.push('/'));
		}
	}
});


var ProductDelete = Vue.extend({
	template: '#delete-product',
	data: function() {
	    return {product: findProduct(this.$route.params.product_id)};
	},
	methods: {
	    deleteProduct: function () {
	    	productService.deleteProduct(this.product.id, r => router.push('/'));
	    }
	}
});

var router = new VueRouter({
	routes: [
		{path: '/', component: List},
		{path: '/product/:product_id', component: Product, name: 'product'},
		{path: '/product', component: ProductAdd, name: 'product-add'},
		{path: '/product/edit/:product_id', component: ProductEdit, name: 'product-edit'},
		{path: '/product/delete/:product_id', component: ProductDelete, name: 'product-delete'},
	]	
});

new Vue({
	router
}).$mount('#app');