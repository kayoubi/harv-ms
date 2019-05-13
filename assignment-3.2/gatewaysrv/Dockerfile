FROM openjdk:8

RUN mkdir -p /usr/local/gatewaysrv

ADD  ./target/gatewaysrv-0.0.1-SNAPSHOT.jar /usr/local/gatewaysrv/

CMD ["java", "-jar", "/usr/local/gatewaysrv/gatewaysrv-0.0.1-SNAPSHOT.jar"]
