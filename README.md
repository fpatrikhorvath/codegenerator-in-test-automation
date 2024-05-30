# Spring.boot framework & Openapi generator

The Openapi/Swagger code generator can help You generate the DTOs and the request templates.

## Installation

1. Clone the repository
2. (Generate the swagger codes, or just use the current one)
3. Install the "Cucumber for Java" plugin
4. Run the server/server.js
5. Run the feature by pressing the run button (cli run later on)

## Commands

To generate swagger codes from swagger.json:

```
$ make Makefile generate
```

To run the mocked server:

```
$ node server/server.js
```

## TODOS

- CLI run <- currently a dependency issue blocking this

## License
MIT