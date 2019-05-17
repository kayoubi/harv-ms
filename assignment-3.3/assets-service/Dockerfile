FROM openjdk:8

RUN mkdir -p /opt/imin/user

ADD ./target/assets-service-0.0.1-SNAPSHOT.jar /opt/imin/user/

EXPOSE 8080

CMD ["java", "-jar", "/opt/imin/user/assets-service-0.0.1-SNAPSHOT.jar"]