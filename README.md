# crud-spring-boot-vuejs-docker
CRUD-spring-boot-vuejs-docker

Run the commands
At backend folder run
> mvn clean package

At root folder run
> docker build -t productstore .
> docker run -it --rm --name productstore -p [external port]:[port at application.properties] -v [application.properties location]:/root/config product-store

Go to
http://localhost:[extenal_port]/store/#/

docker run -it --rm --name productstore -p 8282:8181 -v /home/breno/git/personal/java/product-store/external-file:/root/config product-store
http://localhost:8282/store/#/


docker-compose up
