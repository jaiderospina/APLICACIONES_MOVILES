# 🐾 PetTrack — Solución Integral de Rastreo de Mascotas

Este repositorio contiene el **Análisis Técnico de Hardware** y la **Guía de Optimización** para el desarrollo de un sistema de rastreo de mascotas orientado a la región andina. Tras evaluar tres tecnologías de rastreo —GSM, BLE Mesh y BLE Directo—, el proyecto adopta el **GF-07** como dispositivo definitivo por su cobertura nacional, independencia de infraestructura externa y relación costo-beneficio.

---

## Introducción

En el panorama tecnológico actual, los dispositivos de rastreo personal y de activos han evolucionado aceleradamente, impulsados por la miniaturización de componentes, la expansión de redes celulares de baja latencia y la consolidación de ecosistemas de conectividad como Bluetooth Low Energy (BLE). Este proyecto nació de la necesidad de evaluar, comparar y optimizar tres soluciones de rastreo disponibles en el mercado latinoamericano, con el objetivo de ofrecer una guía técnica rigurosa para usuarios finales, técnicos y administradores de sistemas de seguridad.

Los dispositivos evaluados responden a filosofías tecnológicas distintas: el **GF-07** opera sobre infraestructura celular 2G mediante triangulación de torres (LBS); el **Finein Tag** aprovecha la red *Encontrar* de Apple a través de Bluetooth Low Energy cifrado; y los **iTags genéricos** funcionan como alarmas de proximidad punto a punto. Cada uno ocupa un nicho específico dentro del espectro de soluciones de rastreo, con ventajas y limitaciones propias que este informe explora en profundidad.

Esta edición incluye además un análisis comparativo frente a los dispositivos líderes del mercado global —Apple AirTag, Samsung Galaxy SmartTag2 y Tile Pro— para contextualizar las capacidades de los dispositivos seleccionados y fundamentar las razones técnicas y económicas de su elección.

---

## 🧩 Arquitectura del Sistema

### Componentes Principales

**1. Dispositivo Collar (Hardware)**

- Módulo GPS: GF-07
- Módulo GSM: SIM800L para comunicación celular
- Batería: LiPo 3.7V 1000 mAh recargable
- Cargador: TP4056 con protección integrada

**2. Frontend (Aplicación Web)**

- Framework: React 19
- Estilos: Tailwind CSS 4
- Mapas: Google Maps JavaScript API
- Componentes: shadcn/ui
- Gestión de estado: React Query + tRPC

**3. Integraciones Externas**

- SMS y llamadas: Twilio API
- Mapas: Google Maps API
- Almacenamiento de imágenes: AWS S3

---

## ⚙️ Tecnologías

| Capa | Tecnologías |
|---|---|
| 🔌 Hardware | GPS GF-07, SIM800L (GSM/GPRS), Batería LiPo 3.7V, TP4056, LM2596 |
| 💻 Software | React + Tailwind CSS, Node.js, Google Maps API, Twilio API, AWS S3 |

---

## 📋 Requisitos Funcionales

Los requisitos funcionales definen las capacidades específicas que el sistema PetTrack debe proporcionar.

**RF1 — Gestión de Mascotas**

- El sistema debe permitir registrar nuevas mascotas con los siguientes datos: nombre, foto, número SIM del collar y número de teléfono para alertas.
- El usuario debe poder visualizar un listado de todas sus mascotas registradas en el dashboard principal.
- Cada mascota debe mostrar su última ubicación conocida, nivel de batería actual y estado de conexión del dispositivo.

**RF2 — Rastreo en Tiempo Real**

- La aplicación debe mostrar la ubicación actual de cada mascota en un mapa interactivo (Google Maps).
- El usuario debe poder solicitar una actualización de ubicación mediante un botón *Solicitar Ubicación*, que envía un comando al dispositivo.
- La ubicación debe actualizarse en tiempo real cuando el dispositivo reporta nuevas coordenadas GPS.

**RF3 — Consulta de Estado**

- El usuario debe poder consultar el estado actual del dispositivo (nivel de batería, señal de red, estado de conexión) mediante un botón *Consultar Estado*.
- El sistema debe mostrar el historial de ubicaciones de cada mascota con marcas de tiempo.

**RF4 — Sistema de Geocerca**

- El usuario debe poder definir una zona segura (geocerca) con radio configurable desde un punto de origen (hogar).
- El sistema debe calcular continuamente la distancia entre la ubicación actual de la mascota y el centro de la geocerca.
- Cuando la mascota supera el radio configurado, el sistema debe activar alertas automáticamente.

**RF5 — Alertas Automáticas**

- Al activarse una alerta de geocerca, el sistema debe enviar automáticamente un SMS al número registrado.
- El SMS debe incluir un enlace directo a Google Maps con las coordenadas exactas de la mascota.
- El sistema debe realizar una llamada de voz automática al número registrado para garantizar la atención inmediata.
- El usuario debe poder visualizar el historial completo de alertas generadas.

**RF6 — Historial y Reportes**

- El sistema debe mantener un registro completo de todas las ubicaciones registradas por mascota.
- El sistema debe almacenar un historial de alertas con tipo, fecha, hora y estado de envío (SMS y llamada).
- El usuario debe poder descargar un informe técnico en PDF con los requisitos del proyecto.

---

## 🔒 Requisitos No Funcionales

Los requisitos no funcionales establecen los criterios de calidad y las restricciones técnicas del sistema.

**RNF1 — Rendimiento**

- La aplicación web debe cargar completamente en menos de 3 segundos en conexiones 4G.
- Las consultas a la base de datos no deben superar los 500 ms de latencia.
- El mapa interactivo debe renderizar sin lag con hasta 50 marcadores simultáneos.

**RNF2 — Disponibilidad**

- El sistema debe mantener una disponibilidad del 99,5 % durante el horario de operación.
- El dispositivo debe reconectarse automáticamente a la red celular tras pérdidas temporales de señal.
- Las alertas deben procesarse y enviarse en menos de 30 segundos desde su activación.

**RNF3 — Seguridad**

- Todas las comunicaciones entre la aplicación web y el servidor deben estar cifradas con HTTPS/TLS.
- Los números de teléfono y datos personales deben almacenarse de forma segura en la base de datos.
- La autenticación debe implementarse mediante OAuth 2.0.
- El acceso a los datos de cada mascota debe estar restringido al propietario autenticado.

**RNF4 — Usabilidad**

- La interfaz debe ser responsiva y funcionar correctamente en dispositivos móviles (iOS, Android) y de escritorio.
- El diseño debe cumplir con los principios de accesibilidad WCAG 2.1 AA.
- La navegación debe ser intuitiva, con un máximo de 3 clics para acceder a cualquier funcionalidad.

**RNF5 — Confiabilidad**

- El sistema debe contar con mecanismos de reintento automático para el envío de SMS y llamadas.
- Los datos de ubicación deben persistirse en la base de datos aunque la conexión se interrumpa momentáneamente.
- El dispositivo debe incluir validación de datos GPS para descartar lecturas erróneas.

**RNF6 — Escalabilidad**

- La arquitectura debe soportar el monitoreo simultáneo de hasta 100 mascotas.
- La base de datos debe estar optimizada con índices para consultas de alta frecuencia.
- El sistema debe permitir la incorporación de nuevos dispositivos sin reconfiguración del servidor.

**RNF7 — Mantenibilidad**

- El código debe seguir estándares de escritura consistentes y estar correctamente documentado.
- El sistema debe generar logs detallados de todas las operaciones críticas.
- Debe existir un proceso de actualización de firmware para los dispositivos que no interrumpa el servicio.

---

## 💰 Materiales

| Componente | Precio (COP) |
|---|---|
| GPS GF-07 | $29.600 |
| SIM800L | $30.000 |
| Batería LiPo | $18.000 |
| TP4056 | $4.500 |
| LM2596 | $6.000 |
| SIM Card | $5.000 |
| Carcasa | $8.000 |
| Cables | $7.000 |
| **TOTAL** | **$108.100** |

## 💳 Costos Operativos Mensuales

| Concepto | Costo mensual (COP) |
|---|---|
| Datos SIM | $15.000 |
| Servidor | $50.000 |
| SMS y llamadas | $20.000 |
| Mantenimiento | $10.000 |
| **TOTAL** | **$95.000** |

---

## 🛰️ Evaluación de Dispositivos

### 1. Rastreador GF-07 (GSM/GPRS)

El GF-07 es un dispositivo que, aunque se comercializa bajo la etiqueta de "rastreador GPS", opera exclusivamente mediante triangulación de torres celulares (LBS — *Location Based Services*) a través de redes 2G. Esta distinción es fundamental para comprender tanto sus capacidades reales como sus limitaciones inherentes. La ausencia de un módulo GNSS (GPS/GLONASS) le otorga ventajas en costo y tamaño, pero restringe su precisión a un rango típico de 100 a 500 metros en áreas urbanas y de 2 a 3 kilómetros en zonas rurales con baja densidad de infraestructura celular.

Desde el punto de vista del hardware, el GF-07 integra un módulo SIM800L o equivalente, compatible con bandas GSM 850/900/1800/1900 MHz. La comunicación con el usuario se realiza exclusivamente vía SMS bidireccional y llamadas de voz, sin requerir acceso a internet por parte del usuario. Esta característica lo hace especialmente resiliente en contextos con conectividad limitada o donde se prefieren canales de comunicación simples y auditables.

#### 1.1. Comandos SMS

Para operar el GF-07 es necesario enviar comandos SMS al número de la tarjeta SIM insertada en el dispositivo. A continuación se detalla la lista completa de comandos y su función:

| Comando | Función |
|---|---|
| `000****` | **Vincular número maestro** — Paso inicial obligatorio. Vincula el número del remitente como "maestro"; solo este número podrá recibir alertas y controlar el dispositivo. |
| `777****` | **Solicitar ubicación** — El dispositivo responde con un enlace a Google Maps que indica su posición aproximada mediante LBS. |
| `888****` | **Consultar estado** — Devuelve un informe con nivel de batería, intensidad de señal GSM, capacidad de la tarjeta MicroSD y estado de las funciones activas. |
| `666****` | **Alarma por sonido** — Activa el monitoreo acústico; si el micrófono detecta más de 40 dB, el dispositivo llama automáticamente al número maestro. |
| `555****` | **Iniciar grabación de audio** — El dispositivo comienza a grabar audio ambiental y lo almacena en la tarjeta MicroSD. |
| `445****` | **Formatear tarjeta MicroSD** — Borra todo el contenido almacenado en la tarjeta. |
| `999****` | **Reinicio remoto** — Fuerza un reinicio del dispositivo; útil si deja de responder a comandos. |
| `444****` | **Cancelar tareas** — Detiene cualquier tarea en curso, como grabación de audio o alarma sonora. |
| `FACTORY#****` | **Restablecimiento de fábrica** — Restaura la configuración original y elimina el número maestro vinculado. |

#### 1.2. Estrategias de Optimización

**Configuración de red y APN**
El problema más frecuente es la falta de respuesta al comando `777****`, generalmente causada por una configuración incorrecta del APN. Se recomienda configurarlo manualmente con el formato `APN,nombre_del_apn#`. Para operadoras colombianas:
- Claro: `internet.claro.com.co`
- Movistar: `internet.movistar.com.co`
- Tigo: `web.colombiamovil.com.co`

La SIM debe ser compatible con 2G y no debe tener PIN activado.

**Gestión de energía**
La batería interna ofrece entre 2 y 3 días en modo espera. Se recomienda desactivar la alarma de sonido (`666****`) cuando no sea necesaria, usando el comando `444****`. En modo de solo ubicación, la autonomía puede extenderse hasta 5 días reduciendo la frecuencia de consultas.

**Mejora de precisión LBS**
La precisión mejora en zonas urbanas de alta densidad celular. El dispositivo debe colocarse en áreas con buena cobertura de señal y evitar blindajes metálicos cerrados.

---

### 2. Finein Tag (Red Apple Find My)

El Finein Tag es una solución técnicamente sofisticada que aprovecha la infraestructura de la red *Encontrar* de Apple, compuesta por cientos de millones de dispositivos activos en todo el mundo. A diferencia del GF-07, no requiere tarjeta SIM ni plan de datos propio: su conectividad se sustenta en el protocolo Bluetooth Low Energy (BLE 5.0) y en el ecosistema descentralizado de Apple.

Su funcionamiento se basa en la emisión continua de señales BLE cifradas con rotación de claves periódica, lo que garantiza la privacidad del usuario mientras permite la localización indirecta. Cualquier dispositivo Apple con la red *Encontrar* activada que entre en rango del Finein Tag captura la señal, cifra la ubicación con la clave pública del propietario y la envía a los servidores de iCloud. Solo el propietario puede descifrar esta información con su clave privada, asegurando que ni Apple ni terceros tengan acceso a los datos de ubicación.

#### 2.1. Estrategias de Optimización

**Maximizar la cobertura de red**
La eficacia del Finein Tag es directamente proporcional a la densidad de usuarios Apple en la zona. En Bogotá, la penetración de iPhone supera el 35% del mercado smartphone, lo que proporciona una red de detección muy densa en entornos urbanos.

**Gestión de la batería CR2032**
La pila estándar tiene una duración de entre 8 y 12 meses. Evitar activar el sonido innecesariamente prolonga su vida útil. La desconexión frecuente es el primer síntoma de batería baja.

**Modo Perdido**
Debe activarse de inmediato en caso de extravío. Genera notificaciones en tiempo real y bloquea el emparejamiento del dispositivo con otras cuentas Apple.

**Colocación estratégica**
Para uso antirrobo en vehículos, se recomienda ocultar el dispositivo en zonas de difícil acceso. Las alertas anti-acoso de Apple se activan tras 8 a 24 horas de movimiento con un tag ajeno.

---

### 3. iTag / Smart Tag Genérico (Bluetooth)

Los iTags genéricos son la solución de menor costo y complejidad técnica dentro de la comparativa. Diseñados como alarmas de proximidad bidireccionales, su principal valor radica en la inmediatez de la alerta: la notificación se produce en el instante en que la conexión Bluetooth se interrumpe, sin latencia de red ni dependencia de servidores externos.

El protocolo de comunicación es Bluetooth 4.0 (BLE) en la banda de 2,4 GHz, con un rango efectivo de entre 10 y 25 metros según las condiciones ambientales. La aplicación companion —típicamente *iSearching* para Android o *Find My* para iOS— gestiona la lógica de alertas, el registro de la última ubicación conocida al momento de la desconexión y la función de búsqueda por sonido bidireccional. La ausencia de conectividad propia es su principal limitación frente a los otros dispositivos evaluados, pero también su mayor fortaleza en términos de simplicidad operativa y privacidad.

#### 3.1. Estrategias de Optimización

**Permisos del sistema operativo**
Otorgar permisos de *Ubicación: Permitir siempre* y configurar la batería de la aplicación como *Sin restricciones* en Android para evitar que el SO cierre el proceso en segundo plano.

**Alerta bidireccional**
Configurar la aplicación para que tanto el teléfono como el iTag emitan alerta al perder la conexión. El umbral óptimo se sitúa entre 10 y 15 metros para evitar falsas alarmas.

**Gestión de interferencias**
Limitar el número de dispositivos Bluetooth activos simultáneamente (auriculares, relojes, etc.) para reducir interferencias en la banda de 2,4 GHz.

---

## 📊 Análisis Comparativo con Estándares Globales

> Este análisis fue el fundamento técnico para la selección del dispositivo final del proyecto. Se evaluaron cinco soluciones de rastreo considerando tecnología, precisión, cobertura geográfica y costo. El resultado: el **GF-07** es el único que cumple con todos los criterios críticos para el contexto colombiano.

| Dispositivo | Tecnología | Precisión | Cobertura | Precio | Seleccionado |
|---|---|---|---|---|:---:|
| **GF-07** | GSM / LBS | Media | Nacional (>95%) | Bajo | ✅ |
| Finein Tag | BLE + red Find My | Media | Global (dep. iOS) | Medio | — |
| iTag genérico | BLE | Baja | Local (~15 m) | Bajo | — |
| Apple AirTag | BLE + UWB | Alta | Global | Alto | — |
| Samsung SmartTag2 | BLE | Media | Global | Medio | — |

### ¿Por qué el GF-07?

A diferencia de las soluciones BLE, el GF-07 **no depende de la proximidad del usuario ni de la densidad de otros dispositivos** para reportar ubicación. Opera de forma autónoma sobre la red celular 2G, con cobertura en más del 95% del territorio colombiano, y puede consultarse desde cualquier teléfono mediante SMS sin necesidad de internet ni aplicaciones. Para un collar de mascota que debe funcionar en entornos urbanos y rurales por igual, esta independencia es determinante.

| Criterio | BLE (Finein / iTag / AirTag) | **GF-07** ✅ |
|---|---|---|
| Funciona sin otros dispositivos cerca | ❌ | ✅ |
| Cobertura rural Colombia | ❌ | ✅ |
| Opera sin app ni internet | ❌ | ✅ |
| Costo de adquisición | Medio–Alto | **Bajo** |
| Sin suscripción mensual obligatoria | ✅ | ✅ |

---

## 🎯 Estrategia de Implementación

El **GF-07** es el dispositivo adoptado para todos los escenarios de uso del proyecto. Su operación vía SMS lo hace funcional en cualquier contexto donde exista cobertura celular 2G, sin importar si el entorno es urbano, rural o de difícil acceso.

### 🐕‍🦺 Mascotas en Entorno Urbano
El GF-07 reporta ubicación con precisión de 100 a 500 metros en zonas de alta densidad celular. Suficiente para localizar al animal en el barrio o manzana donde se encuentre.

### 🐎 Mascotas en Zonas Rurales / Viajes
La cobertura 2G de Claro, Movistar y Tigo alcanza más del 95% del territorio colombiano, lo que permite rastrear a la mascota incluso en veredas y municipios alejados.

### 🦮 Entrenamiento / Control de Paseo
El comando `777****` permite solicitar ubicación en cualquier momento, y la alarma de sonido (`666****`) puede activarse remotamente si la mascota se pierde o queda quieta.

---

> ✅ **Dispositivo Final del Proyecto: GF-07**
> Seleccionado por su cobertura nacional, operación autónoma vía SMS, bajo costo de adquisición (~$30.000 COP) y compatibilidad con la infraestructura celular 2G disponible en Colombia.

---

## Justificación de la Selección del Dispositivo

La elección del GF-07 como dispositivo único de este proyecto responde a un análisis riguroso de los casos de uso específicos, las condiciones del mercado local y las restricciones presupuestarias definidas.

### GF-07 — El Rastreador de Amplia Cobertura

Para el rastreo de mascotas —con la necesidad de cobertura geográfica amplia que esto implica—, el GF-07 emerge como la opción más pragmática dentro del presupuesto disponible. A diferencia de los rastreadores GPS activos comerciales (TK103, Concox GT06N, Queclink GV55), que oscilan entre $150.000 y $400.000 COP solo en hardware más instalación profesional y plan de datos, el GF-07 puede adquirirse por entre $35.000 y $60.000 COP y operarse sin conocimientos técnicos avanzados.

La triangulación LBS, aunque menos precisa que el GPS puro, es suficiente para los objetivos principales del proyecto: confirmar si la mascota se ha desplazado de una zona general y reportar el barrio o sector donde se encuentra. En Colombia, donde la red celular 2G de Claro, Movistar y Tigo cubre más del 95% del territorio, el GF-07 ofrece una cobertura operativa superior a cualquier solución basada exclusivamente en Bluetooth.

> **Ventaja clave:** Es el único dispositivo evaluado que no depende de la proximidad física del usuario ni de la densidad de otros dispositivos para funcionar. Puede reportar su ubicación desde cualquier punto del país con cobertura 2G, lo que lo hace insustituible para rastreo de largo alcance en la región andina.
