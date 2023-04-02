FROM openjdk:8-jdk-alpine
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]