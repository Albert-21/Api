# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="alberto.ah14@hotmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/Api-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} api.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/api.jar"]
 
# docker build
# sudo docker build -t alberto21/api .

# docker run
# sudo docker run --name ramosgla-api -p 8080:8080 alberto21/api
