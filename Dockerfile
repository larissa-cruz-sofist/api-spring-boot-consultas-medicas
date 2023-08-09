FROM maven:3.9.3-eclipse-temurin-17-focal
WORKDIR /app
COPY . /project
RUN cd ../project && \
    mvn package spring-boot:repackage && \
    cp target/api-consultas-0.0.1-SNAPSHOT.jar /app/api-consultas.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "api-consultas.jar"]