# Práctica de búsqueda local
## Descripción
Práctica 2 del módulo 2 de la asignatura Ingeniería del Conocimiento donde se aplicará el algoritmo de Busqueda Tabú al TSP (problema del viajante) para n=100 ciudades

### Restricciones
- La ciudad de partida y llegada tiene que ser la misma, en nuestro caso, la ciudad es 0.
- La información de las distancias se leerán de un fichero que contiene únicamente la **matriz diagonal inferior** de distancias.
  - La columna i-ésima guarda las distancias entre la ciudad i-ésima y las ciudades i+1, i+2, ...
  - La diagonal de la matriz D es 0, ya que mediría la distancia a si misma.
  - La matriz es simétrica D(i, j)= D(j, i).
- Para generar los vecinos se empleará el operador de **intercambio de dos elementos**.
- Si se realizan 100 iteraciones y no se obtienen mejores soluciones que la actual, se volverá atras a la mejor solución del momento y se reiniciará la lista tabú, permitiendonos así explorar nuevas opciones.

### Solución inicial
La solución inicial será generada aleatoriamente, el rango de los índices será [1,n-1].

### Generación de vecinos
Para generar los vecinos solamente se empleará el operador de **intercambio de dos elementos**. Esto nos permite un número máximo de vecinos de:

![Máximo de Vecinos1](http://latex.codecogs.com/gif.latex?%5Csum_%7Bi%3D1%7D%5E%7Bn-2%7Di%3D%5Cfrac%7B%28n-1%29*%28n-2%29%7D%7B2%7D)

### Función de coste
Para calcular el coste total del problema se empleará la siguiente función:

![Función de coste](http://latex.codecogs.com/gif.latex?C%28S%29%3D%20D%280%2C%20S%5B0%5D%29%20&plus;%20%5Csum_%7Bi%3D1%7D%5E%7Bn-2%7D&plus;D%28S%5Bi-1%5D%2CS%5Bi%5D%29%20&plus;%20D%28S%5Bn-2%5D%2C0%29)

### Mecanismo de selección de soluciones
El criterio de aceptación será el **mejor** de todos los vecinos, siempre que no sea generado con un movimiento incluído en la lista tabú.

### Criterio de parada
Se finalizará la búsqueda cuando se realicen 1000 iteraciones de generación de vecinos.

## Compilación, generación de ejecutable y ejecución
El proyecto ha sido desarrollado empleando el IDE [IntelliJ IDEA 2016.2.5](https://www.jetbrains.com/idea/), por lo que la compilación, ejecución y la generación del .jar se ha realizado empleando este IDE, aunque se ha probado a ejecutar el .jar en una terminal para comprobar el funcionamiento del .jar generado.

### Compilación
Dentro del IDE se seleccionará la opción **Build** y dentro de esta la opción **Make Project**.

### Generación de ejecutable
Para generar el ejecutable dentro del IDE se seleccionará la opción **Build** y dentro de esta la opción **Build Artifacts...** y en el menú contextual que se despliega se seleccionará **Build**.

### Ejecución
#### Ejecución dentro del IDE
Dentro del IDE se tiene la opción de indicar los parámetros de entrada seleccionando la opción **Run** y posteriormente la opción **Edit configurations...**. Una vez en la pantalla de configuración, en el textbox para **Program arguments:** se indicarán los argumentos con los que queremos realizar la ejecución separados por un espacio. En nuestro caso se introducirá lo siguiente:
- Si queremos ejecutar sin leer un fichero de aleatorios, podemos no poner ningún argumento o poner como argumento el nombre del fichero de distancias, en mi caso:
  > distancias.txt
- Si queremos leer de un fichero de aleatorios nos vemos en la obligación de indicar el fichero de distancias y el de aleatorios en este orden, en mi caso:
  > distancias.txt aleatorios.txt

Para el correcto funcionamiento es necesario que los ficheros que se van a leer estén en el contenidos en el directorio inmediato del proyecto.

#### Ejecución en una terminal usando el .jar
El .jar está ubicado en la carpeta *out/artifacts/PracticaBusquedaTabu_jar**, el comando para ejecutarlo es el siguiente:

> java -jar PracticaBusquedaTabu.jar

Los argumentos que se le pueden pasar y el tipo de ejecución que se realizará en función de los que se le pasen, coincide con el apartado anterior.

Para el correcto funcionamiento es necesario que los ficheros que se van a leer estén en el mismo directorio que el **.jar**.
