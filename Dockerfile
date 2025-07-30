FROM bellsoft/liberica-openjdk-alpine:21.0.8

#Install curl jq
RUN apk add curl jq

# Workspace

WORKDIR /home/selenium-docker

# Add require files to run the test
ADD target/docker-resources ./
ADD runner.sh               runner.sh

# Run the tests
ENTRYPOINT sh runner.sh