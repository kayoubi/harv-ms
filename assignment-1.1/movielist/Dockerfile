FROM openjdk:8

RUN mkdir -p /opt/imin/activity

ADD ./target/movielist-0.0.1-SNAPSHOT.jar /opt/imin/user/

EXPOSE 8080

CMD ["java", "-jar", "/opt/imin/user/movielist-0.0.1-SNAPSHOT.jar"]