# Makefile for OpenAPI Generator

# Define variables
OPENAPI_CLI_JAR := openapi-generator-cli.jar
SWAGGER_JSON := swagger.json
OUTPUT_DIR := swagger/
CONFIG_FILE := config.json
GENERATOR := java

# Default target
.PHONY: all
all: generate

# Target to generate client code
.PHONY: generate
generate:
	@echo "Generating client code..."
	@java -jar $(OPENAPI_CLI_JAR) generate \
		-i $(SWAGGER_JSON) \
		-g $(GENERATOR) \
		-o $(OUTPUT_DIR) \
		-c $(CONFIG_FILE) \
		--skip-validate-spec

# Target to clean the output directory
.PHONY: clean
clean:
	@echo "Cleaning generated files..."
	@rm -rf $(OUTPUT_DIR)

# Target to validate the OpenAPI spec
.PHONY: validate
validate:
	@echo "Validating OpenAPI spec..."
	@java -jar $(OPENAPI_CLI_JAR) validate \
		-i $(SWAGGER_JSON)

# Target to show help
.PHONY: help
help:
	@java -jar $(OPENAPI_CLI_JAR) help generate

# Define the configuration file content
config.json:
	@echo '{' > $(CONFIG_FILE)
	@echo '  "groupId": "com.example",' >> $(CONFIG_FILE)
	@echo '  "artifactId": "swagger-client",' >> $(CONFIG_FILE)
	@echo '  "artifactVersion": "1.0.0",' >> $(CONFIG_FILE)
	@echo '  "hideGenerationTimestamp": true,' >> $(CONFIG_FILE)
	@echo '  "library": "resttemplate",' >> $(CONFIG_FILE)
	@echo '  "dateLibrary": "java17",' >> $(CONFIG_FILE)
	@echo '  "interfaceOnly": true,' >> $(CONFIG_FILE)
	@echo '  "requestMappingMode": false,' >> $(CONFIG_FILE)
	@echo '  "annotationLibrary": "swagger2"' >> $(CONFIG_FILE)
	@echo '}' >> $(CONFIG_FILE)

# Ensure the configuration file exists before generating code
generate: config.json
