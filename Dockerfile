FROM openjdk:11
WORKDIR /app
COPY target/api-joueurs.jar api-joueurs.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "api-joueurs.jar"]
