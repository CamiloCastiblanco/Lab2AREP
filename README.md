# Servidor web multiples peticiones

En este proyecto se resuelven el siguiente ejercicio:

* Escriba un servidor web que soporte múlltiples solicitudes seguidas (no concurrentes). El servidor debe retornar todos los archivos solicitados, incluyendo páginas html e imágenes. Construya un sitio web con javascript para probar su servidor. Despliegue su solución en Heroku. NO use frameworks web como Spark o Spring use solo Java y las librerías para manejo de la red.

## Getting Started

Para hacer uso del proyecto solo debe clonar el repositorio o descargarlo directamente, para ejectarlo lo hacemos por medio de la consola usando el siguiente comando, al ejecutarlo el servidor ya quedará escuchando las peticiones.

```
java -cp target/lab2-1.0-SNAPSHOT.jar co.edu.escuelaing.lab2.HttpServer
```
### Prerequisites

Para usar el proyecto se necesitan tener las siguientes versiones de java o superior:


```
java version "1.8"
javac version "1.8"
Apache Maven 3.6.3
```

### Installing

Para usar el proyecto decargamos directamente el proyecto o lo clonamos de la siguiente manera:

En la consola nos vamos al directorio donde vamos a clonar el repositorio y ponemos el siguiente comando:

```
git clone https://github.com/CamiloCastiblanco/Lab2AREP.git
```

## Running the tests

Para probar el funcionamiento vamos a hacer crear un **HTTPServer**, vamos a hacer un pool de 100 hilos y en cada uno de los hilos vamos a crear un **HTTPClient** el cuál realizará una solicitud de la página **index.html**, con esto comprobaremos que el server es capaz de soportar multiples solicitudes de multiples clientes

Para correr el test usamos el siguiente comando

```
mvn test
```


## Authors

* **Brayan Camilo Castiblanco** 


## License

This project is licensed under the MIT License - see the [LICENSE.txt](LICENSE.txt) file for details

### Explicación

El **HttpServer** recibe las solicitud que hace el navegador, usando un pool de hilos procesa cada solicitud en un hilo nuevo,en la clase **ConsumerPeticiones** saca el nombre del archivo que se quiere de la solicitud y haciendo uso de un **Interprete**, el cual es un **singleton** que identifica la extensión del archivo y con ello usa la clase **Reader** y el método **toHtml** si se trata de un archivo con extensión **html**, **js** o **css** el cual convierte el archivo en una cadena de texto y si se trata de una imagen usa el método **readImage**, el cual dada la dirección de la imagen la transforma la imagen en un arreglo de **Bytes**, una vez convertidos los archivos en texto el Interprete los manda como respuesta al navegador, el cual solo mostrará el resultado.



