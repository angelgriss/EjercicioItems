# EjercicioItems
Este proyecto consiste en una aplicacion que se puede ejecutar utilizando vista html o bien directamente desde un cliente http para ejecutar los distintos métodos.

La base de datos no relacional escogida para este ejercicio fue ElasticSearch.
Para ejecutar el proyecto se puede hacer desde la carpeta src/main/java/controller:
  1. clase ItemController para levantar el servicio en el puerto 9393 y consumir las apis por medio de postman o curl
  2. clase ItemControllerVistas para levatar el servicio en el puerto 9494 y acceder a la vista web


<pre>
 Behaviour                           | Input   | Output                 |
------------------------------------+---------+------------------------|
 Elevator stops on lowest floor     | [1,3,4] | Doors open on floor: 1 |
 Elevator passes floors not entered | [2,3,4] | Passing floor: 1, Doors open on floor: 2 |
</pre>
