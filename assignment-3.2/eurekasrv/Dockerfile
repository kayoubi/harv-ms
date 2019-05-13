FROM openjdk:8

RUN mkdir -p /usr/local/eurekasvr

ADD  ./target/eurekasrv-0.0.1-SNAPSHOT.jar /usr/local/eurekasvr/

CMD ["java", "-jar", "/usr/local/eurekasvr/eurekasrv-0.0.1-SNAPSHOT.jar"]
