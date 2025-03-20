#!/usr/bin/with-contenv bashio

OPTION_INSTANCE_URL=$(bashio::config 'instance_url')

java -jar /quarkus-run.jar