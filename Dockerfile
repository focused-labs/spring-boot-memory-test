FROM openjdk:14-jdk-buster

RUN addgroup --system spring && adduser --system --group spring
USER spring:spring

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
