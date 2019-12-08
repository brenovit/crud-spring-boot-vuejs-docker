# crud-spring-boot-vuejs-docker
CRUD-spring-boot-vuejs-docker

Run the commands
At backend folder run
> mvn clean package

At fronted folder run
> npm run build

At root folder run
> docker build -t productstore .
> docker run -it --rm --name productstore -p [external_port]:[port_at_application.properties] -v [application.properties_location]:/root/config product-store

Go to
http://localhost:[extenal_port]/store/

docker run -it --rm --name productstore -p 8181:8181 -v /home/breno/git/personal/java/product-store/external-file:/root/config product-store
http://localhost:8181/store/#/


docker-compose up
