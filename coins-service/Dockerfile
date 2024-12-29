FROM eclipse-temurin:23-jre-alpine
LABEL authors="ismaelhassancoulibaly"
COPY target/coins-service-0.0.1-SNAPSHOT.jar /app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]