##Run application
 
Prerequisites:
- Java 11
- Maven


```shell
## this will run also Mysql Container
./mvnw compile quarkus:dev
```

##Endpoints


- Load products and inventory
````shell
curl -X POST  --header "Content-Type: application/json" -d'{"type":"ARTICLES"}' localhost:8080/warehouse/load
curl -X POST  --header "Content-Type: application/json" -d'{"type":"PRODUCTS"}' localhost:8080/warehouse/load
 ````

- Get all Products
```shell
curl localhost:8080/warehouse/products
```

- Buy a Product
````shell
curl -X POST localhost:8080/warehouse/products/sell/1
````

##To consider
- There are no IT test.
- Hibernate was not the best option.
- No Error management
- No log
- No Reactive
- I didn't check it in native built or container build mode.
