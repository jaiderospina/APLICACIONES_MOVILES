# 🐾 PetTrack – Requerimientos de Software
### Proyecto Práctico | Tecnología en Electrónica y Comunicaciones

---

## 1. Descripción General del Proyecto

**PetTrack** es una aplicación móvil Android orientada al monitoreo y seguridad de mascotas. El sistema combina tecnologías de posicionamiento, comunicación inalámbrica y almacenamiento de datos para ofrecer seguimiento en tiempo real, alertas de zona segura e historial de recorridos.

Este proyecto tiene un doble objetivo:
- **Objetivo de Ingeniería:** Implementar una solución funcional de tracking.
- **Objetivo Académico:** Investigar, comparar e implementar tecnologías de localización y comunicación (GPS, SMS, BLE, LoRa, etc.).

---

## 2. Stakeholders

| Rol | Descripción |
|---|---|
| Usuario final | Dueño de la mascota |
| Estudiante desarrollador | Diseñador, implementador y documentador |
| Docente evaluador | Revisa documentación, prototipo y pruebas |

---

## 3. Requerimientos Funcionales

### RF-01 – Registro de Mascotas
- El usuario puede registrar una o más mascotas con: nombre, especie, raza, foto, fecha de nacimiento y descripción.
- Cada mascota tiene un perfil individual con su historial asociado.

### RF-02 – Tracking en Tiempo Real
- La app muestra la ubicación actual de la mascota en un mapa interactivo (Google Maps SDK o OpenStreetMap).
- La ubicación se actualiza en intervalos configurables (ej. cada 5, 10 o 30 segundos).
- Se muestra la dirección aproximada (geocodificación inversa).

### RF-03 – Cercas de Seguridad (Geofencing)
- El usuario puede definir zonas seguras circulares o poligonales sobre el mapa.
- Se pueden crear múltiples cercas por mascota.
- Cuando la mascota sale o entra a una zona definida, se genera una **alerta**.
- Las alertas se notifican mediante:
  - Notificación push en la app.
  - **SMS** enviado al número registrado del dueño.

### RF-04 – Historial de Recorridos
- La app guarda cada recorrido de la mascota con: fecha, hora inicio, hora fin, distancia total y ruta trazada.
- El usuario puede reproducir visualmente cualquier recorrido anterior sobre el mapa.
- Los recorridos se almacenan localmente (SQLite/Room) y opcionalmente en la nube (Firebase Firestore).

### RF-05 – Alertas y Notificaciones
- Alerta por salida de geocerca.
- Alerta por batería baja del dispositivo GPS embebido (si aplica).
- Alerta por pérdida de señal del tracker.
- Todas las alertas quedan registradas en un log consultable.

### RF-06 – Gestión de Dispositivo de Rastreo
- La app se empareja con el hardware de tracking (módulo GPS + comunicación).
- Soporte para comunicación vía:
  - **Bluetooth BLE** (corto alcance, bajo consumo).
  - **SMS/GPRS** (largo alcance, mediante módulo SIM).
  - *(Opcional avanzado)* **LoRa** para zonas rurales sin cobertura celular.
- El estado de conexión del dispositivo es visible en la pantalla principal.

### RF-07 – Autenticación de Usuarios
- Registro e inicio de sesión mediante correo/contraseña.
- Opción de login con Google (Firebase Auth).
- Recuperación de contraseña por correo.

### RF-08 – Configuración
- Intervalo de actualización de ubicación.
- Activar/desactivar notificaciones SMS.
- Número de teléfono para alertas.
- Unidades de distancia (km/millas).

---

## 4. Requerimientos No Funcionales

| ID | Requerimiento |
|---|---|
| RNF-01 | La app debe funcionar en Android 8.0 (API 26) o superior. |
| RNF-02 | La interfaz debe ser intuitiva y accesible para usuarios no técnicos. |
| RNF-03 | El consumo de batería del dispositivo móvil debe optimizarse usando servicios en background eficientes (WorkManager / FusedLocationProvider). |
| RNF-04 | Los datos de ubicación deben transmitirse con cifrado TLS. |
| RNF-05 | El historial de recorridos debe soportar al menos 12 meses de datos por mascota. |
| RNF-06 | El tiempo de respuesta de la alerta de geocerca no debe superar los 30 segundos. |
| RNF-07 | La app debe funcionar en modo offline con sincronización posterior (para historial local). |

---

## 5. Investigación Tecnológica Requerida (Objetivo Académico)

Este apartado es **obligatorio** como parte del trabajo práctico. Los estudiantes deben investigar, comparar y documentar las siguientes tecnologías:

### 5.1 Tecnologías de Posicionamiento

| Tecnología | Precisión | Consumo | Cobertura | Costo |
|---|---|---|---|---|
| **GPS** (GNSS) | 3–10 m | Alto | Global | Bajo (módulo) |
| **A-GPS** | 3–5 m | Medio | Global + red | Bajo |
| **Wi-Fi Positioning** | 15–40 m | Bajo | Urbano | Ninguno |
| **BLE Beacons** | 1–5 m | Muy bajo | Interior | Medio |
| **LoRa + GPS** | 3–10 m | Muy bajo | Rural/amplio | Medio |

### 5.2 Tecnologías de Comunicación

| Tecnología | Alcance | Velocidad | Consumo | Uso en proyecto |
|---|---|---|---|---|
| **SMS** | Global (GSM) | Baja | Medio | Alertas de geocerca |
| **GPRS/4G** | Global | Alta | Alto | Streaming de ubicación |
| **Bluetooth BLE** | ~100 m | Media | Muy bajo | Conexión local al tracker |
| **LoRa / LoRaWAN** | 2–15 km | Muy baja | Muy bajo | Zonas sin cobertura |
| **Wi-Fi** | ~50 m | Alta | Medio | Sincronización en casa |

### 5.3 Hardware Sugerido para el Prototipo

**Opción A – Económica (SMS + GPS):**
- Módulo GPS: **NEO-6M** o **NEO-8M**
- Módulo GSM/SMS: **SIM800L** o **SIM900**
- Microcontrolador: **Arduino Nano** / **ESP32**
- Comunicación con app: vía SMS (AT Commands)

**Opción B – BLE (corto alcance):**
- **ESP32** con GPS integrado o módulo NEO-6M
- Comunicación BLE nativa del ESP32
- App Android se conecta por BLE directamente

**Opción C – LoRa (avanzada):**
- **ESP32 + LoRa SX1276**
- Gateway LoRaWAN (TTN – The Things Network)
- Backend MQTT → Firebase → App Android

> 📌 **Recomendación:** Implementar la Opción A o B como prototipo base, y documentar la Opción C como propuesta de escalabilidad.

---

## 6. Arquitectura del Sistema

```
[Collar/Dispositivo GPS]
        |
    (BLE / SMS / GPRS)
        |
[App Android – PetTrack]
        |
   [Firebase Backend]
   ├── Authentication
   ├── Firestore (historial, mascotas, geocercas)
   └── Cloud Messaging (notificaciones push)
        |
  [Servicio SMS] ← Twilio API o SIM800L directo
```

---

## 7. Módulos de la Aplicación

```
PetTrack Android App
├── auth/           → Login, registro, recuperación
├── pets/           → Perfil y gestión de mascotas
├── map/            → Mapa en tiempo real, geofencing
├── history/        → Historial de recorridos
├── alerts/         → Log de alertas y notificaciones
├── settings/       → Configuración de usuario y dispositivo
├── hardware/       → Comunicación BLE / SMS con tracker
└── data/           → Room DB, Firebase, repositorios
```

---

## 8. Modelo de Datos (Entidades Principales)

### Mascota
```
Pet { id, userId, name, species, breed, photo, birthDate, deviceId }
```

### Ubicación
```
Location { id, petId, latitude, longitude, timestamp, accuracy }
```

### Recorrido
```
Route { id, petId, startTime, endTime, distanceKm, points: List<Location> }
```

### Geocerca
```
Geofence { id, petId, name, centerLat, centerLng, radiusM, polygon[], active }
```

### Alerta
```
Alert { id, petId, geofenceId, type, message, timestamp, notifiedSMS }
```

---

## 9. Casos de Uso Principales

| ID | Caso de Uso | Actor |
|---|---|---|
| CU-01 | Registrar mascota | Usuario |
| CU-02 | Ver ubicación en tiempo real | Usuario |
| CU-03 | Crear / editar geocerca | Usuario |
| CU-04 | Recibir alerta por salida de zona | Sistema |
| CU-05 | Consultar historial de recorridos | Usuario |
| CU-06 | Reproducir recorrido en mapa | Usuario |
| CU-07 | Configurar alertas SMS | Usuario |
| CU-08 | Emparejar dispositivo GPS | Usuario |

---

## 10. Stack Tecnológico

| Capa | Tecnología |
|---|---|
| Lenguaje | Kotlin |
| UI | Jetpack Compose o XML + Material Design 3 |
| Mapas | Google Maps SDK for Android |
| Base de datos local | Room (SQLite) |
| Backend / Nube | Firebase (Auth, Firestore, FCM) |
| SMS | Twilio API (cloud) o SIM800L (hardware directo) |
| BLE | Android Bluetooth LE API |
| GPS Background | FusedLocationProviderClient + WorkManager |
| Control de versiones | Git + GitHub |
| Gestión de proyecto | GitHub Projects (Kanban) |

---

## 11. Plan de Trabajo y Entregables

| Fase | Actividades | Entregable |
|---|---|---|
| **1 – Investigación** | Comparar tecnologías GPS, BLE, SMS, LoRa | Informe técnico en `/docs/investigacion.md` |
| **2 – Diseño** | Arquitectura, modelo de datos, wireframes | Diagramas en `/docs/arquitectura/` |
| **3 – Prototipo Hardware** | Ensamblar y programar módulo GPS+comunicación | Esquemático + código en `/hardware/` |
| **4 – Desarrollo App** | Implementar módulos por sprints | Código fuente en `/app/` |
| **5 – Pruebas** | Pruebas funcionales, de campo y de estrés | Informe de pruebas en `/docs/pruebas.md` |
| **6 – Documentación** | README completo, Wiki, video demo | GitHub completo + video |

---

## 12. Estructura del Repositorio GitHub

```
📦 pettrack-android/
├── 📁 app/                  → Código fuente Android
├── 📁 hardware/             → Código Arduino/ESP32, esquemáticos
│   ├── firmware/
│   └── schematics/
├── 📁 docs/                 → Documentación del proyecto
│   ├── investigacion.md     → Comparativa de tecnologías
│   ├── arquitectura/        → Diagramas UML, arquitectura
│   ├── pruebas.md           → Casos y resultados de pruebas
│   └── manual_usuario.md    → Manual de uso
├── 📁 assets/               → Imágenes, videos, presentaciones
├── README.md                → Descripción general del proyecto
└── .github/
    └── workflows/           → CI/CD (opcional)
```

---

## 13. Criterios de Evaluación Sugeridos

| Criterio | Peso |
|---|---|
| Investigación tecnológica documentada | 20% |
| Funcionamiento del prototipo hardware | 20% |
| Funcionalidades de la app (tracking, geocerca, historial) | 30% |
| Calidad de la documentación en GitHub | 15% |
| Pruebas realizadas y documentadas | 15% |

---

## 14. Referencias para Iniciar

- [Android Developers – Geofencing API](https://developer.android.com/training/location/geofencing)
- [Google Maps SDK for Android](https://developers.google.com/maps/documentation/android-sdk)
- [Firebase for Android](https://firebase.google.com/docs/android/setup)
- [Twilio SMS API](https://www.twilio.com/docs/sms)
- [NEO-6M GPS Module Datasheet](https://www.u-blox.com/en/product/neo-6-series)
- [SIM800L AT Commands Reference](https://simcom.ee/documents/SIM800/SIM800_AT%20Command%20Manual_V1.09.pdf)
- [ESP32 BLE Arduino Library](https://github.com/espressif/arduino-esp32/tree/master/libraries/BLE)
- [The Things Network – LoRaWAN](https://www.thethingsnetwork.org/)
