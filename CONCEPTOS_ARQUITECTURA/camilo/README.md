# 🎬 MovieApp - Android

Aplicación Android para mostrar películas populares usando la API de TMDB (The Movie Database), construida con Kotlin y Jetpack Compose.

---

## 🛠️ Tecnologías utilizadas

- **Kotlin** — Lenguaje de programación principal
- **Jetpack Compose** — UI declarativa moderna para Android
- **Retrofit 2.9.0** — Cliente HTTP para consumir APIs REST
- **Gson** — Conversión automática de JSON a objetos Kotlin
- **Coroutines** — Manejo de operaciones asíncronas sin bloquear la UI
- **TMDB API** — Fuente de datos de películas populares

---

## 🚀 Paso a paso — Creación del proyecto

### Paso 1 — Seleccionar plantilla

Al abrir Android Studio se crea un nuevo proyecto. Se selecciona la plantilla **Empty Activity** que viene preconfigurada con Jetpack Compose. Esta plantilla es la más adecuada para construir interfaces modernas desde cero en Android.

<img src="https://raw.githubusercontent.com/jaiderospina/APLICACIONES_MOVILES/main/CONCEPTOS_ARQUITECTURA/camilo/imagenes/seleccionar%20actividad.png" width="620"/>

---

### Paso 2 — Nombre y configuración del proyecto

Se define el nombre de la aplicación como **MovieApp** y el paquete `com.example.movieapp`. El SDK mínimo se establece en **API 24 (Android 7.0)**, lo que garantiza compatibilidad con el 99.2% de los dispositivos Android en el mercado. Como lenguaje de configuración de Gradle se usa **Kotlin DSL**, que es el estándar recomendado actualmente por Google.

<img src="https://raw.githubusercontent.com/jaiderospina/APLICACIONES_MOVILES/main/CONCEPTOS_ARQUITECTURA/camilo/imagenes/nombre%20y%20lenguaje.png" width="620"/>

---

### Paso 3 — Agregar permisos de Internet

Por defecto Android bloquea el acceso a Internet por razones de seguridad. Para habilitarlo se debe declarar explícitamente el permiso `INTERNET` en el archivo `AndroidManifest.xml`. Sin este paso, cualquier petición a la API fallará con un error de red, sin importar que la conexión esté activa.

<img src="https://raw.githubusercontent.com/jaiderospina/APLICACIONES_MOVILES/main/CONCEPTOS_ARQUITECTURA/camilo/imagenes/permiso%20internet.png" width="620"/>

---

### Paso 4 — Agregar dependencias

En el archivo `build.gradle.kts` se agregan las librerías externas necesarias. **Retrofit** es la librería que permite hacer peticiones HTTP de forma sencilla. **Gson Converter** traduce automáticamente la respuesta JSON de la API a objetos Kotlin. **Coroutines** permite ejecutar las peticiones en un hilo secundario para que la interfaz de usuario no se congele mientras espera la respuesta del servidor.

<img src="https://raw.githubusercontent.com/jaiderospina/APLICACIONES_MOVILES/main/CONCEPTOS_ARQUITECTURA/camilo/imagenes/dependencias.png" width="620"/>

---

## 📁 Creación de clases

### Paso 5 — Crear una nueva clase Kotlin

Para agregar nuevas clases al proyecto se hace clic derecho sobre el paquete principal → **New → Kotlin Class/File**. Desde este menú se puede elegir si la clase será una Class, Interface, Data Class, entre otras. Cada tipo tiene un propósito diferente dentro de la arquitectura de la app.

<img src="https://raw.githubusercontent.com/jaiderospina/APLICACIONES_MOVILES/main/CONCEPTOS_ARQUITECTURA/camilo/imagenes/crear%20clase%20tipo%20kotlin.png" width="500"/>

---

### Paso 6 — Crear el modelo Movie

Se crea `Movie.kt` seleccionando el tipo **Class**. Esta clase es un modelo de datos que representa una película. Cada propiedad de la clase corresponde a un campo del JSON que devuelve la API de TMDB: el título, la ruta del póster y la calificación promedio.

<img src="https://raw.githubusercontent.com/jaiderospina/APLICACIONES_MOVILES/main/CONCEPTOS_ARQUITECTURA/camilo/imagenes/movie%20class.png" width="420"/>

Una vez creada la clase, se definen sus propiedades con los tipos de datos correctos y las anotaciones `@SerializedName` que conectan cada campo del JSON con su propiedad en Kotlin.

<img src="https://raw.githubusercontent.com/jaiderospina/APLICACIONES_MOVILES/main/CONCEPTOS_ARQUITECTURA/camilo/imagenes/cod%20movie%20class.png" width="620"/>

---

### Paso 7 — Crear la interfaz de la API

Se crea `MovieApiService.kt` seleccionando el tipo **Interface**. En Retrofit, una interfaz define los endpoints disponibles de la API. Cada función dentro de la interfaz representa una petición HTTP diferente. En este caso se define un único endpoint para obtener las películas populares, incluyendo la API key y el idioma de respuesta directamente en la URL.

<img src="https://raw.githubusercontent.com/jaiderospina/APLICACIONES_MOVILES/main/CONCEPTOS_ARQUITECTURA/camilo/imagenes/interfaz%20api.png" width="420"/>

---

### Paso 8 — Configurar Retrofit

Se configura la instancia de Retrofit con la URL base de la API de TMDB `https://api.themoviedb.org/3/`. El `GsonConverterFactory` se encarga de deserializar automáticamente el JSON recibido y convertirlo en los objetos `Movie` y `MovieResponse` definidos anteriormente. Esta configuración centraliza todas las peticiones HTTP de la app.

<img src="https://raw.githubusercontent.com/jaiderospina/APLICACIONES_MOVILES/main/CONCEPTOS_ARQUITECTURA/camilo/imagenes/retrofit.png" width="620"/>

---

### Paso 9 — Realizar la petición a la API

La función `fetchMovies()` ejecuta la petición al endpoint de películas populares dentro de una corrutina en el hilo `Dispatchers.IO`, diseñado específicamente para operaciones de red. Una vez recibida la respuesta, se vuelve al hilo principal con `runOnUiThread` para actualizar la interfaz. Si la respuesta es exitosa se carga la lista de películas; si ocurre un error de red se muestra un mensaje descriptivo al usuario.

<img src="https://raw.githubusercontent.com/jaiderospina/APLICACIONES_MOVILES/main/CONCEPTOS_ARQUITECTURA/camilo/imagenes/peticion.png" width="620"/>

---

## 🔑 Obtener API Key de TMDB

Para que la app pueda consultar la base de datos de películas es necesario tener una API Key válida de TMDB. El proceso es gratuito:

1. Regístrate en [themoviedb.org](https://www.themoviedb.org/signup)
2. Ve a **Configuración → API**
3. Selecciona **Developer** y completa el formulario
4. Copia la **API Key (v3 auth)**
5. Reemplaza `TU_API_KEY` en `MovieApiService.kt`

---

## 📱 Funcionalidades de la app

- ✅ Lista de películas populares con scroll
- ✅ Muestra título y calificación de cada película
- ✅ Indicador de carga mientras llegan los datos
- ✅ Manejo de errores de conexión
- ✅ Contenido en español con `language=es-ES`

