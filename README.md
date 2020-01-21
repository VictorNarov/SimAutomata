# SimAutomata
Simulador de Autómatas Finitos Deterministas (AFD) y Autómatas Finitos No Deterministas (AFND) 
![](https://i.gyazo.com/322480c232793c201d06f7cf6b5d9233.png)

## Dependencias
###### Plataforma [Java JDK 13](https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html)
###### Librería [JGraphX 4.0.6](https://github.com/jgraph/jgraphx/releases/tag/v4.0.6)

## Paquete Autómata
El paquete Autómata permite la creación de Autómatas Finitos Deterministas (AFD.java) haciendo uso de las TransicionesAFD para poder procesar cadenas de entrada mediante el método reconocer(String cadena).
Asimismo, en el paquete Autómata, encontramos la clase AFND.java para la creación de los no deterministas, haciendo uso de las TransicionesAFND y TransicionesL (lambda).
La diferencia entre las Transiciones AFD y AFND radica en que estas últimas permiten al autómata transitar de un estado origen a un conjunto de estados destino, y no solamente a un estado, como nos restringe el AFD. 

## Paquete Principal
Este paquete contiene la clase main Interfaz.java la cual permite de forma accesible al usuario la creación/gestión de los autómatas.

Se nos permite crear el autómata definiendo, como si de forma teórica se tratase, los símbolos de entrada, el conjunto de estados, el estado inicial, el final y las transiciones mediante selectores que se irán actualizando conforme vayamos introduciendo estados y símbolos. Igualmente, se nos permite rectificar y eliminar lo ya introducido.

Como mejora destacable, hemos introducido un método comprobarDeterminismo() el cual es invocado antes de proceder a la simulación (si se trata de un AFD) y verifica si el AFD es determinista, es decir, tiene una transición para cada estado y símbolo (la tabla de transiciones está completamente rellena). Si no es así, se muestra un mensaje advirtiendo al usuario de que se va a crear un nuevo estado de absorción o muerto y las transiciones que conducirán a él desde los estados origen que carezcan de entrada en la tabla para algún símbolo. Esto se hace invocando al método agregarEstadoMuerto().

En la parte superior-central tenemos un botón selector autoexcluyente que permite seleccionar el tipo de autómata. Al pasar de AFD a AFND, se agregará el símbolo “L” a la tabla de transiciones para representar las transiciones que usen la palabra vacía lambda y selector de estados destino se convertirá en un botón que despliega un nuevo panel que permitirá la selección de múltiples estados destino (implementado en la clase panelEstados.java).

En la parte izquierda, podemos encontrar la tabla de transiciones, que igualmente se irá actualizando de forma dinámica. Podemos clicar en el botón inferior y seleccionar las filas para eliminar las transiciones asociadas a ese estado origen. De la misma manera, en la parte inferior-central, encontramos un botón para reiniciar completamente el autómata y todos los estados y símbolos asociados al mismo.

Para definir las transiciones y el estado inicial, encontramos selectores (combobox) que, de forma dinámica conforme vayamos introduciendo los símbolos y estados, irán añadiendo ítems para que los podamos seleccionar.

Más abajo, encontramos un campo de texto editable donde introduciremos la cadena a procesar por el autómata. Para ello, clicaremos en el botón “SIMULAR” si lo queremos hacer instantáneamente (se invoca al método reconocer del autómata) y se muestra el resultado en forma de ventana de diálogo emergente. 

En cambio, si la simulación la queremos hacer paso a paso, usaremos los botones de “Iniciar”, “Anterior”, “Siguiente” para iterar en el procesado de los diferentes símbolos de entrada, de forma que podamos ver en todo momento los estados activos y el símbolo a leer. El resultado se mostrará en unos label que se harán visibles en la parte derecha.
Para esto, a medida que evoluciona el autómata, se va guardando el estado actual en una pila de estado (AFD), o pila de conjuntos de estados (AFND). 
Nota: el botón “Iniciar” se convierte en “Reiniciar” automáticamente tras empezar la simulación paso a paso.

Por último, en la parte superior-derecha tenemos botones que nos permitirán guardar un autómata en un fichero con la siguiente estructura, y cargar un autómata a partir de un fichero construido previamente.
![](https://gyazo.com/c5f758c84f509992d1518193053ef6df.png)

## Paquete Grafo
Este paquete contiene la clase ManejaGrafo.java la cual hace uso de la librería externa JGraphX para la representación visual de los autómatas y el manejo de su atributo de tipo Grafo, el cual hereda de la clase mxGraph de la librería mencionada anteriormente.
La interfaz contiene el método actualizarGrafica() el cual refresca dinámicamente su grafo incrustado al introducir un nuevo estado, símbolo o transición, o efectuar algún paso de la simulación.
Para la simulación paso a paso, hemos establecido un código de colores en el grafo para los estados, de forma que verde significa estado activo, blanco: estado inicial y negro: estado final.
  
![](https://gyazo.com/774e0880a818f6083fe0e6ebe6144fdc.png)
