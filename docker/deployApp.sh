#!/bin/bash

docker pull alessandronuzziurjc/codeurjc-daw-2021-22-webapp3

docker tag alessandronuzziurjc/codeurjc-daw-2021-22-webapp3 registry.heroku.com/codeurjc-daw-2021-22-webapp3/web

docker push registry.heroku.com/codeurjc-daw-2021-22-webapp3/web

heroku container:release web -a codeurjc-daw-2021-22-webapp3

heroku logs --tail -a codeurjc-daw-2021-22-webapp3