
# Implementation of JWT

Implementation of JWT with Spring securtiy in a stateless session


## API Reference

#### Get authenticate first

```http
  POST http://localhost:8090/authenticate
```

| key |Value     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `admin` | **Required** API key |
| `password` | `password` | *Body* -> *raw* -> *json*|

#### Get all

```http
  GET http://localhost:8090/all
```

| key | value    | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Authorization`      | `Bearer token` | **token** : paste your jwt token here |




## Authors

- [@deepakmishra](https://github.com/sirdeepakmishra/employeemanager)

  