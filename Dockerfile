FROM maven:3.6-jdk-11 AS builder

COPY ./pom.xml ./pom.xml
COPY src ./src/

ENV MAVEN_OPTS='-Xmx6g'
RUN mvn -Dmaven.test.skip=true -Pnative-image-fargate clean package

RUN ls -l target/

###########

FROM openjdk:11-slim

RUN apt-get update && apt-get install -y curl

COPY --from=builder target/grpc-server-1.0-SNAPSHOT-jar-with-dependencies.jar ./grpc-server.jar

EXPOSE 8682

CMD [ "java", "-jar", "grpc-server.jar" ]