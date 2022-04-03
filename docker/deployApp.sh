#!/bin/bash

cd ../backend

mvn spring-boot:build-image -Dspring-boot.build-image.imageName=registry.heroku.com/$1/web

docker push registry-host:registry.heroku.com/$1/web #alessandronuzziurjc/codeurjc-daw-2021-22-webapp3

heroku container:release web -a $1

heroku logs --tail -a $1