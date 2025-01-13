ARG BUILD_FROM
FROM $BUILD_FROM

# Install NGINX
RUN \
  apk --no-cache add \
    nginx \
  \
  && mkdir -p /run/nginx

# Copy configuration
COPY ingress.conf /etc/nginx/http.d/

CMD [ "nginx", "-g", "daemon off; error_log /dev/stdout debug;" ]