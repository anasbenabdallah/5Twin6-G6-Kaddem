FROM openjdk:17
WORKDIR /app
COPY target/*.jar kaddemetudiant.jar
RUN curl -u admin:azer9876 -O http://172.17.0.1:8081/repository/maven-releases/tn/esprit/spring/khaddem/4.0/khaddem-4.0.jar
EXPOSE 8080
CMD ["java","-jar","kaddemetudiant.jar"]