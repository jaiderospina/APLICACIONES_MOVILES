
# REST (Representational State Transfer)

**REST** (Representational State Transfer) es un estilo de arquitectura de software utilizado para crear servicios web. No es un protocolo (como el HTTP) ni un lenguaje de programación, sino un **conjunto de principios y restricciones** que permiten que diferentes sistemas en la red se comuniquen de forma ligera, escalable y eficiente.

Aquí te detallo sus componentes clave y por qué es el estándar actual para las API modernas:

### 1. El concepto de "Recurso"
En REST, todo es un **recurso**. Un recurso puede ser un usuario, una imagen, un documento o un dato específico. Cada recurso debe tener un identificador único, que generalmente es una **URL** (por ejemplo, `https://api.ejemplo.com/usuarios/123`).

### 2. Uso de verbos HTTP
REST aprovecha los métodos estándar del protocolo HTTP para realizar acciones sobre los recursos. Los más comunes son:

* **GET**: Recuperar información del recurso.
* **POST**: Crear un nuevo recurso.
* **PUT / PATCH**: Actualizar un recurso existente.
* **DELETE**: Eliminar un recurso.

### 3. Principios fundamentales
Para que una API sea considerada **RESTful**, debe cumplir con ciertas reglas:

* **Sin estado (Stateless):** El servidor no guarda ninguna sesión del cliente. Cada solicitud debe contener toda la información necesaria para ser procesada (por ejemplo, el token de autenticación).
* **Interfaz Uniforme:** Los recursos se manipulan de la misma manera en toda la API, facilitando que cualquier cliente pueda entender cómo interactuar con ella.
* **Arquitectura Cliente-Servidor:** Existe una separación clara; el cliente se encarga de la interfaz y la experiencia de usuario, mientras que el servidor se encarga del almacenamiento y la lógica de datos.
* **Cacheable:** Las respuestas deben indicar si los datos pueden ser almacenados en caché por el cliente para mejorar la velocidad.

---

### Comparación rápida: REST vs. Otros

| Característica | REST | SOAP |
| :--- | :--- | :--- |
| **Formato de datos** | JSON (preferido), XML, HTML, Texto | Solo XML |
| **Complejidad** | Ligero y fácil de implementar | Robusto pero complejo |
| **Protocolo** | Principalmente HTTP | HTTP, SMTP, TCP, etc. |

> **Nota técnica:** Aunque REST puede usar varios formatos, hoy en día el estándar de facto es el intercambio de datos en formato **JSON**, debido a su ligereza y facilidad de lectura tanto para humanos como para máquinas.


****

# DATA CLASS  **KOTLIN**

En el desarrollo con **Kotlin**, una **data class** (clase de datos) es una clase diseñada específicamente para un solo propósito: **almacenar datos**.

A diferencia de las clases normales en otros lenguajes (como Java), donde tienes que escribir mucho código repetitivo, Kotlin genera automáticamente las funciones estándar que necesitas para manipular esos datos.

### 1. ¿Qué hace que una `data class` sea especial?
Cuando declaras una clase con la palabra reservada `data`, el compilador crea por ti:

* **`equals()` / `hashCode()`**: Para comparar si dos objetos tienen el mismo contenido (no solo si son la misma instancia en memoria).
* **`toString()`**: Devuelve una cadena legible con los nombres de las propiedades y sus valores (ej: `Usuario(nombre=Jaider, id=1)`).
* **`copy()`**: Permite crear una copia del objeto cambiando solo algunas propiedades y manteniendo el resto igual (ideal para programación funcional e inmutabilidad).
* **`componentN()`**: Funciones que permiten la **destructuración**, es decir, extraer valores de forma directa: `val (nombre, id) = usuario`.

---

### 2. Ejemplo de Código
Comparado con una clase tradicional, la sintaxis es extremadamente limpia:

```kotlin
// Todo lo necesario en una sola línea
data class User(val name: String, val email: String, val id: Int)

fun main() {
    val user1 = User("Alex", "alex@mail.com", 1)
    
    // Uso de .copy() para crear un nuevo objeto basado en el anterior
    val user2 = user1.copy(email = "nuevo_email@mail.com")
    
    println(user1) // Imprime: User(name=Alex, email=alex@mail.com, id=1)
    println(user1 == user2) // Devuelve false (tienen distintos emails)
}
```

### 3. Requisitos para usarla
Para que una clase pueda ser una `data class`, debe cumplir con:
1.  El constructor primario debe tener **al menos un parámetro**.
2.  Todos los parámetros del constructor primario deben estar marcados como `val` (solo lectura) o `var` (lectura/escritura).
3.  La clase no puede ser `abstract`, `open`, `sealed` o `inner`.

---

### ¿Por qué usarlas en arquitecturas modernas?
Son perfectas para definir los **DTO** (*Data Transfer Objects*) en una API REST o para representar los modelos de una base de datos. Al ser concisas, reducen errores y hacen que el código sea mucho más fácil de mantener.


---

La arquitectura **MVVM** (Model-View-ViewModel) es un patrón de diseño de software cuyo objetivo principal es **separar la interfaz de usuario (UI) de la lógica de negocio**. 

Es el estándar recomendado por Google para el desarrollo moderno en Android, ya que facilita las pruebas unitarias, el mantenimiento y hace que el código sea mucho más organizado.

---

#  MVVM

### Los 3 Componentes de MVVM

#### 1. Model (El Modelo)
Representa los datos y la lógica de negocio de la aplicación.
* **Qué hace:** Obtiene datos de una base de datos (Room), de una API (Retrofit) o de cualquier otra fuente.
* **No sabe nada** de la interfaz de usuario. Solo se encarga de gestionar la información.

#### 2. View (La Vista)
Es lo que el usuario ve en la pantalla (Activities o Fragments).
* **Qué hace:** Su única responsabilidad es dibujar la interfaz y reaccionar a las interacciones del usuario (clics, gestos).
* **Regla de oro:** La Vista no contiene lógica compleja; solo observa los cambios que le indica el *ViewModel*.

#### 3. ViewModel (El Intermediario)
Es el "cerebro" que conecta el Modelo con la Vista.
* **Qué hace:** Transforma la información del Modelo en algo que la Vista pueda mostrar fácilmente. 
* **Estado:** Expone los datos mediante **LiveData** o **StateFlow**. La Vista se "suscribe" a estos datos y se actualiza automáticamente cuando el ViewModel cambia algo.
* **Supervivencia:** En Android, el ViewModel sobrevive a los cambios de configuración (como rotar la pantalla), evitando que los datos se pierdan.



---

### ¿Cómo se comunican entre ellos?

El flujo de comunicación es unidireccional y desacoplado:

1.  **El Usuario** interactúa con la **Vista** (ej. pulsa un botón de "Cargar").
2.  La **Vista** le avisa al **ViewModel**.
3.  El **ViewModel** le pide los datos al **Modelo** (ej. llama a una API).
4.  El **Modelo** responde al **ViewModel**.
5.  El **ViewModel** actualiza su estado interno (un LiveData).
6.  La **Vista**, que está "observando" ese LiveData, se entera del cambio y se refresca automáticamente.

---

### Ventajas principales

* **Mantenibilidad:** Si decides cambiar tu base de datos o tu API, solo tocas el Modelo; la Vista ni se entera.
* **Testabilidad:** Puedes probar la lógica del ViewModel sin necesidad de ejecutar un emulador de Android, porque el ViewModel no tiene dependencias de la UI.
* **Inmune a la rotación:** Al usar el `viewModelScope` y los ViewModels de Android, los datos no se reinician cuando el usuario gira el teléfono.

---

### Resumen Visual

| Componente | Responsabilidad |
| :--- | :--- |
| **View** | Mostrar datos y capturar eventos del usuario. |
| **ViewModel** | Preparar datos para la vista y manejar la lógica de estado. |
| **Model** | Gestionar los datos (Local/Remoto). |



---

# Uniendo las piezas.

¡Perfecto! Vamos a unir todas las piezas: **Retrofit** (red), **Data Classes** (datos), **Coroutines** (asincronía) y **MVVM** (arquitectura).

Imagina que queremos mostrar una lista de "Publicaciones" (Posts) desde una API. Así se vería la estructura completa:

---

### 1. El Modelo (Data Class + API Interface)
Primero definimos qué datos recibimos y cómo es el servicio de Retrofit.

```kotlin
// Data Class: Representa el recurso (Post)
data class Post(
    val id: Int,
    val title: String,
    val body: String
)

// Interfaz de Retrofit: Define los endpoints
interface PostApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post> // 'suspend' para usar con Coroutines
}
```

### 2. El ViewModel (Lógica y Coroutines)
El ViewModel se encarga de pedir los datos en un hilo secundario y exponerlos para que la Vista los vea.

```kotlin
class PostViewModel(private val apiService: PostApiService) : ViewModel() {

    // LiveData que la Vista va a observar
    val posts = MutableLiveData<List<Post>>()
    val isLoading = MutableLiveData<Boolean>()

    fun fetchPosts() {
        // Usamos viewModelScope: si se cierra la pantalla, la tarea se cancela sola
        viewModelScope.launch {
            isLoading.value = true
            try {
                // Cambiamos a Dispatchers.IO automáticamente gracias a Retrofit + suspend
                val result = apiService.getPosts() 
                posts.value = result // Actualizamos los datos
            } catch (e: Exception) {
                // Manejo de errores (ej. sin internet)
            } finally {
                isLoading.value = false
            }
        }
    }
}
```



### 3. La Vista (Activity / Fragment)
La Vista es "pasiva"; solo se suscribe a los cambios y los muestra.

```kotlin
class PostActivity : AppCompatActivity() {
    
    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 1. Observamos los posts: cuando cambien, se ejecuta este bloque
        viewModel.posts.observe(this) { listaDePosts ->
            // Aquí actualizarías tu RecyclerView o lista
            adapter.updateData(listaDePosts)
        }

        // 2. Observamos el estado de carga para mostrar un Spinner
        viewModel.isLoading.observe(this) { loading ->
            progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }

        // 3. Disparamos la acción
        viewModel.fetchPosts()
    }
}
```

---


### ¿Por qué esta combinación es tan potente?

 **Inmutabilidad:** Usamos `data class` para asegurar que los datos no cambien accidentalmente.
 **Eficiencia:** Las **Coroutines** con `Dispatchers.IO` hacen que la interfaz nunca se trabe mientras descarga los datos.
 **Orden:** Si el usuario gira la pantalla mientras se descargan los datos, el **ViewModel** mantiene la descarga activa y, al terminar la rotación, la **Vista** se vuelve a conectar y recibe los datos sin repetir la petición.
 **Seguridad:** El uso de `suspend` en Retrofit hace que el código sea **Type-safe** y mucho más fácil de leer que los antiguos *callbacks*.

---
 
# **Taller Individual**

Desarrollar , corregir y/o actualizar ( de ser requerido) y de manera individual el taller propuesto en :

- https://keepcoding.io/blog/retrofit-en-kotlin-para-consumir-apis/

Explicar cada una de las funciones implementadas en el contexto de su papel en la aplicación y posibles usos (ejemplificar).

---
