
# 🧠 Actividad Evaluativa – Estacionamiento en PSeInt

## 🎯 Título

**Sistema de cobro para estacionamiento**

## 📝 Contexto del problema

Una empresa de estacionamientos desea automatizar el cálculo del cobro según el tipo de vehículo y el tiempo de permanencia.

El programa deberá desarrollarse en **PSeInt**, aplicando:

* Estructuras condicionales
* Validaciones con ciclos
* Operaciones aritméticas
* Conversión de minutos a horas
* Presentación de resultados en formato resumen

---

## 📌 Requisitos del Programa

El algoritmo debe:

1. Solicitar la **placa del vehículo**.
2. Solicitar el **tipo de vehículo** (únicamente):

   * motocicleta
   * auto
   * camioneta
3. Solicitar el **tiempo en minutos** (mayor que 0).
4. Validar que:

   * El tipo de vehículo sea correcto.
   * Los minutos sean mayores a cero.
   * Si los datos no son válidos, el sistema debe volver a pedirlos.
5. Calcular:

   * Precio base según tarifa por minuto.
   * Descuento según tiempo.
   * Precio final.
6. Convertir los minutos a **horas y minutos**.
7. Mostrar un **cuadro resumen final** con toda la información.

---

## 💰 Tarifas

| Tipo de Vehículo | Precio por minuto |
| ---------------- | ----------------- |
| Motocicleta      | S/ $ 60           |
| Auto             | S/ $ 120          |
| Camioneta        | S/ $ 300          |

---

## 🎯 Descuentos

| Tiempo estacionado        | Descuento |
| ------------------------- | --------- |
| Más de 1 hora (>60 min)   | 10%       |
| Más de 2 horas (>120 min) | 20%       |
| Más de 3 horas (>180 min) | 30%       |

⚠️ Importante:
Los descuentos no se acumulan. Solo se aplica el mayor descuento correspondiente.

---

# 📊 Criterios de Evaluación

| Criterio                                      | Puntaje       |
| --------------------------------------------- | ------------- |
| Validación del tipo de vehículo               | 2 pts         |
| Validación de minutos (>0)                    | 2 pts         |
| Cálculo correcto del precio base              | 2 pts         |
| Cálculo correcto del descuento                | 2 pts         |
| Cálculo correcto del precio final             | 2 pts         |
| Conversión correcta a horas y minutos         | 2 pts         |
| Presentación del cuadro resumen               | 2 pts         |
| Orden, claridad y uso adecuado de estructuras | 2 pts         |
| **Total**                                     | **16 puntos** |

---

# 📌 Estructura Esperada del Algoritmo

El estudiante deberá usar:

* `Mientras` para validaciones
* `Segun` o `Si` para determinar tarifa
* Operadores matemáticos
* División entera y módulo para convertir tiempo:

  * horas = minutos DIV 60
  * minutos_restantes = minutos MOD 60

---

# 📋 Ejemplo de Salida Esperada

```
----------------------------------------
        RESUMEN DE ESTACIONAMIENTO
----------------------------------------
Placa: ABC-123
Tiempo: 3 horas y 20 minutos
Precio base: $ 50.00
Descuento aplicado: $ 10.00
Importe final: $ 40.00
----------------------------------------
```
# **NOTA:**

# **Se debe realizar tanto algoritmo como diagrama de flujo y agragar entrega en carpeta individual**


---


