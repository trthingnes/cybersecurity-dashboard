#!/usr/bin/with-contenv bashio

export OPTION_INSTANCE_URL=$(bashio::config 'instance_url')

java -jar /quarkus-run.jar -D