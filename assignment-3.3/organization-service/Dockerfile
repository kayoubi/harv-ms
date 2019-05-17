FROM openjdk:8

RUN mkdir -p /usr/local/organization

ADD  ./target/organization-service-0.0.1-SNAPSHOT.jar /usr/local/organization/

CMD ["java", "-jar", "/usr/local/organization/organization-service-0.0.1-SNAPSHOT.jar"]
