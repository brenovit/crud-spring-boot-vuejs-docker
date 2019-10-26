FROM openjdk:8-jdk-alpine
VOLUME ["/tmp","/root/config/"]
ARG JAR_FILE
COPY backend/target/store-*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.config.location=file:/root/config/application.properties"]