#!/usr/bin/with-contenv bashio

export OPTION_LOG_LEVEL=$(bashio::config 'log_level')
export OPTION_SCHEDULE_INTERVAL=$(bashio::config 'schedule_interval')
export OPTION_INSTANCE_URL=$(bashio::config 'instance_url')
export OPTION_GITHUB_API_TOKEN=$(bashio::config 'github_api_token')

java -jar /quarkus-run.jar -D