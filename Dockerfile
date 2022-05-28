FROM southamerica-east1-docker.pkg.dev/vieco-services/vieco-java-11-alpine/vieco-java-11-alpine:latest

WORKDIR /app

COPY target/*[0-9].jar /app/api.jar

EXPOSE 8080

CMD ["java", "-jar", "api.jar", "--spring.profiles.active=${ENVIRONMENT}"]