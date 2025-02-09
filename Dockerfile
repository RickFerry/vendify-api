# Etapa de Build
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copia apenas o arquivo pom.xml e baixa as dependências para melhor aproveitamento do cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o restante do projeto e realiza o build
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de Execução
FROM openjdk:17-jdk-alpine
WORKDIR /app

# Copia o JAR gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

# Define as variáveis de ambiente usadas pela aplicação (Render deve definir os valores reais)
ENV SPRING_PROFILES_ACTIVE=prod \
    JDBC_DATABASE_URL="" \
    JDBC_DATABASE_USERNAME="" \
    JDBC_DATABASE_PASSWORD="" \
    TWILIO_SID="" \
    TWILIO_KEY="" \
    TWILIO_PHONE_FROM="" \
    TWILIO_PHONE_TO=""

# Expõe a porta 8080
EXPOSE 8080

# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]
