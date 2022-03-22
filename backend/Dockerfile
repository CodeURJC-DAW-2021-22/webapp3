#################################################
# Imagen base para el contenedor de compilación
#################################################
FROM maven:3.6.3-openjdk-17 as builder

# Define el directorio de trabajo donde ejecutar comandos
WORKDIR /project

# Copia las dependencias del proyecto
COPY pom.xml /project/

# Descarga las dependencias del proyecto
# RUN mvn clean verify

# Copia el código del proyecto
COPY /src /project/src

# Compila proyecto
RUN mvn package

#################################################
# Imagen base para el contenedor de la aplicación
#################################################
FROM openjdk:17

# Define el directorio de trabajo donde se encuentra el JAR
WORKDIR /usr/src/app/

# Copia el JAR del contenedor de compilación
COPY --from=builder /project/target/*.jar /usr/src/app/


# Comando por defecto
# ENTRYPOINT [ "java", "-jar", "app-jar-with-dependencies.jar" ]

EXPOSE 8443

# Parametro por defecto
CMD [ "java", "-jar", "RukyGymasticAPP-0.0.1-SNAPSHOT.jar" ]