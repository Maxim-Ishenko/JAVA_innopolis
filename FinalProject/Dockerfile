FROM openjdk:17
MAINTAINER Maxim Ishchenko <ilstan62rus@yandex.ru>

ARG JAR_FILE=target/FinalProject-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
