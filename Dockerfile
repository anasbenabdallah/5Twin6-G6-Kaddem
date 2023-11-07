FROM openjdk:18-alpine
ADD target/khaddem-*.jar khaddem.jar
ENTRYPOINT ["java", "-jar", "khaddem.jar"]
