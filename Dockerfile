FROM openjdk:21-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

copy src src
RUN ./mvnw package

FROM openjdk:21-jdk-slim
WORKDIR weather-api
COPY --from=build target/*.jar weather-api.jar
ENTRYPOINT ["java","-jar","weather-api.jar"]