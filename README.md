# EjercicioItems
Este proyecto consiste en una aplicacion que se puede ejecutar utilizando vista html o bien directamente desde un cliente http para ejecutar los distintos métodos.

La base de datos no relacional escogida para este ejercicio fue ElasticSearch.

Para ejecutar el proyecto se puede hacer desde la carpeta src/main/java/controller:
  1. clase ItemController para levantar el servicio en el puerto 9393 y consumir las apis por medio de postman o curl con URL:http://localhost:9393
  2. clase ItemControllerVistas para levatar el servicio en el puerto 9494 y acceder a la vista web con URL:http://localhost:9494/list

A continuacion se detallan las operaciones posibles a realizar consumiendo el servicio por cliente postman o curl


<pre>
REQUEST:
    Metodo http: GET
            URL: http://localhost:9393/items/all
        Headers: Content-Type:application/json
RESPONSE:
    [
      {
          "id": "ML0001",
          "available_quantity": "2",
          "currency_id": "ARS",
          "title": "Item prueba.",
          "price": "80",
          "condition": "new",
          "description": "Producto desc",
          "listing_type_id": "bronze",
          "pictures": [
              {
                  "source": "img_url"
              }
          ],
          "buying_mode": "buy_it_now",
          "category_id": "MLA5529",
          "video_id": "youtube_ID",
          "warranty": "desc"
      }
    ]
</pre>
