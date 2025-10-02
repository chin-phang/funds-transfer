# Getting Started

### Start PostgreSQL with Docker
```
# src/main/docker/
docker-compose -f .\docker-compose.yml up -d
```

### Build and Run
```
# project root
mvn clean package -DskipTests

# target
java -jar funds-transfer-0.0.1-SNAPSHOT.jar
```

### Test Users
```
admin / admin
customer1 / test123
customer2 / test123
```