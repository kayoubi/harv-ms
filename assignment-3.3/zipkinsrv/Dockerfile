FROM openjdk:8

RUN mkdir -p /usr/local/zipkinsrv

ADD  ./target/zipkinsrv-0.0.1-SNAPSHOT.jar /usr/local/zipkinsrv/

CMD ["java", "-jar", "/usr/local/zipkinsrv/zipkinsrv-0.0.1-SNAPSHOT.jar"]
