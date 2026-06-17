FROM eclipse-temurin:17-jdk AS builder

# Set the working directory
WORKDIR /app

# Copy the maven executable to the container
COPY . .

# Build the application
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Run the application
FROM eclipse-temurin:17-jre

# Set the working directory
WORKDIR /app

# Copy the built application from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]