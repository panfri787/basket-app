# Microservicios competicion baloncesto

En el repo se encuentran dos proyectos SpringBoot que componen un ejemplo de una básica aquitectura de microservicios para gestionar una competicion de baloncesto.

## Microservicio "partidos"

El primero de ellos es un microservicio orientado a la notificación de resultados de los arbitros cuando termina el partido. Para simplificar el ejemplo, se ha expuesto un controller que tiene un dos endpoints. El primero es un PUT a la ruta `/match/{id}` cuyo payload es el siguiente:

`
{
    "teamAScore": 1,
    "teamBScore": 1
}
`

El objtetivo de este endpoint es el de informar el resultado de un partido. Para ello, despues de almacenar en BBDD el resultado del partido lanza un evento a una cola de RabbitMQ que sera consumida por el otro microservicio.

El segundo endpoint es un GET a la ruta `/matches` que puede llevar de forma opcional el queryparam completed que es un booleano, ej: `/matches?completed=false` dicho endpoint lista todos los partidos, filtrando los partidos completados o no segun el valor del queryparam (Si no se notifica se listan todos)

Al arrancar el microservicio se cargan en memoria tres partidos con id: 1, 2 y 3.

## Microservicio "competicion"

El objetivo de este microservicio el de gestionar la clasificacion y demas aspectos de una competicion. Contiene una entidad con la informacion de los equipos y un listener para recoger los eventos del microservicio de partidos.

Una vez recogido el evento de un partido, el microservicio actualiza los datos de los equipos involucrados en BBDD.

Además, el microservicio expone un controlador que tiene un unico endpoint al path `/standings` el cual devuelve los equipos ordenados segun sus resultados en la clasificación.

El microservicio provee un script que introduce en BBDD tres equipos al arrancarlo.

## Instrucciones de arranque

Los microservicios se despliegan como cualquier aplicacion SpringBoot utilizando la goal del plugin de SpringBoot de Maven `spring-boot:run`. El microservicio de partidos se levanta en el puerto 8080 y el de competicion en el 8081.

Para el correcto funcionamiento de la aplicación, es necesario tener levantada una instancia de RabbitMq en el puerto 5672. Si se dispone de Docker lo más sencillo es levantarla creando un contenedor con su imagen con el siguiente comando:

`docker run -d -p 5672:5672 -p 15672:15672 --name rabbit rabbitmq:3-management`

## Consideraciones no realizadas por falta de tiempo

Seria conveniente realizar test unitarios al controller y el servicio de equipos del microservicio de competicion porque tienen "logica" cuyo comportamiento seria verificado en un test unitario con mocks.

Además, sería conveniente realizar test de integracion a ambos microservicios.




