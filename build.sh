#!/bin/bash

cd backend && \
mvn clean package && \
docker build -t productstore-backend . && \
cd ../frontend && \
npm install && \
npm run build && \
docker build -t productstore-frontend . && \
cd ..

docker run -it --name ps-back -p 8181:8181 -v /home/breno/git/personal/java/product-store/external-file:/root/config productstore-backend
docker run -it --name ps-front -p 8080:80 productstore-frontend
