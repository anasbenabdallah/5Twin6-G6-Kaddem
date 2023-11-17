FROM openjdk:18
EXPOSE 8088
ADD target/khaddem-4.0.jar khaddem-4.0.jar
ENTRYPOINT ["java", "-jar", "khaddem-4.0.jar"]