ARG BUILD_FROM=alpine:3.21
# See https://github.com/home-assistant/docker-base for current BUILD_FROM alpine versions.

FROM $BUILD_FROM AS base
RUN apk add openjdk17 gradle npm

FROM base AS build
COPY build.gradle.kts settings.gradle.kts gradle.properties gradlew ./
RUN gradle dependencies
COPY . .
RUN gradle build -x test

FROM base AS package
COPY --from=build /build/quarkus-app /
COPY run.sh /
RUN chmod a+x /run.sh
CMD ["/run.sh"]
