FROM openjdk:17-jdk-alpine
LABEL authors="User"

EXPOSE 8080

WORKDIR /app
COPY target/reactive-demo-0.0.1-SNAPSHOT.jar /app/reactive-demo-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar","reactive-demo-0.0.1-SNAPSHOT.jar"]