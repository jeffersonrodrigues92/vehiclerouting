## Problema de Roteamento de Veículos

Problema de roteamento de veículos, tendo como solução a menor rota entre os restaurantes e clientes.

Segue as informações especificado no problema:

- You must consider that:
- You have limitless drivers. -> but it is better to minimize the number of drivers.
- A worker can do at most 3 deliveries of the same restaurant at the same time (i.e. in the same route)
- A worker can only do deliveries of a single restaurant at a time (i.e. in the same route)
- You should try to avoid delivering an order after the delivery time
- You CAN'T pick up an order before the pickup time
- The driver is on the restaurant at the pickup time
- Consider that the driver goes 0.1 units in line each 5 minute.
- Each order must be optimized only once
- You should elaborate the algorithm to solve the Vehicle Routing Problem (do not use libraries for that)
- Hints: your system is growing and the constraints can change, furthermore, new constraints can be added. Prepare your system to be easy to change/configure and easy to add new constraints.
 
## Non-functional requerimentseu 
 Now consider that your micro-services are a success inside IFood, it must be prepared to be fault tolerant, responsive and resilient.
 Use whatever tools and frameworks you feel comfortable with, and briefly elaborate on your solution, architecture details, choice of patterns and frameworks. The only restriction is to use Java. 
 Also, make it easy to deploy/run your service(s) locally (consider using some container/vm solution for this). We expect that you solve this test in about 8 hours. Once done, share your code with us.

## TECNOLOGIAS E PADRÃO DE PROJETO

Sistema foi desenvolvido com requisitos funcionais desacoplados pensando em novas features e manutenção de código.

Segue algumas especificações:

- Java 8 :  https://www.java.com/pt_BR/download/faq/java8.xml
- Spring Boot 2 :  https://spring.io/projects/spring-boot
- Spring Data :  https://spring.io/projects/spring-data
- Spring MVC : https://spring.io/guides/gs/serving-web-content/
- MySQL : https://www.mysql.com/
- Padrão de Projeto Chain of Responsibility : https://pt.wikipedia.org/wiki/Chain_of_Responsibility

## SOLUÇÃO TEÓRICA

Segue o modelo abaixo para entrada de dados como latitude e longitude para clientes e restaurantes:

```json
{
    "lat": 2.1,
    "lon": 1.5
}
```

A solução foi desenvolvida sobre a Fórmula Euclidiana ou conhecida também como Distância Euclidiana, que é responsável por calcular a menor distância entre dois pontos.

Distância Euclidiana: https://pt.wikipedia.org/wiki/Dist%C3%A2ncia_euclidiana

Mas e aí, qual foi a linha de raciocínio?  

- 1- Primeiramente o sistema valida e busca todos os pedidos disponíveis para entrega, tendo o horário atual maior que o horário Pickup (horário que o pedido está pronto).

- 2- Foi desenvolvido um algoritmo que calcula a distância entre todos os restaurantes e clientes separadamente, tendo como regras 5 minutos para cada 0.1 unidades percorrido.

- 3- Após o cálculo de todos os pedidos entre a distância do cliente e restaurante, o sistema atribui todos os pedidos de cada restaurante para sua própria lista, assim é possível identificar quantos pedidos cada restaurante tem para ser entregues.

- 4- Respeitando o horário de entrega dos pedidos e tendo a lista de pedidos por restaurante, o sistema ordena essa lista pela MENOR DISTÂNCIA do restaurante até o cliente. 

- 5- Depois de ordenar a lista e saber quais são os clientes que estão mais pertos do restaurante para entrega, o sistema atribui para cada entregador 3 pedidos do mesmo restaurante, quando há mais de 3 pedidos eu solicito um novo entregador para entregar o restante.

Como saber se a distância entre o cliente e restaurante está certo?

Para ter certeza se a distância está correta, você deve criar 1 cliente e 1 restaurante com a mesma latitude e longitude.
Em seguida você deve cadastrar um pedido associando os 2 com seus respectivos Ids que foram gerados. 
Consultando a rota '/routes/stats', você conseguirá ver que a distância do restaurante para o cliente tem o valor 0 e timeAverageDelivery (tempo calculado por 0.1 units = 5 minutos até o cliente) é o mesmo do Pickup (momento que o pedido está pronto), neste caso é como se o cliente estivesse no estabelecimento para pegar o pedido e não houvesse distância entre os 2 pontos.

## COMO EXECUTAR? 

- Necessário Banco de Dados MySql rodando junto a aplicação, disponibilizei um script chamando 'ifood.sql' na raiz do proejeto para criação da database e tables.

- Para alterar senha do Bando de Dados na aplicação da sua preferência, favor acessar o arquivo 'application.properties'


É possível executar o projeto de 2 modos:

- 1 - Rodando a classe VehicleroutingApplication como Java Application que inicializará todo o contexto do Spring e consequentemente a aplicação.

- 2 - Executando o comando 'mvn spring-boot:run' na raiz do projeto via terminal, que inicializará o tomcat embedded que irá iniciar contexto do Spring e a aplicação.

Após essa etapa o sistema estará disponível na seguinda URL:

- http://localhost:8080

## SERVIÇOS

- Criar Cliente

Request:

POST /client/create

Response: 201

```json
{
    "lat": 2.1,
    "lon": 1.5
}
```
Response Status:
  201 CREATED
  
  
- Atualizar Dados Cliente

Request: 

PUT /client/update

Response: 204

```json
{
    "id" : 1,
    "lat": 0.1,
    "lon": 1.1
}
```
Response Status:
  204 NO CONTENT
 
  
- Consultar Cliente por Id.

Request:

GET /client/{id}

Response: 200 (application/json)

```json
{
    "id": 1,
    "lat": 0.1,
    "lon": 1.1
}
```
Response Status:
  200 OK
  
  
- Criar Restaurante

Request:

POST /restaurant/create

Response: 201

```json
{
    "lat": 2.1,
    "lon": 1.5
}
```
Response Status:
  201 CREATED
  
  
- Atualizar Dados Restaurante

Request: 

PUT /restaurant/update

Response: 204

```json
{
    "id" : 1,
    "lat": 0.1,
    "lon": 1.1
}
```
Response Status:
  204 OK
  
  
- Consultar Restaurante por Id.

Request:

GET /restaurant/{id}

Response: 200 (application/json)

```json
{
    "id": 1,
    "lat": 0.1,
    "lon": 1.2
}
```
Response Status:
  200 OK


- Criar Pedido

Request:

POST /order/create

Response: 201

```json
{  
   "restaurant": {
   	"id": 1
   	},
   "client":{
   	"id" : 1
	},
   "pickup" : "2018-12-06T00:29:00Z",
   "delivery" : "2018-12-06T01:37:00Z"
}
```
Response Status:
  201 CREATED


- Consultar Order por Id.

Request:

GET /order/{id}

Response: 200 (application/json)

```json
{
    "id": 1,
    "restaurant": {
        "id": 3,
        "lat": 2,
        "lon": 2
    },
    "client": {
        "id": 4,
        "lat": 1,
        "lon": 1
    },
    "pickup": "2018-12-06T00:29:00.000+0000",
    "delivery": "2018-12-06T01:37:00.000+0000"
}
```
Response Status:
  200 OK


- Consultar Order por Delivery Time.

Request:

GET /order?startDelivery=2018-12-05 00:00:00&endDelivery=2018-12-05 23:59:59

- @RequestParam startDelivery : required
- @RequestParam endDelivery : required

Response: 200 (application/json)

```json
[
    {
        "id": 1,
        "restaurant": {
            "id": 3,
            "lat": 2,
            "lon": 2
        },
        "client": {
            "id": 4,
            "lat": 1,
            "lon": 1
        },
        "pickup": "2018-12-06T00:29:00.000+0000",
        "delivery": "2018-12-06T01:37:00.000+0000"
    }
]
```
Response Status:
  200 OK

- Consultar Rotas de Entrega

Request:

GET /routes

Response: 200 (application/json)

```json
{
    "routes": [
        {
            "orders": [
                6,
                8
            ]
        },
        {
            "orders": [
                7,
                9
            ]
        },
        {
            "orders": [
                2,
                1,
                5
            ]
        }
    ]
}
```

Response Status:
  200 OK


- Consultar Stats das Rotas dos Pedidos ordenado por restaurante e distância dos clientes

Request:

GET /routes/stats

Response: 200 (application/json)

```json
[
    {
        "id": 1,
        "restaurantId": 1,
        "distance": 0.09999999999999987,
        "timeAverageDelivery": "2018-12-05T22:33:00",
        "distanceTime": 4.999999999999993,
        "delivery": "2018-12-06T01:37:00.000+0000"
    },
    {
        "id": 2,
        "restaurantId": 1,
        "distance": 1.91049731745428,
        "timeAverageDelivery": "2018-12-06T00:04:00",
        "distanceTime": 95.524865872714,
        "delivery": "2018-12-06T01:37:00.000+0000"
    },
    {
        "id": 3,
        "restaurantId": 1,
        "distance": 1.91049731745428,
        "timeAverageDelivery": "2018-12-06T00:04:00",
        "distanceTime": 95.524865872714,
        "delivery": "2018-12-06T01:37:00.000+0000"
    },
    {
        "id": 4,
        "restaurantId": 2,
        "distance": 1.9026297590440449,
        "timeAverageDelivery": "2018-12-06T00:04:00",
        "distanceTime": 95.13148795220224,
        "delivery": "2018-12-06T01:37:00.000+0000"
    },
    {
        "id": 5,
        "restaurantId": 2,
        "distance": 0,
        "timeAverageDelivery": "2018-12-05T22:29:00",
        "distanceTime": 0,
        "delivery": "2018-12-06T01:37:00.000+0000"
    }
]
```
Response Status:
  200 OK


## Desenvolvedor
Jefferson Rodrigues

## LinkedIn
https://www.linkedin.com/in/jefferson-nascimento-rodrigues/
