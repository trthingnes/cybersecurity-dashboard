ARG BUILD_FROM=alpine:3

FROM eclipse-temurin:17 AS build
WORKDIR /code
COPY . .
RUN ./gradlew build -x test

FROM $BUILD_FROM AS package
RUN apk add --no-cache openjdk17
WORKDIR /app
COPY --from=build /code/build/quarkus-app .
EXPOSE 8080
CMD ["java", "-jar", "./quarkus-run.jar"]
