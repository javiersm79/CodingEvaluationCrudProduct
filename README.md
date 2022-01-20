# CodingEvaluationCrudProduct
## For Falabella

This aplication is a crud for products managment

In this project yoiu can find a file named **Falabella Coding Test.postman_collection** that is compatible with **Postman APP** for testing.

The architecture consist in 3 layer
- Client layer: Here we can find controllers and DTO for mainly expose our endponits and all acording to show client data
- Business layer: In this layer are services and utils to manage business logic and the inermediate between client and data

- Data layer: Entity and repository are stored in this layer to access to persisting data. This app have an H2 in memory DATABASE

For build this app you can clone this repo and excute this command (maven must be intalled)
```sh
mvn package
```

This generate a .jar file (in target folder named codingevaluation-0.0.1-SNAPSHOT) that can be exceute this way:
```sh
java -jar codingevaluation-0.0.1-SNAPSHOT.jar
```
    