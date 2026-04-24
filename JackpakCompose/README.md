### Jetpack Compose
---
### 1. Fundamentos de Jetpack Compose
Jetpack Compose es un kit de herramientas moderno de Google para crear interfaces de usuario nativas en Android mediante un enfoque **declarativo**. A diferencia del sistema tradicional de XML, en Compose tú describes cómo debe verse la interfaz y el sistema se encarga de dibujarla automáticamente.

*   **Composables:** Son funciones anotadas con `@Composable` que definen un componente de la interfaz.
*   **Estado:** La interfaz se "recompone" automáticamente cuando los datos que la impulsan cambian.
*   **Reutilización:** Se deben construir piezas pequeñas que puedan combinarse en estructuras más complejas.



Parece que la pregunta quedó incompleta, pero por el contexto de Jetpack Compose es razonable abordar la diferencia entre **enfoque declarativo** e **imperativo**, que es el contraste central en el desarrollo de interfaces.

---

## Enfoque imperativo

En el modelo imperativo se describe **paso a paso cómo debe cambiar la interfaz**.

Se trabaja manipulando directamente los componentes.

### Ejemplo (Android tradicional)

```kotlin
val textView = findViewById<TextView>(R.id.text)
textView.text = "Hola"
textView.setTextColor(Color.RED)
```

### Características

* Control explícito de cada cambio
* Se gestionan eventos y actualizaciones manualmente
* Mayor acoplamiento entre lógica y UI
* Propenso a errores cuando el estado crece

### Forma de pensar

> “Haz esto, luego esto, después cambia aquello”

---

## Enfoque declarativo

En el modelo declarativo se describe **qué se quiere mostrar**, no cómo hacerlo.

La UI se construye como una función del estado.

### Ejemplo (Jetpack Compose)

```kotlin
@Composable
fun Saludo(nombre: String) {
    Text(text = "Hola, $nombre")
}
```

Si el valor de `nombre` cambia, la UI se actualiza automáticamente.

### Características

* La UI depende del estado
* No se manipulan vistas directamente
* El sistema gestiona las actualizaciones
* Código más limpio y predecible

### Forma de pensar

> “Dado este estado, la UI debe verse así”

---

## Diferencia conceptual clave

| Aspecto     | Imperativo   | Declarativo    |
| ----------- | ------------ | -------------- |
| Enfoque     | Cómo cambiar | Qué mostrar    |
| Control     | Manual       | Automático     |
| Estado      | Disperso     | Central        |
| Complejidad | Crece rápido | Más controlada |

---

## Ejemplo comparativo claro

### Imperativo

```kotlin
if (isLoggedIn) {
    showHome()
} else {
    showLogin()
}
```

Aquí tú decides cuándo cambiar la UI.

### Declarativo

```kotlin
@Composable
fun Pantalla(isLoggedIn: Boolean) {
    if (isLoggedIn) {
        Home()
    } else {
        Login()
    }
}
```

Aquí la UI es una **representación directa del estado**.

---

## Interpretación técnica

El enfoque declarativo introduce una idea fundamental:

> La interfaz no se “modifica”, se “reconstruye” en función del estado.

Esto permite:

* Reducir inconsistencias
* Evitar estados intermedios incorrectos
* Simplificar el flujo mental del desarrollador

---

## Reflexión final

El cambio no es solo sintáctico, es cognitivo.
Pasar a declarativo implica dejar de pensar en términos de “eventos que modifican vistas” y comenzar a pensar en “estados que describen interfaces”.


---

### 2. Estrategia de Diseño: Descomposición

Antes de programar, es vital **analizar el diseño** para dividirlo en partes reutilizables. 

Para la aplicación MySoothe (tarea 2), el desglose recomendado es:

1.  **Nivel de pantalla:** Contenido principal y navegación inferior.
2.  **Subpartes:** Barra de búsqueda, secciones de contenido y cuadrículas.
3.  **Componentes de bajo nivel:** Elementos individuales de listas y tarjetas.

Se recomienda una implementación **Bottom-Up** (de abajo hacia arriba): crear primero los componentes más pequeños y luego integrarlos.

---

### 3. Modificadores: El motor del diseño

Los **modificadores** permiten adaptar la apariencia, el tamaño y el comportamiento de cualquier componible.

*   **Flexibilidad:** Todo componente debe aceptar un parámetro `modifier` y pasarlo a su elemento raíz.
*   **Encadenamiento:** Puedes combinar múltiples funciones, como `.fillMaxWidth()` para ocupar todo el ancho o `.heightIn(min = 56.dp)` para asegurar una altura mínima adaptable.

**Ejemplo de Barra de Búsqueda:**
```kotlin
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        placeholder = { Text(stringResource(R.string.placeholder_search)) },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp) // Permite crecer con fuentes grandes
    )
}
```


---

### 4. Organización del Diseño (Layouts)

Compose ofrece contenedores estándar para posicionar elementos:

*   **Column:** Organiza elementos verticalmente.
*   **Row:** Organiza elementos horizontalmente.
*   **Box:** Superpone elementos uno sobre otro.

#### Alineación vs. Arreglo

*   **Alineación (Alignment):** Controla el posicionamiento en el **eje cruzado** (ej. centrar horizontalmente dentro de una `Column`).
*   **Arreglo (Arrangement):** Controla la distribución en el **eje principal** (ej. el espacio entre elementos en una `Row`).
<img width="3570" height="4770" alt="image" src="https://github.com/user-attachments/assets/35e9500c-2c98-4a40-8377-a322bedc2980" />

---

### 5. Listas Eficientes (Lazy Layouts)

Para manejar grandes conjuntos de datos, se utilizan componentes **Lazy**, que solo renderizan los elementos visibles en pantalla.

*   **LazyRow:** Fila de desplazamiento horizontal.
*   **LazyHorizontalGrid:** Cuadrícula de desplazamiento horizontal.
*   **Content Padding:** Para evitar que el contenido se corte al desplazarse, usa `contentPadding` en lugar de modificadores de padding estándar en el contenedor.

---

### 6. APIs de Ranuras (Slot APIs)

Son patrones de diseño que dejan "espacios vacíos" en un componible para que el desarrollador los llene con cualquier otro contenido. Esto permite crear contenedores genéricos y altamente flexibles.

**Ejemplo de Sección Reutilizable:**

```kotlin
@Composable
fun HomeSection(
    @StringRes title: Int,
    content: @Composable () -> Unit // El "slot" o ranura
) {
    Column {
        Text(stringResource(title), style = MaterialTheme.typography.titleMedium)
        content() // Se inserta el contenido dinámico aquí
    }
}
```


---

### 7. Estructura de Pantalla Completa: Scaffold

El componible **Scaffold** de Material Design facilita la creación de estructuras estándar. Proporciona ranuras específicas para componentes comunes como la barra de navegación inferior (`bottomBar`).

```kotlin
@Composable
fun MySootheApp() {
    Scaffold(
        bottomBar = { SootheBottomNavigation() }
    ) { padding ->
        HomeScreen(Modifier.padding(padding))
    }
}
```


---

### 8. Adaptabilidad y Pantallas Grandes

Para que la aplicación funcione en modo paisaje o tablets, se debe detectar el tamaño de la ventana mediante **WindowSizeClass**.
*   **Modo Retrato:** Se suele usar una `NavigationBar` (barra inferior).
*   **Modo Paisaje:** Se recomienda un `NavigationRail` (riel lateral) para aprovechar mejor el ancho de la pantalla.

---

* **Tarea 1**

1. Explicar los conceptos Alineación vs. Arreglo
2. Crear imágenes con apoyo de IA para ampliar las ideas anteriormente explicadas.
* **Respuesta**
  el ejemplo que se uso para generar la imagen fue el siguiente:
   Ejemplo Práctico por Tipo de Aplicación
    Aplicación de Red Social (Estilo Instagram)
    Arreglo general:

    Header superior fijo con logo y notificaciones
    Feed central con scroll infinito vertical
    Navegación inferior fija con iconos principales
    Alineación específica:

    Fotos centradas horizontalmente con márgenes uniformes
    Texto de publicaciones alineado a la izquierda
    Botones de interacción (like, comment) alineados a la izquierda
    Perfil de usuario circular perfectamente centrado en la parte superior
    Ventajas de este enfoque:
    
    La navegación inferior siempre accesible facilita el cambio entre secciones
    El scroll vertical es natural para consumir contenido
    La alineación consistente crea una experiencia predecible
    Aplicación de E-commerce (Estilo Amazon)
    Arreglo general:
    
    Búsqueda superior con barra destacada
    Carrusel horizontal de ofertas principales
    Categorías en cuadrícula 2x3
    Lista vertical de productos recomendados
    Alineación específica:
    
    Precientes alineados a la derecha para fácil comparación
    Imágenes de productos centradas con fondo uniforme
    Botones "Comprar ahora" alineados consistentemente en la parte inferior
    Estrellas de valoración siempre alineadas a la izquierda
    Ventajas de este enfoque:
    
    El carrusel horizontal maximiza el espacio para ofertas importantes
    La cuadrícula de categorías permite acceso rápido a diferentes secciones
    La alineación consistente de precios facilita la comparación
    Aplicación de Productividad (Estilo Notion)
    Arreglo general:
    
    Sidebar lateral fijo con navegación principal
    Contenido central con bloques apilables
    Barra de herramientas flotante contextual
    Alineación específica:
    
    Títulos alineados a la izquierda con sangría jerárquica
    Elementos de lista con alineación de viñetas consistente
    Botones de acción alineados a la derecha
    Contenido multimedia centrado con márgenes simétricos
    Ventajas de este enfoque:
    
    El sidebar lateral permite navegación rápida sin salir del contenido
    Los bloques apilables facilitan la organización flexible
    La alineación jerárquica mejora la legibilidad
    Aplicación de Salud y Fitness (Estilo Strava)
    Arreglo general:
    
    Resumen superior con métricas principales destacadas
    Gráficos circulares para progreso
    Historial cronológico vertical
    Botones de acción inferiores fijos
    Alineación específica:
    
    Métricas numéricas centradas con jerarquía tipográfica clara
    Etiquetas de gráficos alineadas radialmente
    Fechas del historial alineadas a la izquierda
    Botones de inicio/parada centrados para fácil acceso
    Ventajas de este enfoque:
    
    Las métricas destacadas permiten rápida evaluación del progreso
    Los gráficos circulares facilitan la comprensión visual
    El historial cronológico sigue el flujo natural del tiempo
  <img width="1536" height="864" alt="image" src="https://github.com/user-attachments/assets/002188b1-07b6-41b5-a339-e77db16ba68d" />


---

* **Tarea 2**

Desarrollar individualmente el laboratorio anexo "Diseños básicos en Compose" y que se presenta en: 

- https://developer.android.com/codelabs/jetpack-compose-layouts?hl=es_419#14


---

# Referencias: 



- https://developer.android.com/get-started/codelabs?hl=es-419
- https://prezi.com/view/TnRKpdzdhwY5e7ZzpQAN/?referral_token=OHR-PUlnB3FN
- https://developer.android.com/courses/pathways/compose?hl=es-419
