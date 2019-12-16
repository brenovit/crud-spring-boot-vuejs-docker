cd backend \ 
mvn clean package \ 
	docker build -t productstore-backend . \ 
	cd ../frontend \ 
	npm install && npm run build \ 
	docker build -t productstore-frontend .