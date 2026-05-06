# 🗄️ Introducción a SQLite en Android

SQLite es el motor de base de datos relacional integrado en Android. No requiere instalación adicional; cada dispositivo ya lo tiene listo para usar. En este capítulo aprenderás su funcionamiento interno, estructura y casos de uso.

---

## 1. ¿Qué es SQLite?

**SQLite** es un motor de base de datos relacional que se caracteriza por ser:
*   **Ligero:** La librería ocupa apenas ~500 KB.
*   **Embebido:** No necesita un proceso de servidor separado.
*   **Monolítico:** Almacena toda la base de datos en un **único archivo `.db`**.
*   **Estándar:** Utiliza lenguaje SQL para las consultas.

### 🔌 Diferencia: Cliente-Servidor vs. Embebido

A diferencia de sistemas como MySQL o PostgreSQL, SQLite vive dentro de tu aplicación:

**MySQL/PostgreSQL (Cliente-Servidor)**
> Tu App (Cliente) ───🌐───► Servidor MySQL ──► [Base de Datos]

**SQLite (Embebido)**
> [ Tu App [ Librería SQLite ──► archivo.db ] ]

**Ventaja:** Cero configuración. Todo reside en el almacenamiento privado de tu aplicación.

---

## 2. Integración en Android

Android incluye SQLite desde su primera versión. Las herramientas principales del SDK son:

| Clase | Propósito |
| :--- | :--- |
| `SQLiteOpenHelper` | Gestiona la creación, apertura y actualización de versiones de la BD. |
| `SQLiteDatabase` | Clase base para ejecutar sentencias SQL (Insert, Update, Delete, RawQuery). |
| `Cursor` | Interfaz que permite navegar por los resultados de una consulta `SELECT`. |
| `ContentValues` | Almacén de pares clave-valor usados para insertar o editar filas. |

### 📂 Ubicación de los archivos
Las bases de datos se guardan en una ruta privada:
`/data/data/[paquete.de.tu.app]/databases/nombre_base_datos.db`

> [!IMPORTANT]
> Esta ubicación es **privada**. Otras apps no pueden leer tus datos a menos que el dispositivo tenga acceso Root.

---

## 3. Tipos de Datos y Mapeo

SQLite utiliza un sistema de tipos flexible. Aquí su equivalencia con **Kotlin**:

| Tipo SQLite | Descripción | Ejemplo | Kotlin |
| :--- | :--- | :--- | :--- |
| **INTEGER** | Números enteros | `42` | `Int`, `Long` |
| **REAL** | Números decimales | `3.14` | `Float`, `Double` |
| **TEXT** | Cadenas de texto | `"Hola"` | `String` |
| **BLOB** | Datos binarios | Imágenes | `ByteArray` |
| **NULL** | Valor nulo | `null` | `null` |

**Nota sobre Fechas:** SQLite no tiene un tipo `DATE`. Se recomienda usar **TEXT** con formato ISO 8601 (`"yyyy-MM-dd HH:mm:ss"`) o **INTEGER** como Unix Timestamp.

---

## 4. Estructura de una Tabla

Para nuestro proyecto de **Intentos Fallidos de Login**, la estructura SQL es:

```sql
CREATE TABLE intentos_fallidos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    usuario TEXT NOT NULL,
    contrasena TEXT NOT NULL,
    fecha_hora TEXT NOT NULL
);
```

### Restricciones comunes:
*   `PRIMARY KEY`: Identificador único.
*   `AUTOINCREMENT`: El ID sube automáticamente (1, 2, 3...).
*   `NOT NULL`: El campo es obligatorio.

---

## 5. Operaciones CRUD

| Letra | Operación | SQL |
| :---: | :--- | :--- |
| **C** | **Create** | `INSERT INTO ...` |
| **R** | **Read** | `SELECT ...` |
| **U** | **Update** | `UPDATE ...` |
| **D** | **Delete** | `DELETE ...` |

---

## 6. El Ciclo de Vida de la BD

Android gestiona la base de datos mediante **versiones numéricas**.



### Métodos Clave:
1.  **`onCreate()`**: Se ejecuta **solo la primera vez** que se crea el archivo `.db`. Aquí se definen las tablas.
2.  **`onUpgrade()`**: Se ejecuta cuando incrementas el número de versión en el código. Sirve para añadir columnas o migrar datos sin borrar lo existente.

---

## 7. ¿Cuándo usar SQLite?

✅ **Usar si:**
*   Necesitas persistencia **offline** (sin internet).
*   Manejas datos privados del usuario.
*   Necesitas un caché de datos descargados de una API.

❌ **Evitar si:**
*   Necesitas sincronización en tiempo real entre múltiples dispositivos (usa **Firebase**).
*   Guardas archivos masivos como videos (usa el **FileSystem**).
*   Son configuraciones simples de "Sí/No" (usa **SharedPreferences**).

---

## 8. Herramientas de Depuración

No necesitas adivinar qué hay en la base de datos. Usa estas herramientas:

1.  **App Inspection (Android Studio):**
    *   Ve a `View` -> `Tool Windows` -> `App Inspection`.
    *   Te permite ver tablas y ejecutar SQL en vivo mientras la app corre.
2.  **DB Browser for SQLite:**
    *   Extrae el archivo `.db` mediante el **Device File Explorer**.
    *   Ábrelo en tu PC con [SQLite Browser](https://sqlitebrowser.org/).

---
# 🏗️ Implementación de SQLiteOpenHelper

En Android, la gestión de la base de datos se centraliza en la clase `SQLiteOpenHelper`. Esta guía detalla cómo crear un asistente de base de datos robusto utilizando el patrón Singleton y clases de datos en Kotlin.

---

## 1. ¿Qué es SQLiteOpenHelper?

Es una **clase abstracta** del SDK de Android diseñada para gestionar el ciclo de vida de la base de datos.

### Responsabilidades principales:
*   **Creación:** Genera el archivo `.db` si no existe.
*   **Inicialización:** Ejecuta el método `onCreate` para definir el esquema (tablas).
*   **Evolución:** Gestiona actualizaciones de esquema mediante `onUpgrade`.
*   **Conectividad:** Proporciona accesos de lectura (`getReadableDatabase`) y escritura (`getWritableDatabase`).



---

## 2. Creación de DatabaseHelper.kt

Ubicación recomendada: `package com.ejemplo.loginapp.database`

### Definición de Constantes
Usamos un `companion object` para centralizar los nombres de tablas y columnas, evitando errores de escritura (typos) en las consultas SQL.

```kotlin
companion object {
    private const val DATABASE_NAME = "intentos_login.db"
    private const val DATABASE_VERSION = 1
    
    const val TABLE_INTENTOS = "intentos_fallidos"
    const val COLUMN_ID = "id"
    const val COLUMN_USUARIO = "usuario"
    const val COLUMN_CONTRASENA = "contrasena"
    const val COLUMN_FECHA_HORA = "fecha_hora"
}
```

---

## 3. El Ciclo de Vida: onCreate y onUpgrade

### onCreate()
Se ejecuta **una sola vez** cuando la base de datos se crea por primera vez.
```kotlin
override fun onCreate(db: SQLiteDatabase) {
    val createTableSQL = """
        CREATE TABLE $TABLE_INTENTOS (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_USUARIO TEXT NOT NULL,
            $COLUMN_CONTRASENA TEXT NOT NULL,
            $COLUMN_FECHA_HORA TEXT NOT NULL
        )
    """.trimIndent()
    db.execSQL(createTableSQL)
}
```

### onUpgrade()
Se activa cuando `DATABASE_VERSION` aumenta. 
*   **Desarrollo:** Podemos usar `DROP TABLE` para recrear todo.
*   **Producción:** Se deben usar sentencias `ALTER TABLE` para no borrar los datos del usuario.

---

## 4. Patrón Singleton (Recomendado)

Para evitar fugas de memoria (memory leaks) y problemas de concurrencia, implementamos `DatabaseHelper` como una instancia única.

```kotlin
class DatabaseHelper private constructor(context: Context) : SQLiteOpenHelper(
    context.applicationContext, // Importante: evita memory leaks
    DATABASE_NAME, null, DATABASE_VERSION
) {
    companion object {
        @Volatile
        private var instance: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper {
            return instance ?: synchronized(this) {
                instance ?: DatabaseHelper(context).also { instance = it }
            }
        }
    }
}
```

---

## 5. Operaciones CRUD Integradas

### Inserción (Create)
Utilizamos `ContentValues` para mapear los datos de Kotlin a las columnas de SQL de forma segura.

```kotlin
fun insertarIntento(usuario: String, contrasena: String): Long {
    val db = writableDatabase
    val values = ContentValues().apply {
        put(COLUMN_USUARIO, usuario)
        put(COLUMN_CONTRASENA, contrasena)
        put(COLUMN_FECHA_HORA, obtenerFechaHoraActual())
    }
    return db.insert(TABLE_INTENTOS, null, values)
}
```

### Consulta (Read)
El `Cursor` nos permite iterar sobre los resultados. Es vital usar `.use {}` o cerrar el cursor manualmente para liberar memoria.

```kotlin
fun obtenerTodosLosIntentos(): List<IntentoFallido> {
    val lista = mutableListOf<IntentoFallido>()
    val db = readableDatabase
    val cursor = db.query(TABLE_INTENTOS, null, null, null, null, null, "$COLUMN_FECHA_HORA DESC")
    
    cursor.use {
        while (it.moveToNext()) {
            lista.add(IntentoFallido(
                id = it.getInt(it.getColumnIndexOrThrow(COLUMN_ID)),
                usuario = it.getString(it.getColumnIndexOrThrow(COLUMN_USUARIO)),
                contrasena = it.getString(it.getColumnIndexOrThrow(COLUMN_CONTRASENA)),
                fechaHora = it.getString(it.getColumnIndexOrThrow(COLUMN_FECHA_HORA))
            ))
        }
    }
    return lista
}
```

---

## 6. Representación de Datos: Data Class

Para manejar los registros de forma limpia en Kotlin, definimos un modelo de datos inmutable:

```kotlin
data class IntentoFallido(
    val id: Int,
    val usuario: String,
    val contrasena: String,
    val fechaHora: String
)
```

---

## 7. Verificación (Prueba de Concepto)

Puedes verificar el funcionamiento en tu `MainActivity` observando el **Logcat**:

```kotlin
private fun testDatabase() {
    val dbHelper = DatabaseHelper.getInstance(this)
    dbHelper.insertarIntento("admin", "root123")
    
    val intentos = dbHelper.obtenerTodosLosIntentos()
    intentos.forEach { 
        Log.d("DB_TEST", "Usuario: ${it.usuario} falló en ${it.fechaHora}") 
    }
}
```

---

## 🏁 Puntos Clave
*   **Contexto:** Siempre usa `applicationContext` en el Singleton.
*   **Seguridad:** `ContentValues` previene ataques de inyección SQL básicos.
*   **Concurrencia:** `@Volatile` y `synchronized` aseguran que solo exista una conexión a la BD.
*   **Limpieza:** El uso de `getColumnIndexOrThrow` asegura que estamos accediendo a columnas existentes.

**Próximo paso:** [Optimización de consultas y filtrado avanzado ➡️](#)

## 🏁 Resumen
*   SQLite es **nativo** y **embebido** en Android.
*   Los datos son **privados** y se guardan en un solo archivo.
*   `SQLiteOpenHelper` es el corazón de la gestión de la BD.
*   Se usa SQL estándar para manipular la información.

---

Aquí tienes el contenido organizado y formateado en un archivo **README.md** detallado, enfocado en la implementación práctica de las operaciones CRUD en Android con Kotlin.

---

# 💾 Operaciones CRUD en SQLite

| ⏱️ Duración estimada | Nivel | Conceptos Clave |
| :--- | :--- | :--- |
| 25 min | Intermedio | Create, Read, Update, Delete |

CRUD representa las cuatro operaciones fundamentales para la gestión de persistencia de datos. En Android, utilizamos la clase `SQLiteDatabase` junto con `ContentValues` y `Cursor` para interactuar con el motor de base de datos.

---

## 1. CREATE: Insertar Datos

Para insertar registros de forma segura, Android utiliza la clase **`ContentValues`**, que funciona como un mapa de pares clave-valor (donde la clave es el nombre de la columna).

### Método `insert()`
```kotlin
fun insertarIntento(usuario: String, contrasena: String): Long {
    val db = writableDatabase
    val values = ContentValues().apply {
        put(COLUMN_USUARIO, usuario)
        put(COLUMN_CONTRASENA, contrasena)
        put(COLUMN_FECHA_HORA, obtenerFechaHoraActual())
    }
    // Retorna el ID del nuevo registro o -1 si hubo un error
    return db.insert(TABLE_INTENTOS, null, values)
}
```

> [!TIP]
> Para insertar **múltiples registros**, usa una **Transacción** (`db.beginTransaction()`). Esto mejora drásticamente el rendimiento y asegura la integridad de los datos.

---

## 2. READ: Consultar Datos

La consulta devuelve un objeto **`Cursor`**, que actúa como un puntero posicionado antes de la primera fila de resultados.



### Método `query()` y uso del Cursor
```kotlin
fun obtenerTodosLosIntentos(): List<IntentoFallido> {
    val lista = mutableListOf<IntentoFallido>()
    val db = readableDatabase
    val cursor = db.query(
        TABLE_INTENTOS, null, null, null, null, null, "$COLUMN_FECHA_HORA DESC"
    )

    // .use asegura que el cursor se cierre automáticamente al terminar
    cursor.use { c ->
        while (c.moveToNext()) {
            val intento = IntentoFallido(
                id = c.getInt(c.getColumnIndexOrThrow(COLUMN_ID)),
                usuario = c.getString(c.getColumnIndexOrThrow(COLUMN_USUARIO)),
                contrasena = c.getString(c.getColumnIndexOrThrow(COLUMN_CONTRASENA)),
                fechaHora = c.getString(c.getColumnIndexOrThrow(COLUMN_FECHA_HORA))
            )
            lista.add(intento)
        }
    }
    return lista
}
```

### 🛡️ Seguridad: SQL Injection
Nunca concatenes strings en el `WHERE`. Usa placeholders (`?`) y `selectionArgs`:
```kotlin
// FORMA SEGURA ✅
val selection = "$COLUMN_USUARIO = ?"
val selectionArgs = arrayOf("juan")
db.query(TABLE_INTENTOS, null, selection, selectionArgs, null, null, null)
```

---

## 3. UPDATE: Actualizar Registros

El método `update()` combina el uso de `ContentValues` (nuevos datos) y una cláusula `WHERE` (qué filas modificar).

```kotlin
fun actualizarContrasena(id: Int, nuevaContrasena: String): Int {
    val db = writableDatabase
    val values = ContentValues().apply {
        put(COLUMN_CONTRASENA, nuevaContrasena)
    }
    // Retorna el número de filas afectadas
    return db.update(
        TABLE_INTENTOS, 
        values, 
        "$COLUMN_ID = ?", 
        arrayOf(id.toString())
    )
}
```

---

## 4. DELETE: Eliminar Registros

El método `delete()` requiere el nombre de la tabla y una condición. Si la condición es `null`, se borrarán **todos** los registros de la tabla.

```kotlin
fun eliminarIntento(id: Int): Int {
    val db = writableDatabase
    return db.delete(
        TABLE_INTENTOS, 
        "$COLUMN_ID = ?", 
        arrayOf(id.toString())
    )
}
```

---

## 5. Consultas Personalizadas con `rawQuery()`

Para operaciones complejas como `GROUP BY` o `COUNT(*)`, puedes usar SQL puro:

```kotlin
fun contarIntentos(): Int {
    val db = readableDatabase
    val cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_INTENTOS", null)
    return cursor.use { if (it.moveToFirst()) it.getInt(0) else 0 }
}
```

---

## 📊 Resumen de Retornos

| Operación | Método | Retorno Exitoso | Error / Fallo |
| :--- | :--- | :--- | :--- |
| **INSERT** | `db.insert()` | ID del registro (Long) | `-1L` |
| **SELECT** | `db.query()` | Objeto `Cursor` | `Cursor` vacío o Excepción |
| **UPDATE** | `db.update()` | Cantidad de filas (Int) | `0` filas afectadas |
| **DELETE** | `db.delete()` | Cantidad de filas (Int) | `0` filas eliminadas |

---

## 🏁 Buenas Prácticas para Recordar
1.  **Cierre de Recursos:** Siempre envuelve tus cursores en `.use { ... }` para evitar fugas de memoria.
2.  **Mapeo Seguro:** Prefiere `getColumnIndexOrThrow()` sobre `getColumnIndex()` para detectar errores de esquema rápidamente.
3.  **Hilos:** No realices operaciones de base de datos en el hilo principal (UI Thread). Usa `CoroutineScope` o `AsyncTask` (aunque este último está depreciado).
4.  **Placeholders:** Usa siempre `?` para los valores de búsqueda para proteger tu app contra ataques de inyección SQL.

---

# 🏗️ Data Classes y Modelos de Datos

| ⏱️ Duración estimada | Nivel | Conceptos Clave |
| :--- | :--- | :--- |
| 15 min | Intermedio | Kotlin Data Classes, Mapeo de Datos, Extension Functions |

En el desarrollo de Android, las **Data Classes** de Kotlin actúan como el puente entre la base de datos (SQLite/MySQL) y la lógica de tu aplicación. Permiten tratar las filas de una tabla como objetos de primer nivel, limpios y fáciles de manipular.

---

## 1. ¿Qué es una Data Class?

Una `data class` es una clase diseñada exclusivamente para contener datos. Al marcar una clase con la palabra clave `data`, Kotlin genera automáticamente:
*   **`equals()` / `hashCode()`**: Para comparar si dos objetos tienen los mismos datos.
*   **`toString()`**: Genera una representación legible: `IntentoFallido(id=1, usuario=juan...)`.
*   **`copy()`**: Permite clonar un objeto cambiando solo algunas propiedades.
*   **Destructuring**: Permite extraer valores rápidamente: `val (id, user) = intento`.



---

## 2. Diseño del Modelo: `IntentoFallido`

Para nuestro proyecto, el modelo debe reflejar fielmente la tabla de SQLite pero añadiendo la potencia de Kotlin:

```kotlin
data class IntentoFallido(
    val id: Int = 0, // 0 indica que el registro aún no se ha guardado (ID autogenerado)
    val usuario: String,
    val contrasena: String,
    val fechaHora: String
) {
    // Propiedad calculada: No ocupa espacio en la DB, pero es útil en la UI
    val contrasenaOculta: String
        get() = if (contrasena.length > 2) {
            "${contrasena.take(2)}${"*".repeat(contrasena.length - 2)}"
        } else "**"

    init {
        // Validación: No permitimos objetos con datos corruptos
        require(usuario.isNotBlank()) { "El usuario no puede estar vacío" }
    }
}
```

---

## 3. Mapeo Eficiente: Funciones de Extensión

Para evitar repetir el código de extracción del `Cursor` en cada consulta, creamos **Extension Functions**. Esto hace que el código de tu `DatabaseHelper` sea mucho más legible.



### `CursorExtensions.kt`
```kotlin
/** Convierte la fila actual del Cursor a un objeto IntentoFallido */
fun Cursor.toIntentoFallido(): IntentoFallido {
    return IntentoFallido(
        id = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)),
        usuario = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_USUARIO)),
        contrasena = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_CONTRASENA)),
        fechaHora = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_FECHA_HORA))
    )
}

/** Itera todo el Cursor y devuelve una lista de objetos */
fun Cursor.toIntentosFallidosList(): List<IntentoFallido> {
    val lista = mutableListOf<IntentoFallido>()
    this.use { // .use cierra el cursor automáticamente
        while (it.moveToNext()) {
            lista.add(it.toIntentoFallido())
        }
    }
    return lista
}
```

---

## 4. De Objeto a Base de Datos (`ContentValues`)

Para realizar inserciones o actualizaciones, necesitamos el proceso inverso: convertir nuestra Data Class en un objeto `ContentValues`.

```kotlin
fun IntentoFallido.toContentValues(): ContentValues {
    return ContentValues().apply {
        // El ID no se pone porque SQLite lo autogenera
        put(DatabaseHelper.COLUMN_USUARIO, usuario)
        put(DatabaseHelper.COLUMN_CONTRASENA, contrasena)
        put(DatabaseHelper.COLUMN_FECHA_HORA, fechaHora)
    }
}
```

### Uso en `DatabaseHelper`:
```kotlin
fun insertar(intento: IntentoFallido): Long {
    return writableDatabase.insert(TABLE_INTENTOS, null, intento.toContentValues())
}
```

---

## 5. Modelos para Datos Externos (JSON/MySQL)

Cuando trabajas con una base de datos remota (MySQL), los datos suelen llegar en formato JSON. Podemos usar un `companion object` para crear "fábricas" de objetos:

```kotlin
data class UsuarioRemoto(
    val nombre: String,
    val correo: String
) {
    companion object {
        fun fromJson(json: JSONObject): UsuarioRemoto {
            return UsuarioRemoto(
                nombre = json.getString("username"),
                correo = json.getString("email")
            )
        }
    }
}
```

---

## 📊 Comparativa de Estrategias para IDs

Al crear un objeto nuevo que aún no existe en la DB, el campo `id` es un reto. Aquí las mejores prácticas:

| Estrategia | Ejemplo | Pros | Contras |
| :--- | :--- | :--- | :--- |
| **Valor por defecto** | `id: Int = 0` | Simple, evita nulls. | `0` es un ID válido en SQL (aunque raro). |
| **ID Nullable** | `id: Int? = null` | Semánticamente correcto. | Obliga a usar `!!` o `?` en el código. |
| **Dos clases** | `NuevoIntento` / `Intento` | Máxima seguridad de tipos. | Duplica el código del modelo. |

---

## 🏁 Puntos Clave para Recordar
1.  **Inmutabilidad:** Usa `val` siempre que sea posible. Si necesitas cambiar algo, usa `.copy()`.
2.  **Encapsulamiento:** Las validaciones (`init`) aseguran que tus datos sean consistentes antes de llegar a la base de datos.
3.  **Extensibilidad:** Las funciones de extensión mantienen limpio el `DatabaseHelper`, separando la lógica de SQL de la lógica de negocio.
4.  **Limpieza:** El uso de `getColumnIndexOrThrow` dentro de las extensiones evita errores silenciosos si cambias el nombre de una columna.


