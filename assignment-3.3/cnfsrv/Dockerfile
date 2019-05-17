#FROM davidcaste/alpine-java-unlimited-jce
FROM openjdk:8

RUN apt-get update && apt-get install -y curl

RUN mkdir -p /usr/local/configserver

ADD  ./target/cnfsrv-0.0.1-SNAPSHOT.jar /usr/local/configserver/

CMD ["java", "-jar", "/usr/local/configserver/cnfsrv-0.0.1-SNAPSHOT.jar"]
