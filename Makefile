# Makefile for OpenAPI Generator

OPENAPI_CLI_JAR := generator/openapi-generator-cli.jar
SWAGGER_JSON := generator/swagger.json
OUTPUT_DIR := swagger-generated/
CONFIG_FILE := generator/config.json
GENERATOR := spring
TEMPLATE_DIR := generator/template/
GENERIC_ERROR_PATH := generator/GenericErrorResponse.java
GENERIC_ERROR_TARGET_PATH := swagger-generated/src/main/java/org/openapitools/model

generate:
	java -jar $(OPENAPI_CLI_JAR) generate \
		-i $(SWAGGER_JSON) \
		-g $(GENERATOR) \
		-o $(OUTPUT_DIR) \
		-c $(CONFIG_FILE) \
		-t $(TEMPLATE_DIR)