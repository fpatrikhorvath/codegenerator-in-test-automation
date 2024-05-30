# Makefile for OpenAPI Generator

GENERATOR := spring

GENERATOR_DIR := generator
OUTPUT_DIR := swagger-generated
MODEL_DIR := src/main/java/org/openapitools/model/

SWAGGER_PATH := $(GENERATOR_DIR)/swagger.json
TEMPLATES_PATH := $(GENERATOR_DIR)/template

generate:
	java -jar $(GENERATOR_DIR)/openapi-generator-cli.jar generate \
		-i $(GENERATOR_DIR)/swagger.json \
		-g $(GENERATOR) \
		-o $(OUTPUT_DIR) \
		-c $(GENERATOR_DIR)/config.json \
		-t $(GENERATOR_DIR)/template \

	cp $(GENERATOR_DIR)/static/GenericErrorResponse.java $(OUTPUT_DIR)/$(MODEL_DIR)
