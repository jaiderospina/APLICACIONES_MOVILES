# 🐾 Pet Tracker
### Localización y Monitoreo de Mascotas

<div align="center">
  
  ![Android](https://img.shields.io/badge/Android-7.0%2B-green?style=for-the-badge&logo=android)
  ![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-7F52FF?style=for-the-badge&logo=kotlin)
  ![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Latest-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
  ![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)
  
  <br><br>
  
  **🎯 Rastreo y monitoreo en tiempo real para la seguridad de tus mascotas**
  
  <p>Pet Tracker es una aplicación Android moderna y fluida diseñada para gestionar dispositivos GPS de forma intuitiva y eficiente.</p>
  
</div>

---

## 📑 Contenido

- [✨ Características](#-características-principales)
- [🖼️ Interfaz](#-interfaz)
- [🏗️ Arquitectura](#️-detalles-técnicos)
- [📋 Requisitos](#-requisitos)
- [🚀 Inicio Rápido](#-configuración-del-desarrollador)
- [🤝 Contribuir](#-contribuciones)

---

## ✨ Características Principales

<table>
  <tr>
    <td width="50%">
      <h4>📍 Rastreo en Tiempo Real</h4>
      <p>Visualización en vivo de la ubicación de tu mascota sobre un mapa interactivo con actualizaciones constantes.</p>
    </td>
    <td width="50%">
      <h4>🐶 Perfiles de Mascotas</h4>
      <p>Gestiona múltiples mascotas con información detallada: nombre, raza, fotos y notas personalizadas.</p>
    </td>
  </tr>
  <tr>
    <td width="50%">
      <h4>💬 Control por SMS</h4>
      <p>Envía comandos directos al rastreador: ubicación, estado de batería, reinicio y más.</p>
    </td>
    <td width="50%">
      <h4>🔴 Geocercas Inteligentes</h4>
      <p>Define perímetros de seguridad con alertas automáticas si tu mascota sale de la zona.</p>
    </td>
  </tr>
  <tr>
    <td width="50%">
      <h4>📊 Historial Detallado</h4>
      <p>Consulta el registro completo de movimientos, rutas y paradas para análisis histórico.</p>
    </td>
    <td width="50%">
      <h4>🔋 Monitor de Dispositivo</h4>
      <p>Supervisa el nivel de batería y estado de conexión del hardware GPS en tiempo real.</p>
    </td>
  </tr>
</table>

---

## 🖼️ Interfaz

> **🎨 Interfaz Moderna con Jetpack Compose**
>
> Desarrollada con las últimas tecnologías de Android para ofrecerte una experiencia fluida, responsiva y hermosa.

<div align="center">
  <i>🚀 Próximamente: Capturas de pantalla en acción</i>
</div>

---

## 🏗️ Detalles Técnicos

La aplicación sigue los **estándares de Google** y principios de **arquitectura limpia**:

```
┌─────────────────────────────────────┐
│       PRESENTACIÓN (UI)             │
│  • Jetpack Compose                  │
│  • Material Design 3                │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│      LÓGICA (ViewModel)             │
│  • MVVM Architecture                │
│  • Coroutines & Flow                │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│      DATOS & SERVICIOS              │
│  • Room Database                    │
│  • Ktor (Networking)                │
│  • SMS (Control GPS)                │
│  • Google Maps API                  │
└─────────────────────────────────────┘
```

### Stack Tecnológico

| Componente | Tecnología |
|:----|:----|
| **Lenguaje** | Kotlin 1.9+ |
| **UI Framework** | Jetpack Compose (declarativo) |
| **Arquitectura** | MVVM |
| **Inyección de Dependencias** | Koin |
| **Networking** | Ktor Client |
| **Base de Datos** | Room Database |
| **Maps** | Google Maps SDK |
| **Comunicación** | SMS nativo |

---

## 📱 Requisitos

### Sistema
- **Android:** 7.0 (API 24) o superior
- **RAM mínima:** 2GB
- **Almacenamiento:** 50MB disponibles

### Hardware
- Rastreador GPS compatible con SMS
  - ✅ Recomendado: Serie GF-07
  - Cualquier dispositivo que soporte comandos AT

### Permisos de Aplicación
```xml
<!-- Ubicación -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

<!-- SMS -->
<uses-permission android:name="android.permission.SEND_SMS" />
<uses-permission android:name="android.permission.RECEIVE_SMS" />

<!-- Red -->
<uses-permission android:name="android.permission.INTERNET" />

<!-- Almacenamiento -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

---

## 🚀 Configuración del Desarrollador

### 1️⃣ Requisitos Previos
- **Android Studio** Jellyfish 2023.3.1 o superior
- **JDK** 17 o superior
- **Git** configurado

### 2️⃣ Clonar el Proyecto
```bash
git clone https://github.com/jaiderospina/APLICACIONES_MOVILES.git
cd APLICACIONES_MOVILES
git checkout estable
```

### 3️⃣ Configurar API Keys

**Google Maps API:**
1. Ir a [Google Cloud Console](https://console.cloud.google.com)
2. Crear proyecto y habilitar "Maps SDK for Android"
3. Crear clave de API

Crear `local.properties`:
```properties
# Google Maps
MAPS_API_KEY=tu_clave_aqui
```

O editar `Config.kt`:
```kotlin
object Config {
    const val MAPS_API_KEY = "TU_API_KEY_AQUÍ"
    const val SMS_TIMEOUT = 30000L
    const val MAP_ZOOM_LEVEL = 15f
}
```

### 4️⃣ Compilar y Ejecutar
```bash
# Sincronizar dependencias
./gradlew sync

# Construir APK
./gradlew assembleDebug

# Ejecutar en dispositivo (recomendado para SMS)
./gradlew installDebug

# O desde Android Studio: Run > Run 'app'
```

⚠️ **Nota:** Las funciones de SMS requieren un dispositivo físico o emulador configurado correctamente.

---

## 🤝 Contribuciones

¡Nos encantaría tu ayuda para mejorar Pet Tracker! 

### Cómo Contribuir

1. **Fork** el repositorio
2. **Crea una rama** para tu feature
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```
3. **Commit** tus cambios
   ```bash
   git commit -m "✨ feat: Descripción clara de cambios"
   ```
4. **Push** a tu rama
   ```bash
   git push origin feature/nueva-funcionalidad
   ```
5. **Abre un Pull Request** con descripción detallada

### Estándares de Contribución
- ✅ Código limpio y bien documentado
- ✅ Tests unitarios para nuevas funcionalidades
- ✅ Mensajes de commit descriptivos
- ✅ Respetar la arquitectura MVVM

---

## 📄 Licencia

Este proyecto está bajo licencia **MIT**. Ver `LICENSE` para más detalles.

---

<div align="center">
  
  ### 💚 Desarrollado con amor para la seguridad de tus mascotas
  
  [![Made with Kotlin](https://img.shields.io/badge/Made%20with-Kotlin-7F52FF?style=flat&logo=kotlin)](https://kotlinlang.org)
  [![Android](https://img.shields.io/badge/Android-Platform-3DDC84?style=flat&logo=android)](https://www.android.com)
  
  **¿Preguntas?** Abre un [issue](https://github.com/jaiderospina/APLICACIONES_MOVILES/issues) 💬
  
</div>
