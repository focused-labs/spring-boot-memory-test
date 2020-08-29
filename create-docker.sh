#!/usr/bin/env bash

./gradlew clean -x check build
docker build --tag memory-test .
docker tag  memory-test:latest $1/memory-test:latest
docker push $1/memory-test:latest
