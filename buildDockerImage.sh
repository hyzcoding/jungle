#!/usr/bin/env bash

set -eo pipefail

modules=( jungle-eureka jungle-article jungle-user )

for module in "${modules[@]}"; do
    docker build -t "jungle/${module}:latest" ${module}
done