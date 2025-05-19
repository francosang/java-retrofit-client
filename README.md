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
- `RepoResponse.java`: Modelo para los datos de respuesta de la API

## Extendiendo el cliente de GitHub

### Cómo agregar nuevas requests al cliente

Para agregar nuevas solicitudes a la API de GitHub, debes modificar la interfaz `Github.java`:

1. Identifica el endpoint de la API de GitHub que deseas utilizar. Consulta la [documentación oficial de la API de GitHub](https://docs.github.com/es/rest) para conocer todos los endpoints disponibles.

2. Agrega un nuevo método a la interfaz `Github.java` siguiendo este patrón:

   ```java
   @GET("endpoint/path")
   Call<TipoRespuesta> nombreMetodo(@Path("parametro") String parametro);
   ```

   Ejemplos:

   ```java
   // Obtener información de un usuario
   @GET("users/{username}")
   Call<UserResponse> getUserInfo(@Path("username") String username);

   // Obtener los gists públicos de un usuario
   @GET("users/{username}/gists")
   Call<List<GistResponse>> listGists(@Path("username") String username);
   
   // Obtener los seguidores de un usuario
   @GET("users/{username}/followers")
   Call<List<UserResponse>> getFollowers(@Path("username") String username);
   ```

3. Puedes usar diferentes anotaciones según el tipo de petición HTTP:
   - `@GET`: Para solicitudes GET
   - `@POST`: Para solicitudes POST
   - `@PUT`: Para solicitudes PUT
   - `@DELETE`: Para solicitudes DELETE

4. Para incluir parámetros de consulta, utiliza la anotación `@Query`:

   ```java
   @GET("search/repositories")
   Call<SearchResponse> searchRepos(@Query("q") String query, @Query("sort") String sort);
   ```

5. Para solicitudes que requieren un cuerpo JSON, utiliza la anotación `@Body`:

   ```java
   @POST("repos/{owner}/{repo}/issues")
   Call<IssueResponse> createIssue(@Path("owner") String owner, @Path("repo") String repo, @Body Issue issue);
   ```

### Cómo crear clases para las respuestas

Para cada tipo de respuesta de la API, necesitas crear una clase modelo que corresponda a la estructura JSON devuelta:

1. Crea una nueva clase Java en el paquete `com.jfranco` (por ejemplo, `UserResponse.java`).

2. Define los campos que deseas extraer de la respuesta JSON:

   ```java
   package com.jfranco;

   public class UserResponse {
       private String login;
       private long id;
       private String avatarUrl;
       private String name;
       private String bio;
       private int followers;
       private int following;

       // Constructor
       public UserResponse(String login, long id, String avatarUrl, String name, String bio, int followers, int following) {
           this.login = login;
           this.id = id;
           this.avatarUrl = avatarUrl;
           this.name = name;
           this.bio = bio;
           this.followers = followers;
           this.following = following;
       }

       // Getters
       public String getLogin() { return login; }
       public long getId() { return id; }
       public String getAvatarUrl() { return avatarUrl; }
       public String getName() { return name; }
       public String getBio() { return bio; }
       public int getFollowers() { return followers; }
       public int getFollowing() { return following; }

       @Override
       public String toString() {
           return "UserResponse{" +
                   "login='" + login + '\'' +
                   ", id=" + id +
                   ", name='" + name + '\'' +
                   ", followers=" + followers +
                   ", following=" + following +
                   '}';
       }
   }
   ```

3. Asegúrate de que los nombres de los campos coincidan con los nombres en la respuesta JSON de la API de GitHub (utilizando camelCase), o configura GSON para que gestione la conversión de nombres:

   ```java
   // En App.java:
   .addConverterFactory(
       GsonConverterFactory.create(new GsonBuilder()
           .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
           .create()))
   ```

   Esta configuración permite que GSON convierta automáticamente los nombres de campos en snake_case de la API (como `avatar_url`) a camelCase en Java (`avatarUrl`).

4. Para respuestas más complejas con estructuras anidadas, puedes crear clases internas o clases separadas para cada componente.

### Ejemplo de uso

Una vez que hayas agregado un nuevo método a la interfaz y la clase de respuesta correspondiente, puedes utilizarlo en `App.java`:

```java
// Obtener información de un usuario
final String userName = args[0];
final UserResponse user = service.getUserInfo(userName).execute().body();
System.out.println("Usuario: " + user.getName() + " (@" + user.getLogin() + ")");
System.out.println("Seguidores: " + user.getFollowers());

// Obtener seguidores
final List<UserResponse> followers = service.getFollowers(userName).execute().body();
System.out.println("Lista de seguidores:");
followers.forEach(follower -> {
    System.out.println("- " + follower.getLogin());
});
```

