

http://localhost:8072/actuator
http://localhost:8072/actuator/gateway/routes


http://localhost:8072/accounts/api/create
http://localhost:8072/accounts/api/fetch?mobileNumber=0786015566

# custom routing
http://localhost:8072/eazybank/accounts/api/create

#
mvn compile jib:dockerBuild
docker login
docker image push docker.io/adjodamawuli/accounts:s9
docker image push docker.io/adjodamawuli/loans:s9
docker image push docker.io/adjodamawuli/cards:s9
docker image push docker.io/adjodamawuli/configserver:s9
docker image push docker.io/adjodamawuli/gatewayserver:s9
docker image push docker.io/adjodamawuli/eurekaserver:s9



docker compose up
docker compose down