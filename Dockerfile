FROM openjdk:17
WORKDIR /app
COPY target/*.jar kaddemuniversite.jar
EXPOSE 8080
CMD ["java","-jar","kaddemuniversite.jar"]