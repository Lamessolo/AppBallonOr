FROM openjdk:11

COPY target/api-joueurs.jar /api-joueurs.jar

ENTRYPOINT ["java", "-jar", "/api-joueurs.jar"]
