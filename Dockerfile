From maven:3. 8.3-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./sr
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim 
WORKDIR /app
COPY --from=build /app/target/FinalProjectIPLTeamManagement-1.0.jar app.jar
EXPOSE 8282
ENTRYPOINT ["java", "-jar",  "app.jar"]