# 🐾 PetTrack: Proyecto de Integración de Dispositivos de Rastreo de Mascotas

[cite_start]Este repositorio contiene el **Análisis Técnico de Hardware** y la **Guía de Optimización** para el desarrollo de una solución integral de tracking de mascotas en la región andina[cite: 1, 10, 16]. [cite_start]El objetivo es integrar diferentes capas tecnológicas (GSM, BLE Mesh y BLE Directo) para garantizar la localización del animal en cualquier escenario[cite: 132].

---

## 📋 Tabla de Contenidos
* [Arquitectura del Proyecto](#-arquitectura-del-proyecto)
* [Metodología de Evaluación de Hardware](#-metodología-de-evaluación-de-hardware)
* [Capas de Rastreo Soportadas](#-capas-de-rastreo-soportadas)
    * [1. Capa de Largo Alcance (GF-07)](#1-capa-de-largo-alcance-gsm2g)
    * [2. Capa Urbana / Mesh (Finein Tag)](#2-capa-urbana--mesh-find-my)
    * [3. Capa de Proximidad (iTag)](#3-capa-de-proximidad-bluetooth)
* [Análisis Comparativo con Estándares Globales](#-análisis-comparativo-con-estándares-globales)
* [Estrategia de Implementación por Caso de Uso](#-estrategia-de-implementación-por-caso-de-uso)

---

## 🏗️ Arquitectura del Proyecto
[cite_start]El proyecto se basa en la **complementariedad tecnológica** para resolver los problemas de conectividad típicos en Colombia y Latinoamérica[cite: 16, 132, 150]:
* [cite_start]**Visión:** Crear un sistema que balancee precisión, autonomía y costo.
* [cite_start]**Alcance:** Desde la prevención de olvido (proximidad) hasta la recuperación en zonas rurales (celular)[cite: 149].

---

## 🧪 Metodología de Evaluación de Hardware
[cite_start]Para seleccionar los dispositivos compatibles con la App, se ejecutó un protocolo de tres fases[cite: 18]:
1. [cite_start]**Laboratorio:** Pruebas de consumo energético y latencia de respuesta a comandos[cite: 19].
2. [cite_start]**Campo:** Pruebas de señal en entornos urbanos (**Bogotá**), periurbanos (**Cajicá**) y rurales (**Vía Zipaquirá-Ubaté**)[cite: 20].
3. [cite_start]**Costo-Beneficio:** Análisis de viabilidad económica para el mercado local[cite: 21].

---

## 🛰️ Capas de Rastreo Soportadas

### 1. Capa de Largo Alcance (GSM/2G)
[cite_start]**Hardware Base:** GF-07[cite: 23].
[cite_start]Ideal para mascotas que viajan a zonas rurales o donde no hay otros smartphones cerca[cite: 119].
* [cite_start]**Tecnología:** Triangulación de torres celulares (LBS) vía redes 2G[cite: 11, 24].
* **Interfaz de Control (API SMS):**
    * [cite_start]`000****`: Vincular número maestro[cite: 33].
    * [cite_start]`777****`: Solicitar ubicación (Google Maps Link)[cite: 36].
    * [cite_start]`666****`: Escucha ambiental (se activa si hay sonidos >40 dB)[cite: 38, 39].
* [cite_start]**Optimización:** Requiere configuración manual de APN según el operador (Claro, Movistar, Tigo)[cite: 48, 49].

### 2. Capa Urbana / Mesh (Find My)
[cite_start]**Hardware Base:** Finein Tag[cite: 56].
[cite_start]Utiliza la red descentralizada de Apple para localizar a la mascota en ciudades densas[cite: 57, 58].
* [cite_start]**Funcionamiento:** Emisión de señales BLE 5.0 cifradas[cite: 58, 59].
* [cite_start]**Ventaja en Colombia:** En ciudades como Bogotá, la densidad de dispositivos Apple (>35% del mercado) garantiza una actualización constante de la ubicación[cite: 64].
* [cite_start]**Autonomía:** 8-12 meses con batería CR2032[cite: 65].

### 3. Capa de Proximidad (Bluetooth)
[cite_start]**Hardware Base:** iTag Genérico[cite: 71, 72].
[cite_start]Diseñado para la **prevención de extravío** mediante alarmas de proximidad en tiempo real[cite: 73, 127].
* [cite_start]**Protocolo:** Bluetooth 4.0 con rango de 10-25 metros[cite: 74].
* [cite_start]**Funcionalidad:** Alerta inmediata en el teléfono cuando la mascota se aleja del rango establecido[cite: 145].

---

## 📊 Análisis Comparativo con Estándares Globales

| Característica | GF-07 | Finein Tag | iTag | AirTag | SmartTag2 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Precisión** | [cite_start]100-500 m [cite: 88] | [cite_start]5-15 m [cite: 88] | [cite_start]10-25 m [cite: 88] | [cite_start]~1 m [cite: 88] | [cite_start]5-10 m [cite: 88] |
| **Red de búsqueda** | [cite_start]Parcial [cite: 88] | [cite_start]Sí [cite: 88] | [cite_start]No [cite: 88] | [cite_start]Sí [cite: 88] | [cite_start]Sí [cite: 88] |
| **Requiere SIM** | [cite_start]Sí [cite: 88] | [cite_start]No [cite: 88] | [cite_start]No [cite: 88] | [cite_start]No [cite: 88] | [cite_start]No [cite: 88] |
| **Batería** | [cite_start]2-5 días [cite: 88] | [cite_start]8-12 meses [cite: 88]| [cite_start]6-12 meses [cite: 88]| [cite_start]12 meses [cite: 88]| [cite_start]6-7 meses [cite: 88]|
| **Costo (COP)** | [cite_start]$35k-$60k [cite: 88] | [cite_start]$60k-$90k [cite: 88] | [cite_start]$20k-$40k [cite: 88] | [cite_start]$299k [cite: 88] | [cite_start]$180k [cite: 88] |

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
