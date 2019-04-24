# bcs-test-assignment

## Running instructions

This is a [spring boot](https://spring.io/projects/spring-boot) project.

You can run it with the following command:

```
./mvnw spring-boot:run
```

You can try the application using httpie to POST data. For instance:

```
http -v --json POST 'http://localhost:8080' 'stocks:=[{"symbol":"AAPL","volume":10},{"symbol":"YNDX","volume":100},{"symbol":"HOG","volume":90}]'
```


