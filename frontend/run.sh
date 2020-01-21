#!/bin/bash

./build.sh

docker build -t productstore-frontend .

docker stop ps-front && \
docker rm ps-front

docker run -d --name ps-front -p 8080:80 productstore-frontend
