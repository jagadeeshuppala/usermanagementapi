# Users API

Offer related API's exposed

## Getting Started
This is a maven project, so you can start running the project using
```
mvn clean install spring-boot:run
```

### swagger ui
```
http://localhost:8080/swagger-ui.html
```

### Application home page
```
http://localhost:8080
```


## Create new User from rest client
```
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \
   "firstName": "Jagadeesh" \
   "lastName": "Uppala", \
   "email": "uppala.jagadeesh@gmail.com", \
 }' 'http://localhost:8080/users'
 ```



### Get All the users from rest client
```
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/users'
```
