FROM eclipse-temurin:21
LABEL authors="user"
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]