# 🐾 PetTrack: Proyecto de Integración de Dispositivos de Rastreo de Mascotas

Este repositorio contiene el **Análisis Técnico de Hardware** y la **Guía de Optimización** para el desarrollo de una solución integral de tracking de mascotas en la región andina, El objetivo es integrar diferentes capas tecnológicas (GSM, BLE Mesh y BLE Directo) para garantizar la localización del animal en cualquier escenario.

---



## Introducción
En el panorama tecnológico actual, los dispositivos de rastreo personal y de activos han experimentado una evolución acelerada impulsada por la miniaturización de componentes, la expansión de redes celulares de baja latencia y la consolidación de ecosistemas de conectividad como Bluetooth Low Energy (BLE). Este proyecto nació de la necesidad de evaluar, comparar y optimizar tres soluciones de rastreo ampliamente disponibles en el mercado de consumo latinoamericano, con el objetivo de proporcionar una guía técnica rigurosa para usuarios finales, técnicos y administradores de sistemas de seguridad.

Los dispositivos evaluados responden a diferentes filosofías tecnológicas: el rastreador GF-07 opera sobre infraestructura celular 2G mediante triangulación de torres (LBS); el Finein Tag aprovecha la red de dispositivos Apple a través de Bluetooth Low Energy cifrado; y los iTags genéricos funcionan como alarmas de proximidad punto a punto. Cada uno ocupa un nicho específico dentro del espectro de soluciones de rastreo, con ventajas y limitaciones intrínsecas que este informe explora en profundidad.

Adicionalmente, se incorpora en esta edición ampliada un análisis comparativo frente a los dispositivos líderes del mercado global —Apple AirTag, Samsung Galaxy SmartTag2 y Tile Pro— con el fin de contextualizar las capacidades de los dispositivos seleccionados y fundamentar las razones técnicas y económicas de su elección para los escenarios de uso definidos en este proyecto.

# 🧩 Arquitectura del Sistema

Componentes Principales
1.	Dispositivo Collar (Hardware)


Módulo GPS: GF-07

Módulo GSM: SIM800L para comunicación celular

Batería: LiPo 3.7V 1000mAh recargable

Cargador: TP4056 con protección integrada

2.	Frontend (Aplicación Web)
   
Framework: React 19

Estilos: Tailwind CSS 4

Mapas: Google Maps JavaScript API

Componentes: shadcn/ui

State Management: React Query + tRPC

3.	Integraciones Externas
   
SMS/Llamadas: Twilio API

Mapas: Google Maps API

Almacenamiento: AWS S3 (para fotos de mascotas)

## ⚙️ Tecnologías
🔌 Hardware

GPS GF-07
SIM800L (GSM/GPRS)
Batería LiPo 3.7V
TP4056
LM2596
💻 Software
React + Tailwind CSS
Node.js
Google Maps API
Twilio API
AWS S3

## Requisitos funcionales
Los requisitos funcionales definen las capacidades específicas que el sistema PetTrack debe proporcionar:

RF1: Gestión de Mascotas

 	El sistema debe permitir al usuario registrar nuevas mascotas con los siguientes datos: nombre, foto, número SIM del collar y número de teléfono para alertas.
 	El usuario debe poder visualizar un listado de todas sus mascotas registradas en un dashboard principal.
 	Cada mascota debe mostrar su última ubicación conocida, nivel de batería actual y estado de conexión del dispositivo.
   
RF2: Rastreo en Tiempo Real

 	La aplicación debe mostrar la ubicación actual de cada mascota en un mapa interactivo (Google Maps).
 	El usuario debe poder solicitar una actualización de ubicación mediante un botón “Solicitar Ubicación” que envía un comando al dispositivo.
 	La ubicación debe actualizarse en tiempo real cuando el dispositivo envía nuevas coordenadas GPS.
   
RF3: Consulta de Estado

 	El usuario debe poder consultar el estado actual del dispositivo (nivel de batería, señal de red, estado de conexión) mediante un botón “Consultar Estado”.
 	El sistema debe mostrar el historial de ubicaciones de cada mascota con timestamps.
   
RF4: Sistema de Geocerca

 	El usuario debe poder definir una zona segura (geocerca) con un radio configurable desde un punto de origen (hogar).
 	El sistema debe calcular continuamente la distancia entre la ubicación actual de la mascota y el centro de la geocerca.
 	Cuando la mascota supera el radio configurado, el sistema debe activar automáticamente alertas.
   
RF5: Alertas Automáticas

 	Al activarse una alerta de geocerca, el sistema debe enviar automáticamente un mensaje de texto (SMS) al número registrado.
 	El SMS debe incluir un enlace directo a Google Maps con las coordenadas exactas de la mascota.
 	El sistema debe realizar una llamada de voz automática al número registrado para asegurar la atención inmediata del usuario.
	 	El usuario debe poder visualizar el historial de todas las alertas generadas.
      
RF6: Historial y Reportes

 	El sistema debe mantener un registro completo de todas las ubicaciones registradas por mascota.
 	El sistema debe almacenar un historial de todas las alertas generadas, incluyendo tipo, fecha, hora y estado de envío (SMS/llamada).
 	El usuario debe poder descargar un informe técnico en PDF con los requisitos del proyecto.

## Requisitos no funcionales.

Los requisitos no funcionales establecen los criterios de calidad y restricciones técnicas:

RNF1: Rendimiento

 	La aplicación web debe cargar completamente en menos de 3 segundos en conexiones de 4G.
Las consultas a la base de datos no deben exceder 500ms de latencia.

El mapa interactivo debe renderizar sin lag con hasta 50 marcadores simultáneos.

RNF2: Disponibilidad

 	El sistema debe mantener una disponibilidad del 99.5% durante el horario de operación.
 	El dispositivo debe reconectarse automáticamente a la red celular en caso de pérdida temporal de señal.
 	Las alertas deben procesarse y enviarse en menos de 30 segundos desde su activación.
   
RNF3: Seguridad

 	Todas las comunicaciones entre la aplicación web y el servidor deben ser encriptadas con HTTPS/TLS.
 	Los números de teléfono y datos personales deben almacenarse de forma segura en la base de datos.
 	La autenticación debe implementarse mediante OAuth 2.0 para garantizar la privacidad del usuario.
 	El acceso a los datos de mascotas debe estar restringido al propietario autenticado.
   
RNF4: Usabilidad

 	La interfaz debe ser responsiva y funcionar correctamente en dispositivos móviles (iOS, Android) y de escritorio.
El diseño debe seguir principios de accesibilidad (WCAG 2.1 AA).
La navegación debe ser intuitiva con máximo 3 clics para acceder a cualquier funcionalidad.

RNF5: Confiabilidad

 	El sistema debe contar con mecanismos de reintentos automáticos para el envío de SMS y llamadas.
 	Los datos de ubicación deben persistirse en la base de datos incluso si la conexión se interrumpe.
 	El dispositivo debe incluir validación de datos GPS para descartar lecturas erróneas.
   
RNF6: Escalabilidad

 	La arquitectura debe soportar el monitoreo simultáneo de hasta 100 mascotas.
La base de datos debe estar optimizada con índices para consultas rápidas.
El sistema debe permitir la adición de nuevos dispositivos sin reconfiguración del servidor.

RNF7: Mantenibilidad

 	El código debe seguir estándares de codificación consistentes y estar bien documentado.
El sistema debe incluir logs detallados de todas las operaciones críticas.
Debe existir un proceso de actualización de firmware para los dispositivos sin interrumpir el servicio.

## Materiales

| Componente   | Precio (COP) |
| ------------ | ------------ |
| ESP32        | $29.900      |
| GPS NEO-6M   | $29.600      |
| SIM800L      | $30.000      |
| Batería LiPo | $18.000      |
| TP4056       | $4.500       |
| LM2596       | $6.000       |
| SIM Card     | $5.000       |
| Carcasa      | $8.000       |
| Cables       | $7.000       |
| **TOTAL**    | **$138.000** |

## Costos Operativos 

| Concepto      | Costo mensual |
| ------------- | ------------- |
| Datos SIM     | $15.000       |
| Servidor      | $50.000       |
| SMS/Llamadas  | $20.000       |
| Mantenimiento | $10.000       |
| **TOTAL**     | **$95.000**   |


## 🛰️ Evaluación de los dispositivos 

### 1. Rastreador GF-07 (GSM/GPRS)

<img width="500" height="500" alt="image" src="https://github.com/user-attachments/assets/62199d12-eb58-49eb-8453-f497b28a04cd" />

El GF-07 es un dispositivo que, aunque comercializado bajo la etiqueta de "rastreador GPS", opera exclusivamente mediante triangulación de torres celulares (LBS – Location Based Services) a través de redes 2G. Esta distinción es fundamental para comprender tanto sus capacidades reales como sus limitaciones inherentes. La ausencia de un módulo GNSS (GPS/GLONASS) le otorga ventajas de costo y tamaño, pero restringe su precisión a un rango típico de 100 a 500 metros en áreas urbanas y hasta 2-3 kilómetros en zonas rurales con baja densidad de infraestructura celular.

Desde el punto de vista de la ingeniería de hardware, el GF-07 integra un módulo SIM800L o equivalente, compatible con bandas GSM 850/900/1800/1900 MHz. La comunicación con el usuario se realiza exclusivamente vía SMS bidireccional y llamadas de voz, sin requerir acceso a internet desde el lado del usuario. Esta característica lo hace especialmente resiliente en contextos donde el usuario final tiene conectividad limitada o prefiere canales de comunicación simples y auditables.
1.1. Guía de Comandos SMS Avanzados
Para operar el GF-07, es necesario enviar comandos SMS específicos al número de la tarjeta SIM insertada en el dispositivo. A continuación, se detalla la lista completa de comandos y su función:

•	000**** Vincular Número Maestro: Este es el paso inicial obligatorio. Al enviar 000****, el rastreador vincula el número de teléfono del remitente como el "maestro". Solo este número podrá recibir alertas y controlar el dispositivo.
•	777**** Solicitar Ubicación: El comando más utilizado. El dispositivo responderá con un mensaje de texto que incluye un enlace a Google Maps con su ubicación aproximada basada en LBS.
•	888**** Consultar Estado: Devuelve un informe detallado que incluye el nivel de batería, la intensidad de la señal GSM, la capacidad disponible en la tarjeta MicroSD y el estado de las funciones activas.
•	666**** Alarma por Sonido: Activa el modo de monitoreo acústico. Si el micrófono detecta un sonido superior a 40 dB, llamará automáticamente al número maestro vinculado.
•	555**** Iniciar Grabación de Audio: Ordena al dispositivo que comience a grabar el audio ambiental y lo guarde en la tarjeta MicroSD insertada.
•	445**** Formatear Tarjeta MicroSD: Borra todo el contenido almacenado en la tarjeta MicroSD.
•	999**** Reinicio Remoto: Fuerza un reinicio del dispositivo. Útil si el rastreador deja de responder a los comandos.
•	444**** Cancelar Tareas: Detiene cualquier tarea en curso, como la grabación de audio o la alarma por sonido.
•	FACTORY#**** Restablecimiento de Fábrica: Restaura el dispositivo a su configuración original, borrando el número maestro vinculado.

1.2. Estrategias de Optimización
La maximización del rendimiento del GF-07 requiere atender tres dimensiones críticas: la configuración de red, la gestión energética y la colocación física del dispositivo.

•	Configuración APN: Optimización de Red y APN: El problema más frecuente es la falta de respuesta al comando 777****. Esto se debe generalmente a una configuración incorrecta del APN. Se recomienda configurar el APN manualmente con el formato APN,nombre_del_apn#. Para operadoras colombianas: Claro usa "internet.claro.com.co", Movistar usa "internet.movistar.com.co", Tigo usa "web.colombiamovil.com.co". La SIM debe ser compatible con 2G y no tener PIN activado.
•	Gestión Energética: Gestión de Energía: La batería interna ofrece 2-3 días en modo espera. Es fundamental desactivar la alarma de sonido (666****) cuando no sea necesaria, mediante el comando 444****. En modo solo-ubicación, la autonomía puede extenderse hasta 5 días reduciendo la frecuencia de consultas.
•	Precisión LBS: Mejora de Precisión LBS: La precisión mejora en zonas urbanas densas. Debe colocarse en zonas de buen paso de señal de radio, lejos de blindajes metálicos cerrados.

### 2. Finein Tag (Etiqueta Apple Find My)
<img width="480" height="655" alt="image" src="https://github.com/user-attachments/assets/46edcbe2-9091-40bf-9475-d9db59057b8c" />

El Finein Tag representa una solución técnicamente sofisticada que aprovecha la infraestructura de la red "Encontrar" de Apple, compuesta por cientos de millones de dispositivos activos en todo el mundo. A diferencia del GF-07, este dispositivo no requiere tarjeta SIM ni plan de datos propio: su conectividad se sustenta en el protocolo Bluetooth Low Energy (BLE 5.0) y en el ecosistema descentralizado de Apple.

El funcionamiento se basa en la emisión continua de señales BLE cifradas con rotación de claves periódica, lo que garantiza la privacidad del usuario al mismo tiempo que permite la localización indirecta. Cualquier dispositivo Apple (iPhone, iPad, Mac, Apple Watch) con la red "Encontrar" activada que entre en rango del Finein Tag captura la señal, cifra la ubicación con la clave pública del propietario y la envía a los servidores de iCloud. Solo el propietario puede descifrar esta información con su clave privada, garantizando que ni Apple ni terceros tengan acceso a los datos de ubicación.
2.1. Estrategias de Optimización
•	Densidad de Red: Maximizar Cobertura: La eficacia es directamente proporcional a la densidad de usuarios Apple en la zona. En Bogotá, la penetración de iPhone supera el 35% del mercado smartphone, lo que proporciona una red de detección muy densa en entornos urbanos.
•	Batería: Gestión de la Batería CR2032: La pila estándar dura entre 8 y 12 meses. Evitar activar el sonido innecesariamente prolonga la vida útil. La desconexión frecuente es el primer síntoma de batería baja.
•	Modo Perdido: Modo Perdido: Activar inmediatamente en caso de extravío. Genera notificaciones en tiempo real y bloquea el emparejamiento a otras cuentas Apple.
•	Colocación Estratégica: Para uso antirrobo en vehículos, ocultar en zonas no accesibles. Las alertas anti-acoso de Apple se activan tras 8-24 horas de movimiento con un tag ajeno.
 


### 3. iTag / Smart Tag Genérico (Bluetooth)
<img width="1000" height="1000" alt="image" src="https://github.com/user-attachments/assets/46d1969f-8529-43d0-a321-a7e0ecb66407" />

Los iTags genéricos son la solución de menor costo y complejidad técnica dentro de la comparativa. Diseñados como alarmas de proximidad bidireccionales, su principal valor radica en la inmediatez de la alerta: la notificación se produce en el instante en que la conexión Bluetooth se interrumpe, sin latencia de red ni dependencia de servidores externos.

El protocolo de comunicación es Bluetooth 4.0 (BLE) en la banda de 2.4 GHz, con un rango efectivo que varía entre 10 y 25 metros según las condiciones ambientales. La aplicación companion (típicamente iSearching para Android o FindMy para iOS) gestiona la lógica de alertas, el registro de la última ubicación conocida al momento de la desconexión y la función de búsqueda por sonido bidireccional. La ausencia de conectividad de red propia es su principal limitación frente a los otros dispositivos evaluados, pero también su mayor fortaleza en términos de simplicidad operativa y privacidad.
3.1. Estrategias de Optimización
•	Permisos del SO: Otorgar permisos de "Ubicación: Permitir siempre" y configurar la batería de la app como "Sin restricciones" en Android para evitar que el SO elimine el proceso en segundo plano.
•	Alerta Bidireccional: Configurar la app para que tanto el teléfono como el iTag emitan alerta al perder conexión. El umbral óptimo es entre 10-15 metros para evitar falsas alarmas.
•	Gestión de Interferencias: Limitar el número de dispositivos Bluetooth activos simultáneos (auriculares, relojes, etc.) para reducir interferencias en la banda 2.4 GHz.
 


## 📊 Análisis Comparativo con Estándares Globales

| Dispositivo | Tecnología | Precisión | Cobertura | Precio |
| ----------- | ---------- | --------- | --------- | ------ |
| GF-07       | GSM        | Baja      | Nacional  | Bajo   |
| Finein Tag  | BLE + red  | Media     | Global    | Medio  |
| iTag        | BLE        | Baja      | Local     | Bajo   |
| AirTag      | BLE + UWB  | Alta      | Global    | Alto   |
| SmartTag2   | BLE        | Media     | Global    | Medio  |


---

## 🎯 Estrategia de Implementación por Caso de Uso

### 🐕‍🦺 Mascotas en Entorno Urbano
[cite_start]Se recomienda el **Finein Tag** como dispositivo principal por su balance de precisión y autonomía[cite: 121, 140].

### 🐎 Mascotas en Zonas Rurales / Viajes
Se recomienda el **GF-07**. [cite_start]Es el único que reporta ubicación nacional sin depender de la proximidad de otros smartphones, siempre que haya cobertura 2G[cite: 119, 135].

### 🦮 Entrenamiento / Control de Paseo
[cite_start]El **iTag genérico** es la solución óptima para evitar que la mascota se aleje durante paseos sin correa mediante la alerta de desconexión inmediata[cite: 131, 145].

---
> [cite_start]**Recomendación Final:** Para una cobertura total con presupuesto reducido (<$200.000 COP), la combinación de las tres tecnologías es la inversión óptima frente a soluciones premium cerradas[cite: 153].
## Justificación de la selección de dispositivos
Justificación de la Selección de Dispositivos
La selección del GF-07, el Finein Tag y los iTags genéricos para este proyecto no es arbitraria: responde a un análisis riguroso de los casos de uso específicos, las condiciones del mercado local y las restricciones presupuestarias definidas. A continuación se detallan los argumentos técnicos y económicos que sustentan cada elección.

1. GF-07: El Rastreador vehicular Accesible
Para el caso de uso de rastreo vehicular —el de mayor riesgo y mayor necesidad de cobertura geográfica amplia—, el GF-07 emerge como la opción más pragmática dentro del presupuesto disponible. A diferencia de los rastreadores GPS activos comerciales (TK103, Concox GT06N, Queclink GV55), que cuestan entre $150.000 y $400.000 COP solo en hardware, más instalación profesional y plan de datos, el GF-07 puede adquirirse por $35.000-60.000 COP y operarse sin conocimientos técnicos avanzados.

La triangulación LBS, aunque menos precisa que el GPS, es suficiente para los dos objetivos principales del rastreo vehicular antirrobo: confirmar si el vehículo se ha movido de una zona general y reportar la ciudad o barrio donde se encuentra. En Colombia, donde la red celular 2G de Claro, Movistar y Tigo cubre más del 95% del territorio, el GF-07 ofrece una cobertura operativa superior a cualquier solución basada exclusivamente en Bluetooth.

Ventaja clave del GF-07: Es el único dispositivo de la selección que no depende de la proximidad física del usuario ni de la densidad de otros usuarios para funcionar. Puede reportar su ubicación desde cualquier punto del país con cobertura celular 2G, lo que lo hace insustituible para rastreo de largo alcance.

2. Finein Tag: Integración al Ecosistema de Mayor Alcance
Para el rastreo de objetos personales de valor medio-alto (mochilas, maletas de viaje, equipos fotográficos), el Finein Tag ofrece la mejor relación entre precisión, autonomía y costo de operación. Frente al AirTag —su referente tecnológico directo—, el Finein Tag aprovecha exactamente la misma infraestructura Find My a una fracción del costo, siendo compatible con el mismo ecosistema sin sacrificar funcionalidades esenciales.

La elección del Finein Tag sobre el AirTag se fundamenta en tres razones: primero, el ahorro de $200.000-240.000 COP por unidad permite adquirir múltiples dispositivos para equipar diferentes objetos con el mismo presupuesto; segundo, su certificación MFi garantiza compatibilidad oficial con la red Find My sin las restricciones de los clones no certificados; tercero, el diseño compacto y el peso reducido (menor que el AirTag en algunos modelos) facilita su integración discreta en objetos donde el espacio es limitado.

