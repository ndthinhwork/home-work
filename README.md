# Data Pipeline Backend Service

This repository contains a backend service implemented in Java Spring Boot that reads user segmentation data from a CSV
file and pushes it to a Kafka message queue.

## Prerequisites

- **Java 17** (or later)
- **Apache Kafka** (version 3.6.2 or compatible)
- **Docker** (for Kafka and Zookeeper)
- **Gradle** (for building the project)

## Setup and Configuration

### **1. Running Kafka and Zookeeper**

To run Kafka and Zookeeper using Docker:

```shell
docker-compose up
```

### **2. Build the Project**

```shell
./gradlew build
```

### **3. Run the Application**

```shell
./gradlew bootRun
```

The application will be available at http://localhost:8080.(config
in [application.yml](src%2Fmain%2Fresources%2Fapplication.yml))

### **4. Using the API**

To upload a CSV file and process it, send a POST request to:

```shell
POST http://localhost:8080/api/v1/files/process
```

with the form-data containing the file. You can use tools like `Postman` to perform this operation.

### **5. Configuration**

Application configuration is located in [application.yml](src%2Fmain%2Fresources%2Fapplication.yml). Key settings include:

Kafka Bootstrap Servers: `localhost:9092`

Default Kafka Topic: `datapipeline`