## Ideas para comenzar mi proyecto

Tener 3 programas

1. El cliente asíncrono. Tendrá el mismo tipo de back-end que siempre, pero el archivo Application se modificacirá

2. Un servidor HTTP cuyo unico endpoint (tentativo a cambiar) sea el generador de CURPS

3. Un servidor HTTP cuyo unico endpoint sea el almacenador de CURPS

----

## Metas del programa

- [X] Completar comunicación entre el servidor principal y el de almacenamiento para guardar los CURPS

- [X] Hacer un sistema para solicitar información de los CURPS entre el servior principal y los de almacenamiento
  - [X] Idear un metodo para contar los CURPS que tengan las características especificadas por el profesor, ya sea dentro del almacenamiento o del servidor principal (posiblemente dentro del almacenador)

- Hacer un sistema para que el cliente solicite información al servidor principal y este le regrese la información solicitada
  - Hacer menú del cliente


----

## Ideas de cambio entre proyectos

1. El generador de CURPS será una función en lugar de una clase
   1. Esa función usará envío asincrono en lugar de Threads

2. Para mas variedad, podría hacerce que la función de envío de Curps sea una función Lamnda que usa un método específicamente para generarlos

3. Hacer que el envío y lectura de los datos del cliente sean una misma función que se ejecuta siempre
4. Separar las opciones como hombres y mujeres en 2 preguntas distintas
5. Las preguntas al servidor de almacenamiento se pueden hacer en las cabeceras en lugar del cuerpo
6. Se puede usar un objeto serializado para enviar una query y recibir en objetos diferentes
7. Hacer que la comunicación hacia otro servidor sea un método separado
   1. Alternativamente crear un cliente Http dentro del método para `/task` para recibir las solicitudes