# Task 2 - CSV Parser
## Solution Description
We parse the csv file with OpenCSV library then with the help of spring framework and JPA repositories we insert the parsed row into the Postgres database that can be deployed from the docker-compose file.

## Usage
### Run following commands
```shell
docker-compose up -d

./gradlew bootRun --args="min"
```

