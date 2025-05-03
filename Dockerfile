From maven:3.8.3-openjdk-17 AS build
COPY . . 
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim 
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar FinalProjectIPLTeamManagement.jar
EXPOSE 8282
ENTRYPOINT ["java", "-jar",  "FinalProjectIPLTeamManagement.jar"]