FROM openjdk:17
COPY ./target/openai-1.0.0.jar app.jar
WORKDIR .
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]