# crud-spring-boot-vuejs-docker
CRUD-spring-boot-vuejs-docker

Run the commands
At backend folder run
> mvn clean package
> docker build -t ps-back .

At fronted folder run
> npm run build
> docker build -t ps-front .

At root folder run
> docker run -it --rm --name ps-back -p 8181:8181 -v /home/breno/git/personal/java/product-store/external-file:/root/config ps-back

Go to
http://localhost:8181/store/

> docker run -it --rm --name ps-fron -p 8080:80 ps-front

Go to
http://localhost:8080

Or

docker-compose up
