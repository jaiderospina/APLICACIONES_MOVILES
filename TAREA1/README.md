## Actividad: "Arqueología de Código: Reconstruyendo el ADN de nuestra App"

### 🎯 Objetivo

Realizar un proceso de **ingeniería inversa** sobre una aplicación Android existente para documentar su ciclo de vida de desarrollo de software (SDLC) "ideal", aplicando buenas prácticas y centralizando todo en un repositorio de GitHub altamente profesional.

### 👥 Formato de Trabajo

* **Grupos:** 3 estudiantes.
* **Duración sugerida:** 1 a 2 sesiones de laboratorio.
* **Entregable:** Repositorio de GitHub con el código fuente y un `README.md` exhaustivo.

---

### 🛠️ La Misión

Tienen una aplicación funcional pero "huérfana" de documentación. Su tarea es analizar el APK o el código fuente actual para deducir y documentar cómo debió haber sido construida bajo estándares de calidad modernos.

#### Roles Sugeridos (Rotativos):

1. **Analista de Arquitectura:** Se enfoca en patrones de diseño (MVVM, Clean Architecture) y diagramación.
2. **Especialista en QA y Operaciones:** Define el ciclo de pruebas y despliegue (CI/CD).
3. **Líder de Producto/Documentación:** Coordina la coherencia del README y la experiencia de usuario (UX/UI).

---

### 📋 Requerimientos del README (Estructura Sugerida)

El `README.md` no debe ser solo texto; debe ser el centro de mando del proyecto. Deben incluir:

1. **Análisis de Ingeniería Inversa:**
* **Desglose de Componentes:** Identificación de Actividades, Fragments, Services y Broadcast Receivers.
* **Stack Tecnológico:** Versión de Kotlin/Java, SDK mínimo, librerías de terceros (Retrofit, Room, Jetpack Compose, etc.).


2. **Propuesta de Ciclo de Vida Óptimo (SDLC):**
* **Fase de Requerimientos:** Redacción de Historias de Usuario basadas en las funciones actuales.
* **Diagrama de Arquitectura:** Reconstrucción visual de cómo fluyen los datos.


3. **Buenas Prácticas de Ingeniería:**
* **Guía de Estilo:** Convenciones de nombres utilizadas.
* **Estrategia de Branching:** Explicar qué modelo de Git (GitFlow o Trunk Based) se adaptaría mejor a este proyecto.
* **Seguridad:** Análisis de permisos en el `AndroidManifest.xml` y posibles vulnerabilidades halladas.



---

### 🚀 Instrucciones para la Entrega en GitHub

Para que el repositorio sea considerado "enriquecido", deben cumplir con lo siguiente:

* **Uso de Issues:** Deben crear al menos 6 *Issues* que representen "hallazgos" o mejoras detectadas durante la ingeniería inversa.
* **Pull Requests (PRs):** La documentación del README debe hacerse mediante PRs cruzados. Un estudiante escribe una sección y otro la revisa y aprueba (uso de *Code Review*).
* **Recursos Visuales:** Uso de insignias (Badges), capturas de pantalla de la app y diagramas en formato Mermaid o imágenes.
* **Wiki o Proyectos:** (Opcional) Uso de *GitHub Projects* para organizar las tareas de reconstrucción.

---


