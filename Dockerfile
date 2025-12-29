# Use a Java base image
FROM eclipse-temurin:17-jdk-alpine
# Copy the JAR file from the target folder to the container
COPY target/*.jar app.jar
# Command to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]