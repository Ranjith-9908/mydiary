# Step 1: Use OpenJDK 21 as base image
FROM openjdk:21-jdk-slim

# Step 2: Set the working directory
WORKDIR /app

# Step 3: Copy the packaged JAR file into the container
COPY target/*.jar app.jar

# Step 4: Expose the application port
EXPOSE 8080

# Step 5: Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
