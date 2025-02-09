FROM maven:3.8.4-openjdk-17 AS build

COPY . .
RUN mvn clean install

FROM openjdk:17-jdk-alpine

EXPOSE 8080

ENV TWILIO_SID=${TWILIO_SID}
ENV TWILIO_KEY=${TWILIO_KEY}
ENV TWILIO_PHONE_FROM=${TWILIO_PHONE_FROM}
ENV TWILIO_PHONE_TO=${TWILIO_PHONE_TO}

COPY --from=build /target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]