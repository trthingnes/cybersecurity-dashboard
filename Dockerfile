ARG BUILD_FROM=alpine:3.18

FROM $BUILD_FROM AS base
RUN apk add --no-cache openjdk17

FROM base AS build
RUN apk add --no-cache npm
COPY . .
RUN ./gradlew build -x test

FROM base AS package
COPY --from=build /build/quarkus-app /
COPY run.sh /
RUN chmod a+x /run.sh
CMD ["/run.sh"]
