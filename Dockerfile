FROM openjdk:8-jre-alpine
WORKDIR /app
COPY ./target/springJPA-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "springJPA-0.0.1-SNAPSHOT.jar"]
EXPOSE:8081