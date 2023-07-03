#!/bin/bash
mvn clean install -DskipTests
docker rmi -f spring-boot/hyu-api-core
docker build -t spring-boot/hyu-api-core .
docker-compose up
