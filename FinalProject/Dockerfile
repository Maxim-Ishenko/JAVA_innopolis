FROM khipu/openjdk17-alpine
MAINTAINER Maxim Ishchenko <ilstan62rus@yandex.ru>
ARG WAR_FILE=target/FinalProject-0.0.1-SNAPSHOT.war
WORKDIR /opt/finalProject
COPY ${WAR_FILE} finalProject.jar
ENTRYPOINT ["java", "-jar", "finalProject.jar"]
