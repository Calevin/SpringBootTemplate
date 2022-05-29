FROM maven:3.5-jdk-11 AS build
ENV SPRING_PROFILES_ACTIVE=prod

COPY pom.xml .
COPY src ./src/

RUN --mount=type=cache,target=/root/.m2 mvn -DskipTests=true -B -s /usr/share/maven/ref/settings-docker.xml clean verify

RUN mvn -DskipTests=true -B -s /usr/share/maven/ref/settings-docker.xml clean package

FROM openjdk:11-jdk-slim

EXPOSE 80

COPY --from=build /target/*.jar /app/spring-boot-application.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prod","-jar","/app/spring-boot-application.jar"]