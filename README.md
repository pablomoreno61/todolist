# todolist

## REST endpoints 

Create todo list

`mvn clean spring-boot:run`

`curl -X POST http://localhost:8080/todolist/add -d '{"name":"Tech debt"}' -H "Content-Type: application/json"` 

## CLI endpoints

`mvn spring-boot:run -Dspring-boot.run.arguments="--action=create --params=name=Tech debt"`