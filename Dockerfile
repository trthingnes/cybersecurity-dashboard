ARG BUILD_FROM=alpine:3

FROM node:20-alpine AS remix-dev-deps-env
COPY /frontend /frontend
WORKDIR /frontend
RUN npm ci

FROM node:20-alpine AS remix-build-env
COPY /frontend /frontend
COPY --from=remix-dev-deps-env /frontend/node_modules /frontend/node_modules
WORKDIR /frontend
RUN npm run build

FROM $BUILD_FROM
COPY --from=remix-build-env /frontend/build/client /frontend/build/client
RUN apk --no-cache add nginx && mkdir -p /run/nginx
COPY ingress.conf /etc/nginx/http.d/

CMD [ "nginx", "-g", "daemon off; error_log /dev/stdout debug;" ]