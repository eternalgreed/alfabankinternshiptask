FROM openjdk:11
COPY . /app
WORKDIR /app
RUN ./gradlew build
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "build/libs/alfabankinternshiptask-0.0.1-SNAPSHOT.jar"]
