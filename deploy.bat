@echo off
echo Building Spring Boot application...
mvn clean package -DskipTests

echo Building Docker image...
docker build -t shopping-backend .

echo Starting application with Docker Compose...
docker-compose up -d

echo Application is running at http://localhost:8080
echo MySQL is running at localhost:3306
echo.
echo To stop the application, run: docker-compose down