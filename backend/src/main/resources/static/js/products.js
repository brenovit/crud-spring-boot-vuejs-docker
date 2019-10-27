const api = axios.create({
	baseURL: '/store/api/v1/products'
});
	
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
		.put('/'+id, product )
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
		}
	},
	methods : {
		showModalDelete(product){
			this.selectedProduct = product;
		},
		deleteProduct() {
	    	productService.deleteProduct(this.selectedProduct.id, r => console.log(r));
	    	productService.findAll(r => { this.products = r.data, this.productsFixed = r.data});
	    }
	},
	mounted (){
		productService.findAll(r => { this.products = r.data, this.productsFixed = r.data});
	}
});

var Product = Vue.extend({
	template: '#product',
	data: function(){
		return {
			product: []
		};
	},
	mounted (){
		productService.findById(this.$route.params.product_id, r => this.product = r.data);
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
			product: []
		}
	},
	methods:{
		updateProduct: function(){
			productService.update(this.product.id, this.product, r => router.push('/'));
		}
	},
	mounted (){
		productService.findById(this.$route.params.product_id, r => this.product = r.data);
	}
});


var ProductDelete = Vue.extend({
	template: '#delete-product',
	data: function() {
	    return {
	    	product: []
	    };
	},
	methods: {
	    deleteProduct: function () {
	    	productService.deleteProduct(this.product.id, r => router.push('/'));
	    }
	},
	mounted (){
		productService.findById(this.$route.params.product_id, r => this.product = r.data);
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