
# check if configservicer load eurekaproperty add to  https://github.com/mawuliadjoda/eazybank-config.git

http://localhost:8071/eurekaserver/default

access eurekaserver
http://localhost:8070/
http://localhost:8070/eureka/apps 

# Generate Docker images

    mvn compile jib:dockerBuild

# Push images to Docker Hub

    docker login
    docker image push docker.io/adjodamawuli/eurekaserver:s8
    docker image push docker.io/adjodamawuli/accounts:s8
    docker image push docker.io/adjodamawuli/loans:s8
    docker image push docker.io/adjodamawuli/cards:s8
    docker image push docker.io/adjodamawuli/configserver:s8