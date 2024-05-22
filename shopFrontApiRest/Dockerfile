FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/shopFrontApiRest-2.0.4.jar
COPY ${JAR_FILE} app_shop.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app_shop.jar"]
