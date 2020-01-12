FROM openjdk:8-jdk-alpine as backend-stage
VOLUME ["/tmp","/root/config/"]
ARG JAR_FILE
COPY target/store-*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]