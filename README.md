# Market Trade Processor in backend
This backend processor provides services for posting transaction, getting all transaction and the latest one.
There are four endpoints in the whole services as below.

## Endpoint

### Post Currency Exchange Transaction
```
/v1/demo/postCurExTx
````
### Get all Transaction
```
/v1/demo/getTx
```
### Get the latest Transaction
```
/v1/demo/getLatestTx
```
### Get summary for the transaction
```
/v1/demo/getSummary

```
### Postman Collection is at the project
```
/postman/currencyFairdemo.postman_collection.json
```

## Build Project
<code>gradlew build</code>

## Path of built jar file
```
/build/lib/currencyfairdemo-0.0.1-SNAPSHOT.jar
```
## Run Project
<code>java -jar "currencyfairdemo-0.0.1-SNAPSHOT"</code>

## Domain
```
http://localhost:7000
```

