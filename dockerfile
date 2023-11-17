# Use the official OpenJDK 8 image as the base image
FROM openjdk:8
EXPOSE 8089
ENV NEXUS_USERNAME=admin
ENV NEXUS_PASSWORD=esprit
ENV NEXUS_REPO_URL=http://192.168.33.10:8081/repository/maven-releases/tn/esprit/spring/khaddem/4.0/khaddem-4.0.jar

# Download the JAR file from Nexus and copy it to the container
RUN curl -L -o khaddem.jar -u $NEXUS_USERNAME:$NEXUS_PASSWORD $NEXUS_REPO_URL

# Define the entry point for your application
ENTRYPOINT ["java", "-jar", "khaddem.jar"]