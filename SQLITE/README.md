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

## 🏁 Resumen
*   SQLite es **nativo** y **embebido** en Android.
*   Los datos son **privados** y se guardan en un solo archivo.
*   `SQLiteOpenHelper` es el corazón de la gestión de la BD.
*   Se usa SQL estándar para manipular la información.

**Próximo paso:** [Creación de la clase SQLiteOpenHelper ➡️](#)