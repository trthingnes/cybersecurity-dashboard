ARG BUILD_FROM=alpine:3
FROM $BUILD_FROM

RUN apk add --no-cache openjdk21
WORKDIR /app
COPY build/quarkus-app /app

EXPOSE 8099
CMD ["java", "-jar", "/app/quarkus-run.jar"]
