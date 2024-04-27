

# To start kafka locally choose option 1 or 2 
# I choose option 2 with docker 

1. Kafka with KRaft

Kafka can be run using KRaft mode using local scripts and downloaded files or the docker image. Follow one of the sections below but not both to start the kafka server.

Using downloaded files
Generate a Cluster UUID

    $ KAFKA_CLUSTER_ID="$(bin/kafka-storage.sh random-uuid)"
    Format Log Directories
    
    $ bin/kafka-storage.sh format -t $KAFKA_CLUSTER_ID -c config/kraft/server.properties
    Start the Kafka Server
    
    $ bin/kafka-server-start.sh config/kraft/server.properties


2. kafka with docker

       docker pull apache/kafka:3.7.0
       docker run -p 9092:9092 apache/kafka:3.7.0


3. Push images to docker hub

        docker login
        mvn compile jib:dockerBuild
        
        docker image push docker.io/adjodamawuli/accounts:s14
        docker image push docker.io/adjodamawuli/cards:s14
        docker image push docker.io/adjodamawuli/loans:s14
        docker image push docker.io/adjodamawuli/message:s14
        docker image push docker.io/adjodamawuli/eurekaserver:s14
        docker image push docker.io/adjodamawuli/eurekaserver:s14
        docker image push docker.io/adjodamawuli/configserver:s14
        docker image push docker.io/adjodamawuli/gatewayserver:s14


4.  For docker compose of kafka 

    https://github.com/bitnami/containers/blob/main/bitnami/kafka/docker-compose.yml