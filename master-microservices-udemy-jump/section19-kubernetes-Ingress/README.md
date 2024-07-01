# Services of type ExternalName
https://kubernetes.io/docs/concepts/services-networking/service/#externalname

Whenever we want to map our service to one of the DNS name or domain name that our organization owns then in such scenarios
we are going to use a service type as external name.
This will give flexibility to our kubernetes admins to maps our services to a specific domain name.

This way, any client application who want to consume our microservices, they can simply forward the request the domain
name that you have given to them and behind the scenes, this external name services 

# Ingress
Using Ingress also, we can expose all our microservices to the outside of the cluster, 

=> but here you may have a question
like how load balancer is different from the ingress because you can clearly see Ingress is not available inside the service

Ingress itself is a separate topic or separate concept inside kubernetes, so there has to be a difference between service type 
load balancer and ingress.

So they are doing the ssame job, which is exposing the microservices to the outside of the kubernetes cluster.

    # Difference between Ingress and LoadBalancer
    So whenever you are using service type as LoadBalancer, you are going to expose a particular microservices 
    to which you are tagging the service type as LoadBalancer to the outside of your Kubernetes cluster.
    
    => So if you use service type as LoadBalancer for five different microservices, then all of them they are going to have 
    five different public IP with their own LoadBalancer provided by your cloud provider. 
    That means your service type, which is LoadBalancer, is going to be specific for each microservices, but sometimes we 
    want to have a single entry point into our Kubernetes cluster, which is going to take care of forwarding all the external 
    requests to one of container or microservice available within the cluster.
    
    We already build such component with the help of Spring Cloud Gateway 
    But whatever we have built with the help of Spring Cloud Gateway, this is more of a developer approach.
    
    But some organization, instead of building their own edge server with the help of Spring Cloud Gateway, they are going to
    rely on the Kubernetes Ingress

# What is Ingress
Ingress exposes HTTP and HTTPS routes from outside the cluster to services within the cluster. Traffic routing is controlled 
rules defined on the Ingress resource. An Ingress may be configured to give Services externally-reacheable URLs, load balance
traffic terminate SSL/TLS, and offer name-based virtual hosting.

In other words, it is going to act as an edge server inside our microservices and it is going to be responsible for the 
traffic routing controlled by the rules defined on the ingress resource.

And if needed, Ingress is also capable of load balancing the traffic, terminating SSL and TLS traffic and offer name based virual hosting.


=> So Ingress has many capabilities using those capabilities, you can also enforce some cross-cutting concerns authentication 
and authorization with the help of Ingress by integrating with Oauth2 or OIDC products like Keycloak or Okta.

# Ingres vs Spring cloud Gateway
So why can't we always go with the ingress component available inside the Kubernetes ?
=> Evry organization will have different, different team structures inside an organization.

There can be some talented developpers who are capable of building an edge server with the help of Spring Cloud Gateway. 
In such scenarios, people will incline to the approach of Spring Cloud Gateway, 

whereas in some organization there can be some super, super talented DevOps team members who knows everything about Kubernetes cluster,
how to set up a Kubernetes ingres, and how to integrate with various products like Keycloak for security and how to enforce some cross-cutting
concerns everything with the help of Kubernetes Ingress.
=> So if you have such talented team who knows everything about Kubernetes Ingress and how to set up that, then in such scenarios, 
obviously, the project leadership, they will incline towards the Kubernetes Ingress approach. 

The two approach like Spring Cloud Gateway and Kubernetes Ingress, they both does the same job.
It's just that at the end of the day, the organization has to take a decision whether you want to put this responsibility of exposing the traffic to the 
outside world at the developer hand or at the DevOps hand