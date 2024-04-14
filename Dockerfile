# Use the official OpenJDK image as a base image
FROM adoptopenjdk/openjdk17:alpine-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/my-spring-boot-app.jar /app/my-spring-boot-app.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8080

# Define the command to run the application when the container starts
CMD ["java", "-jar", "my-spring-boot-app.jar"]
