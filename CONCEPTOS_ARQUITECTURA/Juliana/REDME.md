# 🎬 MovieList App

Aplicación Android que consulta una API pública gratuita de películas usando **Retrofit** y muestra los resultados en un **RecyclerView**.

---

## ¿Qué hace esta app?

Consulta una API pública gratuita de películas usando Retrofit y muestra los resultados en un RecyclerView.

### Flujo de datos

```
API (JSON) → Retrofit → Gson → List<Movie> → MovieAdapter → RecyclerView → Pantalla
```

---

## 📁 Estructura del proyecto

```
app/src/main/
├── java/com/example/movielist/
│   ├── MainActivity.kt          ← Lógica principal + fetchMovies()
│   ├── Movie.kt                 ← Data class del modelo
│   ├── MovieApiService.kt       ← Interfaz de Retrofit con los endpoints
│   ├── RetrofitClient.kt        ← Singleton con la instancia de Retrofit
│   ├── MovieAdapter.kt          ← Adapter del RecyclerView
│   └── MovieViewHolder.kt       ← ViewHolder que vincula datos a vistas
├── res/
│   ├── layout/
│   │   ├── activity_main.xml    ← Layout principal con RecyclerView
│   │   └── item_movie.xml       ← Layout de cada tarjeta de película
│   └── values/
│       ├── colors.xml
│       ├── strings.xml
│       └── themes.xml
└── AndroidManifest.xml          ← Permiso de internet
```

### Vista de la app

![Vista general de la app](images/image1.png)

---

## 🚀 Pasos para crear la APP

### Paso 1 — Crea un Nuevo Proyecto en Android Studio

Inicia Android Studio y crea un nuevo proyecto. Asegúrate de seleccionar **Kotlin** como lenguaje de programación. Se usará el nombre **MovieList**.

![Crear nuevo proyecto en Android Studio](images/image2.png)

---

### Paso 2 — Configura los permisos de internet

Para que tu aplicación pueda acceder a internet y comunicarse con las APIs, es necesario añadir el permiso de internet en el archivo `AndroidManifest.xml`. Inserta el código dentro de la etiqueta `<manifest>`:

![Configurar permisos en AndroidManifest.xml](images/image3.png)

---

### Paso 3 — Agrega las dependencias en el archivo build.gradle

Retrofit requiere librerías adicionales para su funcionamiento. Abre el archivo `build.gradle` (a nivel de módulo, generalmente `app/build.gradle`) y añade las dependencias en la sección `dependencies`:

Estas dependencias permiten a Retrofit convertir los datos JSON en objetos Kotlin y gestionar las peticiones de forma asíncrona utilizando corrutinas. Después de añadir las dependencias, sincroniza el proyecto para que se descarguen las librerías.

![Dependencias en build.gradle](images/image4.png)

---

### Paso 4 — Crea la Data class para los datos de la API

Para que Retrofit pueda mapear la respuesta JSON de la API a objetos Kotlin, necesitas definir las data classes que representen la estructura de los datos.

Deberás crear las siguientes data classes en tu directorio principal (por ejemplo, en un archivo `MovieResponse.kt`):

![Data class MovieResponse](images/image5.png)

![Data class Movie](images/image6.png)

---

### Paso 5 — Crea la interfaz API para Retrofit

Define los métodos para interactuar con la API en una interfaz. Crea un archivo `MovieApiService.kt` y define la interfaz `MovieApiService`:

El método `getPopularMovies()` utiliza la anotación `@GET` para especificar el endpoint de la API (`"movies/popular"`). La palabra clave `suspend` indica que esta función es una corrutina y se ejecutará en segundo plano, lo que es fundamental para operaciones de red que no deben bloquear el hilo principal de la UI.

![Interfaz MovieApiService](images/image7.png)

---

### Paso 6 — Configura la instancia de Retrofit en Kotlin

Ve al archivo `MainActivity.kt` y configura la instancia de Retrofit que usarás en la aplicación.

La función `getRetrofit()` devuelve una instancia de Retrofit configurada con la URL base de la API y un convertidor JSON.

![Configurar instancia de Retrofit](images/image8.png)

---

### Paso 7 — Crea la función para hacer la solicitud de datos

En `MainActivity.kt`, implementa la lógica para llamar a la API y manejar la respuesta. Añade la función `fetchMovies()`:

Esta función utiliza `CoroutineScope(Dispatchers.IO).launch` para ejecutar la llamada a la API en un hilo de E/S, evitando bloquear la UI. Una vez que se recibe la respuesta, `runOnUiThread` se utiliza para actualizar la interfaz de usuario en el hilo principal, mostrando las películas o un mensaje de error según el resultado.

![Función fetchMovies](images/image9.png)

---

### Paso 8 — Configura el RecyclerView y el Adapter

Para mostrar las películas, necesitamos un RecyclerView. En el archivo `activity_main.xml`, agrega un RecyclerView con el ID `rvMovies`. Luego, crea una clase `MovieAdapter` para enlazar los datos con las vistas:

El `MovieAdapter` recibe una lista de películas y la adapta para cada elemento de la vista. También necesitarás un `MovieViewHolder` y un archivo de layout `item_movie.xml` para definir cómo se verá cada elemento de la lista.

![MovieAdapter y RecyclerView](images/image10.png)

---

### Paso 9 — Mostrar los datos en la pantalla

En `MainActivity.kt`, implementa la función `displayMovies()` para actualizar el RecyclerView con los datos obtenidos de la API:

Esta función inicializa el `MovieAdapter` con la lista de películas, establece un `LinearLayoutManager` para el RecyclerView y asigna el adaptador.

![Función displayMovies](images/image11.png)

---

### Paso 10 — Manejo de errores

Para informar al usuario sobre posibles problemas al cargar los datos, implementa una función `showError()` en `MainActivity.kt`:

Esta función muestra un mensaje `Toast` al usuario cuando ocurre un error durante la llamada a la API.

![Función showError](images/image12.png)

---

## 🛠️ Tecnologías utilizadas

- **Kotlin**
- **Android Studio**
- **Retrofit 2**
- **Gson Converter**
- **Coroutines**
- **RecyclerView**

---

## 📄 Licencia

Este proyecto es de uso educativo y libre distribución.
