FROM maven:3.8.4-openjdk-17 AS build

COPY . .
RUN mvn clean install

FROM openjdk:17-jdk-alpine

EXPOSE 8080

COPY --from=build /target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]