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
$ java -jar openapi-generator-cli.jar generate -i swagger.json -g spring -o swagger-generated/ -c config.json
```

To run the mocked server:

```
$ node server/server.js
```

## TODOS

- Implement generating process in the Makefile
- CLI run <- currently a dependency issue blocking this
- Implementing 2 suites for each Api group
- Remove the configuration folder from the generated code
- Add a context id to the mustache

## License
MIT