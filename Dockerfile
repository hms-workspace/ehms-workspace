FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN apt-get update && apt-get install -y maven \
    && mvn clean package -DskipTests \
    && rm -rf /root/.m2/repository

FROM eclipse-temurin:17-jre-focal

WORKDIR /app

COPY --from=0 /app/target/vaadin-app-1.0.0.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "vaadin-app-1.0.0.jar"]
