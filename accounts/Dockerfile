FROM openjdk:11-slim as build

LABEL org.opencontainers.image.description="Accounts spring application of udemy course 'Master MS with java, spring, docker, kubernetes'"
LABEL org.opencontainers.image.version="0.0.1-SNAPSHOT"
LABEL org.opencontainers.image.title="accounts"
LABEL org.opencontainers.image.authors="joma74"

COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]