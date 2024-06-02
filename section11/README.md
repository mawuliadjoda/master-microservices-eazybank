

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


# Start redis server
lefort@ADJODA:~$ docker run -p 6379:6379 --name eazyredis -d redis

# How to Use Apache bench for Load Testing
https://ubiq.co/tech-blog/how-to-use-apache-bench-for-load-testing/

ab -n 10 -c 2 -v 3 http://localhost:8072/eazybank/cards/api/contact-info

# Observability vs. Monitoring

    * In the real project, it's not the developer responsibility to implement observability and monitoring.
    The developer has to work with the operations team or platform team and implement the same.
    But as developer we should be able to give some directions to our operations teams or platform team.
    Or we can also build some demo applications with the help of all the concepts that we are going to discuss.
    And with taht, we'll become a super, super hero or a super developer inside our project.

    When we climb ourself as microservices developer, we need to aware about all these concepts, otherwise it's going to
    be super, super tough to clear the any interviews around microservices.


    Monitoring is about collecting data, and observability is about understanding data
    
    Monitoring is reactive and observability is proactive
    
    Monitoring is reacting to the problems while observability is fixing them in real time
    Like any runtime exceptions or performance issues, we have to deep dive into the information to understand the internal
    state and fix them in real time

    Observability will help us to understand the internal state of a system, whereas monitoring will help us to identify 
    and troubleshoot problems with the help of alerts, dashboards notifications.

#
https://grafana.com/
https://grafana.com/docs/loki/latest/get-started/quick-start/?pg=oss-loki&plcmt=quick-links

# 
    We are going to implement how the tools like Promptail, Grafana, Loki are going to interact with each other.

# Loki 
    Loki is a database for logs optimized to reduce storage costs, sift through a lot of logs very quicky and easily 
    handle whatever weird format the logs come in.
    
    Loki is fundamentally a microservices-based architecture. Essentially, Loki is built from a series of components
    that all work together.

# Promptail
    Promtail is log agent, it collect the log, send it to Gateway (an internal component), and the gateway forward the 
    same to Loki Write component (an internal component of Loki). From the Loki write component, the logs is written into
    a storage system called Minio.

# Grafana
    When a developer want to see the log, we'll go to Grafana and try to search the logs. The request will go to the 
    Gateway will forward the same request to the Loki read component. Loki is going to read the log from the Minio enventually

# Important
    The 15 factor methodology is recommending to tread the logs as events stream to the standard output and not concerned 
    with how they are processed or stored


# One of the pillar of observability and monitoring is logging
# The second pillar of observability and monitoring is metrics
# To properly understand our microservices, 
    we need to understand metrics details like what is the CPU usage, what is the memory usage, what is the heap dump usage
    what are the threads, connections, errors ...

# With the help of components like Spring Boot Actuator, Micrometer, Prometheus and Grafana
# The very first component that is responsible to generate the metrics inside a microservice instance is Spring Boot Actuator

# Prometheus
    Prometheus is a component which is responsible to extract and aggregate all the metrics from microservices instances
    running inside our microservices network.
    
    Prometheus cannot understand metrics provided by actuator, because actuator exposes the metrics information in a Json 
    format which we can easily understand as humans.
    Since the same format cannot be understand by Prometheus, we need to use micrometer

    ==> Prometheus is going to collect the metrics from the individual microservice and it is going to store all of them
        in a single location, which we can monitor easily with the help of UI provided by the Prometheus

    ==> After the Prometheus, we should also integrate Prometheus with the Grafana.

    ==> Question: What is the need of Grafana when we already have Prometheus which will help us to monitor our microservices ? 
        Answer: We agree but Prometheus has certain limitations. We cannot build very complex dashboards and we cannot create
                alerts and notifications with the Prometheus alone.
                => When we integrate Prometheus with Grafana, we can build a lot of dashboards and apart from dashboards 
                    we can also create alerts and notifications.
                => That's why we need to make sure we are integrating Prometheus with the Grafana.

# Actuator
    Actuator is mainly used to expose operational information about the running application: health, metrics, info, dump, env ...
    It uses HTTP endpoints or JMX beans to enable us to interact with it.

# Micrometer
    Micrometer automatically exposes /actuator/metrics data into something our monitoring system can understand. 
    All we need to do is include that vendor-specific micrometer dependency in our application. Think like SLF4J but 
    for metrics

# Grafana
    Grafana iss visualization tool that can be used to create dashboards and charts from Prometheus data.


#  account metrics
http://localhost:8080/actuator/metrics
http://localhost:8080/actuator/prometheus

# loans
http://localhost:8090/actuator/prometheus

# cards
http://localhost:9000/actuator/prometheus

# eureka
http://localhost:8070/actuator/prometheus

# confiserver
http://localhost:8071/actuator/prometheus

# gatewayserver
http://localhost:8072/actuator/prometheus


# Prometheus Dashboard
http://localhost:9090/targets