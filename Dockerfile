FROM openjdk:11-jre

COPY build/libs/*.jar Member_Board_REST-API.jar

ENTRYPOINT ["java", "-jar", "Member_Board_REST-API.jar"]