# Propósito del Proyecto

### Este proyecto fue desarrollado como aplicación práctica de conceptos de desarrollo Android, implementando:
- Gestión de estado de aplicación
- Persistencia de datos
- Interacción con componentes 
- Validación de entrada de usuario
- Programación en Java


## Descripción General

Inventario App
Es una aplicación móvil Android desarrollada para la gestión eficiente de inventario de productos electrónicos. Implementa un sistema de almacenamiento persistente mediante SharedPreferences, permitiendo el control de cantidades de productos (Teclados, Mouse, Pantallas y Micrófonos) con capacidad de incremento, decremento y consulta en tiempo real.


Registro de productos (Teclado, Mouse, Pantalla, Micrófono)
Actualización de cantidades (suma y resta)
Consulta de inventario actual
Reinicio de contadores por producto
Almacenamiento persistente con SharedPreferences
Interfaz intuitiva con CheckBoxes y EditTexts
### Interfaz principal

 Funcionalidades

- Selección de artículos (teclado, mouse, pantalla, micrófono)  
- Ingreso de cantidades  
- Botón *Guardar* para almacenar los datos  
- Botón *Reset* para reiniciar el inventario  
- Botón *Mostrar* para visualizar la información registrada  

La app tiene 4 checkbox que permiten seleccionar el producto que se desea mostrar, guardar (ya sea sacar o ingresar al inventario) y hacer un reset completo del inventario. La app solo realizara la accion que el boton indique para la checkbox seleccionada.

<img src="https://github.com/jaiderospina/APLICACIONES_MOVILES/blob/main/TAREA1/CODIGO%20INVENTARIO/images/1000208893.png" alt="Arquitectura del Sistema" width="300" height="400" />

### Mostrar
Al usar este botón la app muestra el estado del inventario del producto seleccionado

<img src="https://github.com/jaiderospina/APLICACIONES_MOVILES/blob/main/TAREA1/CODIGO%20INVENTARIO/images/25.png" alt="Arquitectura del Sistema" width="300" height="400" />

### Guardar

Guardar permite que al seleccionar un producto y en el espacio donde la app permite una entrada numerica con numeros negativos guarda o saca del inventario segun los sognos que se usen, para sacar algo se debe usar el signo menos(-) mientras que para ingresar basa con poner el numero normal

<img src="https://github.com/jaiderospina/APLICACIONES_MOVILES/blob/main/TAREA1/CODIGO%20INVENTARIO/images/1000208895.png" alt="Arquitectura del Sistema" width="300" height="400" />  <img src="https://github.com/jaiderospina/APLICACIONES_MOVILES/blob/main/TAREA1/CODIGO%20INVENTARIO/images/1000208896.png" alt="Arquitectura del Sistema" width="300" height="400" /> 


### Características importantes:

Persistente: Los datos permanecen aunque cierres la app

Privado: Solo tu aplicación puede acceder a estos datos

Acumulativo: Cada vez que presionas "Almacenar", se suma a la cantidad existente (cant=cant+cani)

Reseteable: El botón "Reset" pone el inventario en 0 para los productos seleccionados

## ¿Cómo acceder al archivo donde se guardan los datos?

El dispositivo tiene root

Se usa Android Device Monitor desde Android Studio

Usas un emulador de Android

<img width="926" height="367" alt="image" src="https://github.com/user-attachments/assets/fc84de87-26ad-4b75-b67e-70a980c4662c" />



## Cómo Agregar Más Productos al Inventario


Método 1: Implementación Manual 

Este método consiste en replicar la estructura existente para cada nuevo producto que se desee agregar.
Pasos para agregar un nuevo producto (ejemplo: "Audífonos"):

 -Modificar el archivo XML (activity_main.xml): Agregar un nuevo bloque LinearLayout con un CheckBox y un EditText después de los productos existentes, asignando IDs únicos como ch_aud y n_aud.
 
 -Declarar variables en MainActivity.java: Crear las variables privadas correspondientes para el CheckBox y EditText del nuevo producto.

 -Inicializar en el método onCreate: Vincular las variables con los elementos de la interfaz mediante findViewById y configurar el validador de entrada numérica.

 -Inicializar en OnCreate.

<img width="454" height="781" alt="image" src="https://github.com/user-attachments/assets/3ade127f-62ff-41bc-9c75-cd51e76c3ba0" />



 Método 2: Sistema Dinámico con ArrayList 

Crear una clase auxiliar Producto: Esta clase encapsula el nombre del producto junto con sus componentes CheckBox y EditText.
Utilizar ArrayList: Almacenar todos los productos en una lista dinámica que se inicializa en el método onCreate.
Refactorizar métodos con bucles: Los métodos almacenar(), mostrar() y reset() se simplifican utilizando bucles for que iteran sobre la lista de productos.

Para agregar un nuevo producto: Solo es necesario agregar el bloque visual en el XML y una única línea de código en el método onCreate que añada el producto al ArrayList.

<img width="522" height="836" alt="image" src="https://github.com/user-attachments/assets/eecd890d-6b93-48fb-93cb-a74f1732656d" />


