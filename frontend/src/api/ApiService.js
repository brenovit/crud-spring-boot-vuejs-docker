const baseApi = axios.create({
	baseURL: '/WexBatch/api/'
});

baseApi.interceptors.request.use(config => {
	NProgress.start();
	return config;
});

baseApi.interceptors.response.use(config => {
	NProgress.done();
	return config;
});

var apiService = {
		getJobsStatus(fn) {
			baseApi
		      .get('job/status')
		      .then(response => fn(response))
		      .catch(error => console.log(error))
		},
		getJobs(fn) {
			baseApi
		      .get('job')
		      .then(response => fn(response))
		      .catch(error => console.log(error))
		},
		getJobsControle(fn) {
			baseApi
		      .get('job/controle')
		      .then(response => fn(response))
		      .catch(error => console.log(error))
		},
		getJobsContingencia(fn) {
			baseApi
		      .get('job/contingencia')
		      .then(response => fn(response))
		      .catch(error => console.log(error))
		},
		start(chave, fn){
			baseApi
		      .post('job/start/'+chave)
		      .then(response => fn(response))
		      .catch(error => console.log(error))
		},
		stop(chave, fn){
			baseApi
		      .post('job/stop/'+chave)
		      .then(response => fn(response))
		      .catch(error => console.log(error))
		},
		find(chave, fn){
			baseApi
		      .get('job/'+chave)
		      .then(response => fn(response))
		      .catch(error => console.log(error))
		},
		execute(chave, query, fn){
			baseApi
		      .post('job/execute/'+chave, null, { params:query })
		      .then(response => fn(response))
		      .catch(error => console.log(error))
		},
		update(id, job, fn){
			baseApi
		      .put('job/update/'+id, job)
		      .then(response => fn(response))
		      .catch(error => console.log(error))
		},
		gerarCodigoAcesso(query, fn){
			baseApi
		      .get('codigoacesso/gerar', { params:query })
		      .then(response => fn(response))
		      .catch(error => console.log(error))
		}		
	};