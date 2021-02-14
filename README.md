## REST webservice with Gradle
A simple example

### Build
```bash
$ ./gradlew build
```

### Run
```bash
$ java -jar build/libs/job-example-1.0-SNAPSHOT-all.jar

```

### Market Place
* Place Bid
    - Method: `POST`
    - URL: `http://localhost:8081/api/v1/marketplace/bid`
    - ```curl -X POST -H "Content-Type: application/json" http://localhost:8081/api/v1/marketplace/bid -d '{"user": {"userId":1,"userEmail":"a1"}, "job":{"jobId":1, "jobDescription": "blah", "expirationDate":"2021-02-14T23:28:56.782-08:00", "user": {"userId":1,"userEmail":"a1"}}, "amount":10}'```
    - ```{"success":true,"bid":{"job":{"jobId":1,"jobDescription":"blah","user":{"userId":1,"userEmail":"a1"},"expirationDate":1613374136782},"user":{"userId":1,"userEmail":"a1"},"amount":10},"bidCount":1,"timeRemaining":34044}```

* Get Active
    - Method: `GET`
    - URL: `http://localhost:8081/api/v1/marketplace/active`
    - Response body data type: Json
    - ```[{"success":true,"bid":{"job":{"jobId":1,"jobDescription":"blah","user":{"userId":1,"userEmail":"a1"},"expirationDate":1613374136782},"user":{"userId":1,"userEmail":"a1"},"amount":10},"bidCount":1,"timeRemaining":33745}]```

### User APIs
* Get all Users
    - Method: `GET`
    - URL: `localhost:8080/api/v1/users/1`
    - Response body data type: Json
    - ```{"userId":1,"userEmail":"a"}```
* Add new User
    - Method: `POST`
    - ```curl -X POST  -H "Content-Type: application/json" http://localhost:8080/api/v1/users -d '{"userEmail":"a3"}'```
    - Success code: 201 Created
* Get a User
    - Method: `GET`
    - URL: `localhost:8080/api/v1/users/{userId}`
    - Response body data type: Json
    - ```[{"userId":1,"userEmail":"a"},{"userId":2,"userEmail":"b"}]```
* Update a User
    - Method: `PUT`
    - ```curl -X PUT  -H "Content-Type: application/json" http://localhost:8080/api/v1/users/1 -d '{"userEmail":"a3"}'```
    - Response body data type: Json
    - "userId":1,"userName":"a"}
* Delete a User
    - Method: `DELETE`
    - ```curl -X DELETE  -H "Content-Type: application/json" http://localhost:8080/api/v1/users/1```
    - Success code: 204 No Content
    
### Job APIs
* Get all Jobs
    - Method: `GET`
    - URL: `localhost:8080/api/v1/jobs`
    - Response body data type: Json
    - ```{"jobList":[{"jobId":1,"jobDescription":"blah","user":{"userId":1,"userEmail":"a1"},"expirationDate":1388647736782}]}```
* Add new Job
    - Method: `POST`
    - ```curl -X POST -H "Content-Type: application/json" http://localhost:8081/api/v1/jobs -d '{"jobDescription": "blah", "expirationDate":"2014-01-01T23:28:56.782-08:00", "user": {"userId":1,"userEmail":"a1"}}''```
    - Success code: 201 Created
* Get a User
    - Method: `GET`
    - URL: `localhost:8080/api/v1/jobs/1`
    - Response body data type: Json
    - ```{"jobId":1,"jobDescription":"blah","user":{"userId":1,"userEmail":"a1"},"expirationDate":1388647736782}```
   