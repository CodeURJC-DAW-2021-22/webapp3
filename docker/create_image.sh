#!/bin/bash

if test $# -ne 1
then
	echo "Introducir argumento con el nombre de la imagen. Ejemplo: $0 <nombre_imagen>"
	exit 1;
fi

#Directory change from /docker to /frontend/rukygym
cd ../frontend/rukygym

#Install node modules
npm install

#Build project
ng build --prod --base-href="/new/"

#Copy to src/main/resources/public/new
cp -r dist/rukygym/* ../../backend/src/main/resources/public/new

#Directory change from /docker to /backend
cd ../../backend

#Verify directory change
echo "Directorio actual cambiado a $PWD"
#docker run --rm -v "$PWD":/data -w /data maven mvn package

#Image creation
docker build -f ../docker/dockerfile -t alessandronuzziurjc/$1 .

#Image push to DockerHub
docker push alessandronuzziurjc/$1

exit 0;