FROM alpine:3.15

ARG version=11.0.15.9.1

WORKDIR /app

CMD ["mvn", "verify"]

COPY target/*[0-9].jar /app/api.jar

EXPOSE 8080

CMD ["java", "-jar", "api.jar", "--spring.profiles.active=${ENVIRONMENT}"]