# Avro + Schema Registry

Avro is a schema-based binary serialization system designed for distributed systems.  
When used with Kafka and Schema Registry, it enables safe data contracts, schema evolution, and independent service deployments.

## Installation

If using Spring Boot with Kafka and Avro, add the following dependencies.

### Gradle

```bash
implementation 'org.springframework.kafka:spring-kafka'
implementation 'io.confluent:kafka-avro-serializer:7.5.0'
implementation 'org.apache.avro:avro:1.11.3'
```

Make sure Schema Registry is running (typically on port 8081).

## Usage

### 1. Define a Schema (`log-event.avsc`)

```json
{
  "type": "record",
  "name": "LogEvent",
  "namespace": "com.example.schema",
  "fields": [
    { "name": "level", "type": "string" },
    { "name": "message", "type": "string" },
    { "name": "service", "type": "string" },
    { "name": "timestamp", "type": "long" }
  ]
}
```

### 2. Configure Kafka Consumer

```yaml
spring:
  kafka:
    bootstrap-servers: localhost:29092
    consumer:
      group-id: ingestion-group
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: io.confluent.kafka.serializers.KafkaAvroDeserializer
      properties:
        schema.registry.url: http://localhost:8081
        specific.avro.reader: true
```

### 3. Consume with SpecificRecord

```java
@KafkaListener(topics = "logs-topic")
public void consume(LogEvent event) {
    System.out.println(event.getLevel());
}
```

### 4. Consume with GenericRecord (Alternative)

```java
@KafkaListener(topics = "logs-topic")
public void consume(GenericRecord record) {
    String level = record.get("level").toString();
}
```

## Schema Evolution

Avro supports safe schema evolution through compatibility modes.

Safe changes:

- Add optional field with default value
- Add nullable field

Unsafe changes:

- Remove required field
- Change incompatible field type

Schema Registry enforces compatibility modes:

- BACKWARD
- FORWARD
- FULL
- NONE

Most production systems use BACKWARD or FULL.

## Shared DTO vs Avro

### Shared DTO

- Simple for small systems
- Requires coordinated deployments
- Tight compile-time coupling

### Avro + Schema Registry

- Runtime contract enforcement
- Independent deployments
- Safe schema evolution
- Suitable for large distributed systems

## Consumer Upgrades

Consumers upgrade by:

1. Updating the schema
2. Regenerating classes (SpecificRecord)
3. Redeploying

Consumers do not manually choose schema versions.  
Each Kafka message contains its schema ID, and Avro resolves differences automatically.
