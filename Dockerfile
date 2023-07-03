FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/hyu-api-core-0.0.1-SNAPSHOT.jar hyu-api-core-0.0.1-SNAPSHOT.jar
ENV ENVIRONMENT=docker
ENTRYPOINT ["java","-jar","/hyu-api-core-0.0.1-SNAPSHOT.jar"]