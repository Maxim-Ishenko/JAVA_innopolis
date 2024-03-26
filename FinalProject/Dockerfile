FROM openjdk:17
MAINTAINER Maxim Ishchenko <ilstan62rus@yandex.ru>

ARG WAR_FILE=target/FinalProject-0.0.1-SNAPSHOT.war
COPY ${WAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
