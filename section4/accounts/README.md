CTRL + F12 : show all method in class




1. Generate Docker image


    lefort@ADJODA:/mnt/d/dev/java/microservices/master-microservices-udemy/section4/accounts$ docker build . -t adjodamawuli/accounts:s4


    lefort@ADJODA:/mnt/d/dev/java/microservices/master-microservices-udemy/section4/accounts$ docker images

    REPOSITORY                    TAG       IMAGE ID       CREATED              SIZE
    adjodamawuli/accounts         s4        a4983f92aeea   About a minute ago   464MB
    ekwateur-billing-app-app      latest    32f4b0747630   6 days ago           551MB



    lefort@ADJODA:/mnt/d/dev/java/microservices/master-microservices-udemy/section4/accounts$ docker inspect image a498

    [
    {
    "Id": "sha256:a4983f92aeea975da1526002c1069428c5804335b9c135610fa45c18cd8dc83d",
    "RepoTags": [
    "adjodamawuli/accounts:s4"
    ],
    
    ...


2. Running accounts microservice as Docker container


a) interactive mode

    lefort@ADJODA:/mnt/d/dev/java/microservices/master-microservices-udemy/section4/accounts$ docker run -p 8080:8080 adjodamawuli/accounts:s4

b) detached mode

    lefort@ADJODA:/mnt/d/dev/java/microservices/master-microservices-udemy/section4/accounts$ docker run -d -p 8080:8080 adjodamawuli/accounts:s4

    65b71169f8258732c0366069216fce03532b28a3a093ea6f4004005fb42b679c



3. show all container

        lefort@ADJODA:/mnt/d/dev/java/microservices/master-microservices-udemy/section4/accounts$ docker ps

        CONTAINER ID   IMAGE                                 COMMAND                  CREATED              STATUS              PORTS
        NAMES
        65b71169f825   adjodamawuli/accounts:s4              "java -jar accounts-…"   About a minute ago   Up About a minute   0.0.0.0:8080->8080/tcp, :::8080->8080/tcp                                                                                              nice_margulis
        eb460ebcbfd4   gcr.io/k8s-minikube/kicbase:v0.0.42   "/usr/local/bin/entr…"   6 days ago           Up 9 hours          127.0.0.1:32772->22/tcp, 127.0.0.1:32771->2376/tcp, 127.0.0.1:32770->5000/tcp, 127.0.0.1:32769->8443/tcp, 127.0.0.1:32768->32443/tcp   minikube



4. show all the stopped container

        lefort@ADJODA:/mnt/d/dev/java/microservices/master-microservices-udemy/section4/accounts$ docker ps -a


    CONTAINER ID   IMAGE                                 COMMAND                  CREATED         STATUS                       PORTS
    NAMES
    65b71169f825   adjodamawuli/accounts:s4              "java -jar accounts-…"   3 minutes ago   Up 3 minutes                 0.0.0.0:8080->8080/tcp, :::8080->8080/tcp                                                                                              nice_margulis
    af1f8bd3906a   adjodamawuli/accounts:s4              "java -jar accounts-…"   4 minutes ago   Exited (130) 4 minutes ago
    festive_brahmagupta
    53f1cb87f1bf   ekwateur-billing-app-app              "java -jar ekwateur-…"   6 days ago      Exited (143) 26 hours ago
    ekwateur-billing-app-app-1
    170e09130e5d   postgres:latest                       "docker-entrypoint.s…"   6 days ago      Exited (0) 26 hours ago
    ekwateur-billing-app-postgres-1
    eb460ebcbfd4   gcr.io/k8s-minikube/kicbase:v0.0.42   "/usr/local/bin/entr…"   6 days ago      Up 9 hours                   127.0.0.1:32772->22/tcp, 127.0.0.1:32771->2376/tcp, 127.0.0.1:32770->5000/tcp, 127.0.0.1:32769->8443/tcp, 127.0.0.1:32768->32443/tcp   minikube
    49bbc71aa07a   hello-world                           "/hello"                 6 days ago      Exited (0) 6 days ago
    cranky_hermann


5. To stop container


docker stop CONTAINER_ID

    lefort@ADJODA:/mnt/d/dev/java/microservices/master-microservices-udemy/section4/accounts$ docker stop 65b71169f825



6. run more than one container

docker run -d -p LOCAL_PORT:CONTAINER_PORT adjodamawuli/accounts:s4

= > the local port must be different but container port is in an isolate system so we can keep it as the same


    lefort@ADJODA:/mnt/d/dev/java/microservices/master-microservices-udemy/section4/accounts$ docker run -d -p 8080:8080 adjodamawuli/accounts:s4
    ff3e7fe21ed8646cd09ab0db8f10d195aacdecfef920bee0fd92f8624d8b650a


    lefort@ADJODA:/mnt/d/dev/java/microservices/master-microservices-udemy/section4/accounts$ docker run -d -p 8081:8080 adjodamawuli/accounts:s4
    5bb21448e9c62b6aa525dc8f5b9f4bd3c93415db0ddf431675e547d7248233a2

