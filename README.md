# Consulta de Repositorios de GitHub

Aplicación Java que permite consultar los repositorios públicos de un usuario de GitHub utilizando la API oficial de GitHub.

## Descripción

Esta aplicación de consola Java utiliza la biblioteca Retrofit para realizar solicitudes HTTP a la API de GitHub y recuperar información sobre los repositorios públicos de un usuario específico. La aplicación muestra el ID, URL y nombre completo de cada repositorio.

## Tecnologías utilizadas

- Java 17
- Maven
- Retrofit 3.0.0 (Cliente HTTP y convertidor de JSON)
- GSON (Convertidor JSON)

## Requisitos previos

- JDK 17 o superior
- Maven 3.6 o superior

## Instalación

1. Clona el repositorio:
   ```bash
   git clone [URL_DEL_REPOSITORIO]
   cd java-retrofit-client
   ```

2. Compila el proyecto con Maven:
   ```bash
   ./mvnw clean install
   ```

## Uso

Ejecuta la aplicación proporcionando un nombre de usuario de GitHub como argumento:

```bash
./mvnw exec:java -Dexec.args="nombre_usuario_github"
```

Por ejemplo:
```bash
./mvnw exec:java -Dexec.args="octocat"
```

## Estructura del proyecto

- `App.java`: Punto de entrada de la aplicación
- `Github.java`: Interfaz de Retrofit para la API de GitHub
- `ResponseGithub.java`: Modelo para los datos de respuesta de la API

