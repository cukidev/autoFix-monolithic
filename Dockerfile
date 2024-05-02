FROM openjdk:17
ARG JAR_FILE=./target/autoFix-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} autoFix-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/autoFix-0.0.1-SNAPSHOT.jar"]