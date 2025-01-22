ARG BUILD_FROM=alpine:3.18

FROM $BUILD_FROM AS base
RUN apk add --no-cache openjdk17

FROM base AS build
WORKDIR /code
RUN apk add --no-cache npm
COPY . .
RUN ./gradlew build -x test

FROM base AS package
WORKDIR /app
COPY --from=build /code/build/quarkus-app .
COPY --from=build /code/run.sh .
RUN chmod a+x run.sh
EXPOSE 8080
CMD ["run.sh"]
