
- ------ if Kubernetes dashboard not started ------------
kubectl -n kubernetes-dashboard port-forward svc/kubernetes-dashboard-kong-proxy 8443:443

Kubernetes dashboard:
https://localhost:8443/



koffi@ADJODA /cygdrive/d/dev/java/microservices/master-microservices-udemy/master-microservices-udemy-jump/section17/kubernetes
$ touch kubernetes-discoveryserver.yml



koffi@ADJODA /cygdrive/d/dev/java/microservices/master-microservices-udemy/master-microservices-udemy-jump/section17/kubernetes
$ kubectl apply -f kubernetes-discoveryserver.yml
service/spring-cloud-kubernetes-discoveryserver created
serviceaccount/spring-cloud-kubernetes-discoveryserver created
rolebinding.rbac.authorization.k8s.io/spring-cloud-kubernetes-discoveryserver:view created
role.rbac.authorization.k8s.io/namespace-reader created
deployment.apps/spring-cloud-kubernetes-discoveryserver-deployment created


--- install keycloak on kubernetes  

PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section17\helm> helm install keycloak keycloak
NAME: keycloak
LAST DEPLOYED: Thu May  9 12:25:13 2024
NAMESPACE: default
STATUS: deployed
REVISION: 1
TEST SUITE: None
NOTES:
CHART NAME: keycloak
CHART VERSION: 21.1.2
APP VERSION: 24.0.3

** Please be patient while the chart is being deployed **

Keycloak can be accessed through the following DNS name from within your cluster:

    keycloak.default.svc.cluster.local (port 80)

To access Keycloak from outside the cluster execute the following commands:

1. Get the Keycloak URL by running these commands:

  NOTE: It may take a few minutes for the LoadBalancer IP to be available.
        You can watch its status by running 'kubectl get --namespace default svc -w keycloak'

    export HTTP_SERVICE_PORT=$(kubectl get --namespace default -o jsonpath="{.spec.ports[?(@.name=='http')].port}" services keycloak)
    export SERVICE_IP=$(kubectl get svc --namespace default keycloak -o jsonpath='{.status.loadBalancer.ingress[0].ip}')

    echo "http://${SERVICE_IP}:${HTTP_SERVICE_PORT}/"

2. Access Keycloak using the obtained URL.
3. Access the Administration Console using the following credentials:

  echo Username: user
  echo Password: $(kubectl get secret --namespace default keycloak -o jsonpath="{.data.admin-password}" | base64 -d)

WARNING: There are "resources" sections in the chart not set. Using "resourcesPreset" is not recommended for production. For production installations, please set the following values according to your workload needs:
  - resources
+info https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/


--  
koffi@ADJODA ~
$ echo Password: $(kubectl get secret --namespace default keycloak -o jsonpath="{.data.admin-password}" | base64 -d)
Password: password

koffi@ADJODA ~
$ export HTTP_SERVICE_PORT=$(kubectl get --namespace default -o jsonpath="{.spec.ports[?(@.name=='http')].port}" services keycloak)
    export SERVICE_IP=$(kubectl get svc --namespace default keycloak -o jsonpath='{.status.loadBalancer.ingress[0].ip}')

koffi@ADJODA ~
$ echo "http://${SERVICE_IP}:${HTTP_SERVICE_PORT}/"
http://:80/
