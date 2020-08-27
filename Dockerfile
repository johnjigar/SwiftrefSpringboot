FROM openjdk:8-jdk-alpine

COPY target/swiftref-latest.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

