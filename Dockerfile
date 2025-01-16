ARG BUILD_FROM=alpine:3

FROM $BUILD_FROM AS base
RUN apk add --no-cache openjdk17

FROM base AS build
RUN apk add --no-cache nodejs
WORKDIR /code
COPY . .
RUN ./gradlew build -x test

FROM base AS package
WORKDIR /app
COPY --from=build /code/build/quarkus-app .
EXPOSE 8080
CMD ["java", "-jar", "./quarkus-run.jar"]
