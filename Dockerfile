FROM maven:3.8.4-openjdk-17 AS build

COPY . .
RUN mvn clean install

FROM openjdk:17-jdk-alpine

EXPOSE 8080

ENV TWILIO_SID=your_twilio_sid_value
ENV TWILIO_KEY=your_twilio_key_value
ENV TWILIO_PHONE_FROM=your_twilio_phone_from_value
ENV TWILIO_PHONE_TO=your_twilio_phone_to_value

COPY --from=build /target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]