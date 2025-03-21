#!/usr/bin/with-contenv bashio

export OPTION_INSTANCE_URL=$(bashio::config 'instance_url')
export OPTION_GITHUB_API_TOKEN=$(bashio::config 'github_api_token')

java -jar /quarkus-run.jar -D