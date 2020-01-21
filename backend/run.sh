#!/bin/bash

./build.sh

docker build -t productstore-backend .

docker stop ps-back && \
docker rm ps-back && \

docker run -d --name ps-back -p 8181:8181 -v /home/breno/git/personal/java/product-store/external-file:/root/config productstore-backend
