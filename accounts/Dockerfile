FROM openjdk:11-slim as build

MAINTAINER joma74

COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]