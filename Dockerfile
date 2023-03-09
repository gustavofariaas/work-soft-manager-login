

FROM openjdk:11-jdk

# Set the working directory
WORKDIR /app

# Copy the Spring Boot application JAR file to the working directory
COPY target/WorkSoftManagerAuthenticator-0.0.1-SNAPSHOT.jar .

# Expose the port that the Spring Boot application runs on
EXPOSE 8080

# Start the Spring Boot application
CMD ["java", "-jar", "<jar-file-name>.jar"]
