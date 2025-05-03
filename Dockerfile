From maven:3.8.3-openjdk-17 AS build
COPY . . 
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim 
COPY --from=build /app/target/FinalProjectIPLTeamManagement-1.0-SNAPSHOT.jar FinalProjectIPLTeamManagement.jar
EXPOSE 8282
ENTRYPOINT ["java", "-jar",  "FinalProjectIPLTeamManagement.jar"]