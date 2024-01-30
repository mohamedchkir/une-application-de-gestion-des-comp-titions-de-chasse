# this stage for packaging our app into a jar file
FROM maven:3.9.6-amazoncorretto-17 AS package

WORKDIR /app

COPY pom.xml .

COPY src src

RUN mvn clean package  -DskipTests

# second stage to run our app
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=package /app/target/aftas-0.0.1.jar .

EXPOSE 8888

CMD ["java", "-jar", "aftas-0.0.1.jar"]
