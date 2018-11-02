#!/usr/bin/env bash

docker-compose -f docker-compose.yml stop

echo "Cleaning docker containers"
docker rm $(docker ps -qa --no-trunc --filter "status=exited")