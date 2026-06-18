FROM eclipse-temurin:17-jre

WORKDIR /app
ARG JAR_FILE=build/libs/user-profile-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
