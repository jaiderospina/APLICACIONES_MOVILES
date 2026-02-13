# 📘 Manual Intermedio de Programación con PSeInt

## 1. ¿Qué es PSeInt?

PSeInt es una herramienta educativa diseñada para aprender lógica de programación mediante pseudocódigo en español. Permite escribir, ejecutar y depurar algoritmos antes de migrar a lenguajes como Python, Java o C++.

En nivel intermedio ya no solo se aprende sintaxis, sino:

* Diseño estructurado de algoritmos
* Modularización
* Manejo de estructuras de datos básicas
* Análisis lógico de eficiencia
* Buenas prácticas

---

# 2. Principios Fundamentales de Programación

## 2.1 Algoritmo

Un **algoritmo** es una secuencia finita, ordenada y lógica de pasos para resolver un problema.

Características:

* Preciso
* Determinístico
* Finito
* Eficiente

Ejemplo básico:

```
Proceso Suma
    Definir a, b, resultado Como Entero
    Escribir "Ingrese dos números:"
    Leer a, b
    resultado <- a + b
    Escribir "La suma es:", resultado
FinProceso
```

---

## 2.2 Variables y Tipos de Datos

En nivel intermedio debes dominar:

| Tipo     | Uso                   |
| -------- | --------------------- |
| Entero   | Números sin decimales |
| Real     | Números con decimales |
| Caracter | Un solo símbolo       |
| Cadena   | Texto                 |
| Lógico   | Verdadero/Falso       |

Buenas prácticas:

* Declarar siempre las variables
* Usar nombres descriptivos
* Inicializar cuando sea necesario

---

## 2.3 Estructuras de Control

### 2.3.1 Condicionales

Permiten tomar decisiones.

```
Si condicion Entonces
    instrucciones
Sino
    instrucciones
FinSi
```

Ejemplo intermedio:

```
Si nota >= 3 Entonces
    Escribir "Aprobado"
Sino
    Escribir "Reprobado"
FinSi
```

También puedes usar:

* Segun (switch)
* Condiciones anidadas
* Operadores lógicos: Y, O, No

---

### 2.3.2 Estructuras Repetitivas

Dominio obligatorio:

### 🔹 Mientras

```
Mientras condicion Hacer
    instrucciones
FinMientras
```

### 🔹 Para

```
Para i <- 1 Hasta 10 Con Paso 1 Hacer
    Escribir i
FinPara
```

### 🔹 Repetir-Hasta

```
Repetir
    instrucciones
Hasta Que condicion
```

Conceptos clave:

* Variable de control
* Condición de salida
* Prevención de bucles infinitos

---

# 3. Modularización (Nivel Intermedio Clave)

Aquí comienza la programación real.

## 3.1 SubProcesos (Funciones y Procedimientos)

Permiten dividir el problema.

### 🔹 Función (retorna valor)

```
Funcion resultado <- Sumar(a, b)
    resultado <- a + b
FinFuncion
```

### 🔹 SubProceso (no retorna valor)

```
SubProceso MostrarMensaje()
    Escribir "Hola mundo"
FinSubProceso
```

Beneficios:

* Reutilización
* Mantenimiento
* Claridad
* División del trabajo

---

# 4. Arreglos (Vectores y Matrices)

## 4.1 Vectores

Estructuras de datos lineales.

```
Definir numeros Como Entero
Dimension numeros[5]
```

Acceso:

```
numeros[1] <- 10
```

Recorrido típico:

```
Para i <- 1 Hasta 5 Hacer
    Leer numeros[i]
FinPara
```

---

## 4.2 Matrices

Estructuras bidimensionales.

```
Dimension matriz[3,3]
```

Recorrido clásico:

```
Para i <- 1 Hasta 3 Hacer
    Para j <- 1 Hasta 3 Hacer
        Leer matriz[i,j]
    FinPara
FinPara
```

Conceptos importantes:

* Índices
* Recorrido fila/columna
* Inicialización

---

# 5. Validación de Datos

Nivel intermedio implica robustez.

Ejemplo:

```
Repetir
    Escribir "Ingrese un número positivo:"
    Leer num
Hasta Que num > 0
```

Principio:
👉 Nunca confíes en la entrada del usuario.

---

# 6. Principios de Programación Estructurada

PSeInt sigue el paradigma estructurado propuesto por:

Edsger Dijkstra

Principios:

1. Secuencia
2. Selección
3. Iteración
4. No usar saltos innecesarios
5. Diseño descendente (Top-Down)

---

# 7. Diseño Descendente (Top-Down)

1. Definir el problema general
2. Dividir en subproblemas
3. Implementar cada módulo
4. Integrar

Ejemplo práctico:

Problema: Sistema de notas

Módulos:

* LeerNotas()
* CalcularPromedio()
* MostrarResultado()

---

# 8. Trazado y Depuración

PSeInt permite:

* Ejecutar paso a paso
* Ver valores de variables
* Detectar errores lógicos

Errores comunes:

* Variables no inicializadas
* Condiciones mal planteadas
* Desbordamiento de índices
* Bucles infinitos

---

# 9. Complejidad Básica (Introducción)

En nivel intermedio debes comenzar a pensar en eficiencia:

* Un ciclo simple → O(n)
* Ciclos anidados → O(n²)

Ejemplo:

```
Para i <- 1 Hasta n
    Para j <- 1 Hasta n
        Escribir i, j
    FinPara
FinPara
```

---

# 10. Buenas Prácticas

✔ Usar indentación clara
✔ Nombres descriptivos
✔ Dividir en funciones
✔ Validar entradas
✔ Evitar duplicación de código
✔ Documentar con comentarios

---

# 11. Ejercicio Integrador Intermedio

Desarrollar un programa que:

1. Solicite cantidad de estudiantes
2. Almacene notas en un vector
3. Calcule promedio usando función
4. Determine mayor y menor nota
5. Muestre resultados

Esto integra:

* Bucles
* Condicionales
* Funciones
* Arreglos
* Validación

---

# 12. Transición a Lenguajes Reales

PSeInt facilita migrar a:

* Python
* Java
* C++
* JavaScript

Porque enseña:

* Lógica estructurada
* Separación de responsabilidades
* Pensamiento algorítmico

---

# 🎯 Conclusión

En nivel intermedio con PSeInt debes dominar:

* Control de flujo complejo
* Modularización
* Estructuras de datos básicas
* Validación robusta
* Pensamiento algorítmico
* Organización y claridad

