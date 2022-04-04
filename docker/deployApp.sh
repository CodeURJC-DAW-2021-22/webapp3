#!/bin/bash
# $1 = alessandronuzziurjc/codeurjc-daw-2021-22-webapp3
# $2 = codeurjc-daw-2021-22-webapp3

docker pull $1 

docker tag $1 registry.heroku.com/$2/web

docker push registry.heroku.com/$2/web

heroku container:release web -a $2

heroku logs --tail -a $2
