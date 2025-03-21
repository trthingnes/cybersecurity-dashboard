# Cybersecurity Dashboard

This Home Assistant add-on uses Quarkus, the Supersonic Subatomic Java Framework. 
If you want to learn more about Quarkus, please visit [its website](https://quarkus.io/).
The UI in `src/webui` is served using Quinoa and Vite, making the add-on a single executable. 

## Local Development

> **NOTE:** For quick development and testing, it is recommended to run the add-on separate from Home Assistant.
> However, since this add-on relies heavily on the Home Assistant APIs, a development instance is still required.
> This can be achieved by configuring a Home Assistant [devcontainer](https://developers.home-assistant.io/docs/add-ons/testing)
> or [virtual machine](https://www.home-assistant.io/installation/alternative).

1. Create a file called `.env` in the project root based on `example.env`.
2. In the development instance, enable advanced mode under general in account settings.
3. In the development instance, create a long-lived access token under security in account settings.
4. Paste the token in the add-on log as the value of `CORE_TOKEN` the `.env` file.
5. Add the development add-on repository: https://github.com/home-assistant/addons-development.
6. Install the Remote API Proxy add-on, set the configured port to 8124, and press start.
7. Paste the token that appears in the add-on log as the value of `SUPERVISOR_TOKEN` the `.env` file.

To run the application in development mode, run `./gradlew quarkusDev`.
This will run both the frontend and backend, as well as the [Quarkus Dev UI](http://localhost:8080/q/dev-ui).

> **NOTE:** If you restart the Remote API Proxy add-on, you have to retrieve the API token from the logs again.
> If you recreate the Home Assistant instance you will have to create the long-lived access token again.

## Local Installation

If you are using the included **devcontainer** configuration, the project files should be available locally.
Open the Home Assistant interface and install the add-on from the local file system.

> **NOTE:** Ensure that the version of the addon is `dev`. If this is not the case, the `config.yaml` file likely
> includes an image URL, and Home Assistant will attempt to download the prebuild image from GitHub Package Registry.

## Building

This project includes a GitHub Action workflow that uses the official builder action to create the add-on container.
Since add-ons are containers with a `config.yaml` file attached, the build process is in the `Dockerfile`.

## Quarkus Resources

- Kotlin ([guide](https://quarkus.io/guides/kotlin)): Write your services in Kotlin.
- Rest ([guide](https://quarkus.io/guides/rest)): Serve a REST API.
- Rest Client ([guide](https://quarkus.io/guides/rest-client)): Communicate with REST APIs.
- Scheduler ([guide](https://quarkus.io/guides/scheduler)): Schedule functions.
- Cache ([guide](https://quarkus.io/guides/cache)): Cache application data.
- Quinoa ([guide](https://quarkiverse.github.io/quarkiverse-docs/quarkus-quinoa/dev/index.html)): Develop, build, and serve your npm-compatible web applications such as React, Angular, Vue, Lit, Svelte, Astro, SolidJS, and others alongside Quarkus.
