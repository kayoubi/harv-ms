FROM openjdk:8

RUN mkdir -p /usr/local/securitysrv

ADD  ./target/securitysrv-0.0.1-SNAPSHOT.jar /usr/local/securitysrv/

CMD ["java", "-jar", "/usr/local/securitysrv/securitysrv-0.0.1-SNAPSHOT.jar"]
