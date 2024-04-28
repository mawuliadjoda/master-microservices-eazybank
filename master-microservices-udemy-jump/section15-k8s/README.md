

#

https://docs.docker.com/desktop/kubernetes/

kubectl config get-contexts
kubectl config get-clusters



PS C:\Users\koffi> kubectl config get-clusters
NAME
docker-desktop
arn:aws:eks:us-east-2:533267226582:cluster/adjodadev-cluster
PS C:\Users\koffi> kubectl config get-contexts
CURRENT   NAME                                                           CLUSTER                                                        AUTHINFO                                                       NAMESPACE
*         arn:aws:eks:us-east-2:533267226582:cluster/adjodadev-cluster   arn:aws:eks:us-east-2:533267226582:cluster/adjodadev-cluster   arn:aws:eks:us-east-2:533267226582:cluster/adjodadev-cluster
          docker-desktop                                                 docker-desktop                                                 docker-desktop
PS C:\Users\koffi> kubectl config use-context docker-desktop
Switched to context "docker-desktop".
PS C:\Users\koffi> kubectl config get-contexts
CURRENT   NAME                                                           CLUSTER                                                        AUTHINFO                                                       NAMESPACE
          arn:aws:eks:us-east-2:533267226582:cluster/adjodadev-cluster   arn:aws:eks:us-east-2:533267226582:cluster/adjodadev-cluster   arn:aws:eks:us-east-2:533267226582:cluster/adjodadev-cluster
*         docker-desktop

# 

PS C:\Users\koffi> kubectl get nodes
NAME             STATUS   ROLES           AGE   VERSION
docker-desktop   Ready    control-plane   21d   v1.29.1

# 
https://kubernetes.io/docs/tasks/access-application-cluster/web-ui-dashboard/

First install helm in our local machine 
https://helm.sh/

if choco is not install = > https://chocolatey.org/install


PS C:\WINDOWS\system32> choco install kubernetes-helm
PS C:\WINDOWS\system32> helm version
version.BuildInfo{Version:"v3.14.3", GitCommit:"f03cc04caaa8f6d7c3e67cf918929150cf6f3f12", GitTreeState:"clean", GoVersion:"go1.21.7"}

#
PS C:\WINDOWS\system32> helm repo add kubernetes-dashboard https://kubernetes.github.io/dashboard/
"kubernetes-dashboard" has been added to your repositories

PS C:\WINDOWS\system32> helm version
version.BuildInfo{Version:"v3.14.3", GitCommit:"f03cc04caaa8f6d7c3e67cf918929150cf6f3f12", GitTreeState:"clean", GoVersion:"go1.21.7"}
PS C:\WINDOWS\system32> helm repo add kubernetes-dashboard https://kubernetes.github.io/dashboard/
"kubernetes-dashboard" has been added to your repositories

#
PS C:\WINDOWS\system32> helm upgrade --install kubernetes-dashboard kubernetes-dashboard/kubernetes-dashboard --create-namespace --namespace kubernetes-dashboard

	Congratulations! You have just installed Kubernetes Dashboard in your cluster.

	To access Dashboard run:
	  kubectl -n kubernetes-dashboard port-forward svc/kubernetes-dashboard-kong-proxy 8443:443

	NOTE: In case port-forward command does not work, make sure that kong service name is correct.
		  Check the services in Kubernetes Dashboard namespace using:
			kubectl -n kubernetes-dashboard get svc

	Dashboard will be available at:
	  https://localhost:8443
	  
#	  
https://github.com/kubernetes/dashboard/blob/master/docs/user/access-control/creating-sample-user.md

touch dashboard-adminuser.yaml
kubectl apply -f dashboard-adminuser.yaml
	serviceaccount/admin-user created

touch dashboard-rolebinding.yaml

kubectl apply -f dashboard-rolebinding.yaml
	clusterrolebinding.rbac.authorization.k8s.io/admin-user created
	
# Getting a Bearer Token for ServiceAccount
kubectl -n kubernetes-dashboard create token admin-user
eyJhbGciOiJSUzI1NiIsImtpZCI6IkxWUHNwYVR5cE8wYTIzWGRRb3A4TTNSeHVDZjRSdlhlR2p4YXdyS0l3TGMifQ.eyJhdWQiOlsiaHR0cHM6Ly9rdWJlcm5ldGVzLmRlZmF1bHQuc3ZjLmNsdXN0ZXIubG9jYWwiXSwiZXhwIjoxNzE0MjU2MDY3LCJpYXQiOjE3MTQyNTI0NjcsImlzcyI6Imh0dHBzOi8va3ViZXJuZXRlcy5kZWZhdWx0LnN2Yy5jbHVzdGVyLmxvY2FsIiwia3ViZXJuZXRlcy5pbyI6eyJuYW1lc3BhY2UiOiJrdWJlcm5ldGVzLWRhc2hib2FyZCIsInNlcnZpY2VhY2NvdW50Ijp7Im5hbWUiOiJhZG1pbi11c2VyIiwidWlkIjoiYjc5YjYzZDItMjk4Yi00MDg0LTlkY2ItNjJmNzkxOTAyNTFkIn19LCJuYmYiOjE3MTQyNTI0NjcsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDprdWJlcm5ldGVzLWRhc2hib2FyZDphZG1pbi11c2VyIn0.SAf3tBkXRP4po6APbCGGGHvEvEHL-1C6cALWYTMr5b7MAlx4xEKpIpJ_UoQ4dMznzmW2WEM3WuQKqgsJOULlEtqP-DVMpXvaAUEiS8HgCIRp-HgeGwaRRFeywZJLqvpr_Pw_pjNn_Vip5t_MkgFb-5x0oYSUW7dY2sZ6T6BdUEoS1sn93_tnlfmCGmqXipsb5V9LfS5A9qfGlkk1SzlUEyMltPTOoBIJmbSY3iGjPgXp1_XYukGZ0IiXXSqX8phjxh4M0Z5yr3aRRx24rCBOmZmK7RTwXEF8G2mvLmZiGrcBNmPmYB0Eyy1uxwpSE6RmWgdAbH5CizPo9bLMVHZH-Q


# Getting a long-lived Bearer Token for ServiceAccount
touch secret.yaml
kubectl apply -f secret.yaml
	secret/admin-user created
	
kubectl get secret admin-user -n kubernetes-dashboard -o jsonpath={".data.token"} | base64 -d

	eyJhbGciOiJSUzI1NiIsImtpZCI6IkxWUHNwYVR5cE8wYTIzWGRRb3A4TTNSeHVDZjRSdlhlR2p4YXdyS0l3TGMifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlcm5ldGVzLWRhc2hib2FyZCIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJhZG1pbi11c2VyIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQubmFtZSI6ImFkbWluLXVzZXIiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiJiNzliNjNkMi0yOThiLTQwODQtOWRjYi02MmY3OTE5MDI1MWQiLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6a3ViZXJuZXRlcy1kYXNoYm9hcmQ6YWRtaW4tdXNlciJ9.UIcx8IkiNWSbMKiMOupDzlwO5aynupontg56YQLx9ygmlztOiM4iSGK5tgX_gMtqltlmwWbtGUCrC3N5u3Zq1y-SwlwCj_13s2bOYB5KVkSFDpnCPjbFg71i0kQO72Q3347BitsjZSJ8_vpySkX6O1C0S90f5Bljh4W10SBqYCBD-vGSGOrG5hpSPV70iXe-JKTNOKFRZSoVSzh6K_JAxji9X65IQsvZ1Qe6rNED3a0MWhZWXiGYSMo6PTmNzrrQKs3fIr8dd9ps1ePAWCNOujbk06F6MsDwvdMowwthg9p0OqhdIy2XieG17VuBTkJyvd4BLSurgz8W9o5QfDdseQ


# 
https://kubernetes.io/docs/concepts/workloads/controllers/deployment/



"---" in yaml file ==> it indicate to the yaml file to treat these single yaml file as a two separate yaml file 


PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get deployments
No resources found in default namespace.
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get services
NAME         TYPE        CLUSTER-IP   EXTERNAL-IP   PORT(S)   AGE
kubernetes   ClusterIP   10.96.0.1    <none>        443/TCP   21d
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get replicasets
No resources found in default namespace.


#
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl apply -f configserver.yml
deployment.apps/configserver-deployment created
service/configserver created
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get deployments
NAME                      READY   UP-TO-DATE   AVAILABLE   AGE
configserver-deployment   1/1     1            1           21s
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get services
NAME           TYPE           CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
configserver   LoadBalancer   10.110.73.236   localhost     8071:32237/TCP   70s
kubernetes     ClusterIP      10.96.0.1       <none>        443/TCP          21d
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get replicasets
NAME                                 DESIRED   CURRENT   READY   AGE
configserver-deployment-6fd786f5fd   1         1         1       2m8s
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get pods
NAME                                       READY   STATUS    RESTARTS   AGE
configserver-deployment-6fd786f5fd-d6grt   1/1     Running   0          2m39s

# 
http://localhost:8071/accounts/prod  => accounts related properties from production
http://localhost:8071/loans/prod
http://localhost:8071/cards/prod
http://localhost:8071/eurekaserver/default

==> This confirms our configserver is successfully deployed into the kubernetes cluster.


# create configmaps
https://kubernetes.io/docs/concepts/configuration/configmap/
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl apply -f configmaps.yaml
configmap/eazybank-configmap created


# Note: 
In kubernetes manifest file, we expose service as a LoadBalancer => that means anyone can access that service at the given port number



# Deploying microservices 
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl apply -f 1_keycloak.yml
deployment.apps/keycloak created
service/keycloak created
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl apply -f 2_configmaps.yaml
configmap/eazybank-configmap unchanged
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl apply -f 3_configserver.yml
deployment.apps/configserver-deployment configured
service/configserver unchanged
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl apply -f 4_eurekaserver.yml
deployment.apps/eurekaserver-deployment created
service/eurekaserver created
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl apply -f 5_accounts.yml
deployment.apps/accounts-deployment created
service/accounts created
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl apply -f 6_loans.yml
deployment.apps/loans-deployment created
service/loans created
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl apply -f 7_cards.yml
deployment.apps/cards-deployment created
service/cards created

# after waiting for a few minute, now we can go to Eureka dashboard to see if our microservices are regist
http://localhost:8070/

# Now we start our gatewayserver
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl apply -f 8_gateway.yml
deployment.apps/gatewayserver-deployment created
service/gatewayserver created

# Before test our microservices in Postman we need to create client detail (Client ID: eazybank-callcenter-cc with roleS: ACCOUNTS, LOANS, CARDS) in  keycloak 
http://localhost:7080/


# Self healing
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get replicaset
NAME                                  DESIRED   CURRENT   READY   AGE
accounts-deployment-68748f4f5         1         1         1       25m
cards-deployment-8476f7c94b           1         1         1       24m
configserver-deployment-6fd786f5fd    0         0         0       11h
configserver-deployment-769c967468    1         1         1       28m
eurekaserver-deployment-78484bf6f5    1         1         1       26m
gatewayserver-deployment-6f94f8cb9f   1         1         1       20m
keycloak-5df7dcfd5                    1         1         1       31m
loans-deployment-9b48db958            1         1         1       24m
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get pods
NAME                                        READY   STATUS    RESTARTS   AGE
accounts-deployment-68748f4f5-rsx62         1/1     Running   0          25m
cards-deployment-8476f7c94b-sh959           1/1     Running   0          24m
configserver-deployment-769c967468-kww8q    1/1     Running   0          28m
eurekaserver-deployment-78484bf6f5-5rnk7    1/1     Running   0          27m
gatewayserver-deployment-6f94f8cb9f-v89qn   1/1     Running   0          21m
keycloak-5df7dcfd5-npvgx                    1/1     Running   0          31m
loans-deployment-9b48db958-jkwjl            1/1     Running   0          25m
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl apply -f 5_accounts.yml
deployment.apps/accounts-deployment configured
service/accounts unchanged
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get replicaset
NAME                                  DESIRED   CURRENT   READY   AGE
accounts-deployment-68748f4f5         2         2         2       26m
cards-deployment-8476f7c94b           1         1         1       25m
configserver-deployment-6fd786f5fd    0         0         0       11h
configserver-deployment-769c967468    1         1         1       29m
eurekaserver-deployment-78484bf6f5    1         1         1       27m
gatewayserver-deployment-6f94f8cb9f   1         1         1       21m
keycloak-5df7dcfd5                    1         1         1       31m
loans-deployment-9b48db958            1         1         1       25m
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get pods
NAME                                        READY   STATUS    RESTARTS   AGE
accounts-deployment-68748f4f5-rsx62         1/1     Running   0          26m
accounts-deployment-68748f4f5-wxwqn         1/1     Running   0          18s
cards-deployment-8476f7c94b-sh959           1/1     Running   0          25m
configserver-deployment-769c967468-kww8q    1/1     Running   0          29m
eurekaserver-deployment-78484bf6f5-5rnk7    1/1     Running   0          27m
gatewayserver-deployment-6f94f8cb9f-v89qn   1/1     Running   0          21m
keycloak-5df7dcfd5-npvgx                    1/1     Running   0          32m
loans-deployment-9b48db958-jkwjl            1/1     Running   0          26m


#
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl delete pod accounts-deployment-68748f4f5-wxwqn
pod "accounts-deployment-68748f4f5-wxwqn" deleted
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get replicaset
NAME                                  DESIRED   CURRENT   READY   AGE
accounts-deployment-68748f4f5         2         2         2       28m
cards-deployment-8476f7c94b           1         1         1       28m
configserver-deployment-6fd786f5fd    0         0         0       11h
configserver-deployment-769c967468    1         1         1       31m
eurekaserver-deployment-78484bf6f5    1         1         1       30m
gatewayserver-deployment-6f94f8cb9f   1         1         1       24m
keycloak-5df7dcfd5                    1         1         1       34m
loans-deployment-9b48db958            1         1         1       28m
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get pods
NAME                                        READY   STATUS    RESTARTS   AGE
accounts-deployment-68748f4f5-pt8rw         1/1     Running   0          27s
accounts-deployment-68748f4f5-rsx62         1/1     Running   0          28m
cards-deployment-8476f7c94b-sh959           1/1     Running   0          28m
configserver-deployment-769c967468-kww8q    1/1     Running   0          32m
eurekaserver-deployment-78484bf6f5-5rnk7    1/1     Running   0          30m
gatewayserver-deployment-6f94f8cb9f-v89qn   1/1     Running   0          24m
keycloak-5df7dcfd5-npvgx                    1/1     Running   0          34m
loans-deployment-9b48db958-jkwjl            1/1     Running   0          28m


# 
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get events --sort-by=.metadata.creationTimestamp
LAST SEEN   TYPE     REASON              OBJECT                                           MESSAGE
37m         Normal   Scheduled           pod/keycloak-5df7dcfd5-npvgx                     Successfully assigned default/keycloak-5df7dcfd5-npvgx to docker-desktop
37m         Normal   ScalingReplicaSet   deployment/keycloak                              Scaled up replica set keycloak-5df7dcfd5 to 1

6m2s        Normal   ScalingReplicaSet   deployment/accounts-deployment                   Scaled up replica set accounts-deployment-68748f4f5 to 2 from 1
6m1s        Normal   Created             pod/accounts-deployment-68748f4f5-wxwqn          Created container accounts
6m1s        Normal   Started             pod/accounts-deployment-68748f4f5-wxwqn          Started container accounts
6m1s        Normal   Pulled              pod/accounts-deployment-68748f4f5-wxwqn          Container image "adjodamawuli/accounts:s12" already present on machine
3m38s       Normal   Killing             pod/accounts-deployment-68748f4f5-wxwqn          Stopping container accounts
3m38s       Normal   SuccessfulCreate    replicaset/accounts-deployment-68748f4f5         Created pod: accounts-deployment-68748f4f5-pt8rw
3m37s       Normal   Scheduled           pod/accounts-deployment-68748f4f5-pt8rw          Successfully assigned default/accounts-deployment-68748f4f5-pt8rw to docker-desktop
3m37s       Normal   Started             pod/accounts-deployment-68748f4f5-pt8rw          Started container accounts
3m37s       Normal   Created             pod/accounts-deployment-68748f4f5-pt8rw          Created container accounts
3m37s       Normal   Pulled              pod/accounts-deployment-68748f4f5-pt8rw          Container image "adjodamawuli/accounts:s12" already present on machine


# scale using command line 
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl scale deployment accounts-deployment --replicas=1
deployment.apps/accounts-deployment scaled
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get pods
NAME                                        READY   STATUS    RESTARTS   AGE
accounts-deployment-68748f4f5-rsx62         1/1     Running   0          43m
cards-deployment-8476f7c94b-sh959           1/1     Running   0          42m
configserver-deployment-769c967468-kww8q    1/1     Running   0          46m
eurekaserver-deployment-78484bf6f5-5rnk7    1/1     Running   0          44m
gatewayserver-deployment-6f94f8cb9f-v89qn   1/1     Running   0          38m
keycloak-5df7dcfd5-npvgx                    1/1     Running   0          49m
loans-deployment-9b48db958-jkwjl            1/1     Running   0          42m
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get replicaset
NAME                                  DESIRED   CURRENT   READY   AGE
accounts-deployment-68748f4f5         1         1         1       44m
cards-deployment-8476f7c94b           1         1         1       43m
configserver-deployment-6fd786f5fd    0         0         0       11h
configserver-deployment-769c967468    1         1         1       47m
eurekaserver-deployment-78484bf6f5    1         1         1       45m
gatewayserver-deployment-6f94f8cb9f   1         1         1       39m
keycloak-5df7dcfd5                    1         1         1       50m
loans-deployment-9b48db958


# Automatic rollout => deploy gatewayserver:s11 
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl describe pod gatewayserver-deployment-6f94f8cb9f

PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl set image deployment gatewayserver-deployment gatewayserver=eazybytes/gatewayserver:s11 --record
Flag --record has been deprecated, --record will be removed in the future
deployment.apps/gatewayserver-deployment image updated

PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get event --sort-by=.metadata.creationTimestamp

#
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl rollout history deployment gatewayserver-deployment
deployment.apps/gatewayserver-deployment
REVISION  CHANGE-CAUSE
1         <none>
2         kubectl.exe set image deployment gatewayserver-deployment gatewayserver=eazybytes/gatewayserver:s111 --record=true
3         kubectl.exe set image deployment gatewayserver-deployment gatewayserver=eazybytes/gatewayserver:s11 --record=true


# rollout to revision=1
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl rollout undo deployment gatewayserver-deployment --to-revision=1
deployment.apps/gatewayserver-deployment rolled back
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get pods
NAME                                        READY   STATUS    RESTARTS   AGE
accounts-deployment-68748f4f5-rsx62         1/1     Running   0          61m
cards-deployment-8476f7c94b-sh959           1/1     Running   0          60m
configserver-deployment-769c967468-kww8q    1/1     Running   0          64m
eurekaserver-deployment-78484bf6f5-5rnk7    1/1     Running   0          63m
gatewayserver-deployment-6f94f8cb9f-hn6zr   1/1     Running   0          22s
keycloak-5df7dcfd5-npvgx                    1/1     Running   0          67m
loans-deployment-9b48db958-jkwjl            1/1     Running   0          61m

PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl describe pod gatewayserver-deployment-6f94f8cb9f-hn6zr
	
	Containers:
  gatewayserver:
    Container ID:   docker://d0f8448eef1f2be6bbfc7d0b3ecf7682209ab43664a9343389fc9c2b7d27e696
    Image:          adjodamawuli/gatewayserver:s12
    Image ID:       docker-pullable://adjodamawuli/gatewayserver@sha256:c04bed6527b74a70a54aa6996b7748d4c0635e89581ea2c906a49a27e5e55b34
	
	
	
# Service types 
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get services
NAME            TYPE           CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE
accounts        LoadBalancer   10.97.193.111    localhost     8080:32209/TCP   82m
cards           LoadBalancer   10.111.158.247   localhost     9000:30408/TCP   81m
configserver    LoadBalancer   10.110.73.236    localhost     8071:32237/TCP   12h
eurekaserver    LoadBalancer   10.104.53.88     localhost     8070:31763/TCP   83m
gatewayserver   LoadBalancer   10.101.96.217    localhost     8072:30606/TCP   77m
keycloak        LoadBalancer   10.110.23.99     localhost     7080:31982/TCP   88m
kubernetes      ClusterIP      10.96.0.1        <none>        443/TCP          22d
loans           LoadBalancer   10.111.16.168    localhost     8090:30569/TCP   81m


#
LoadBalancer => that means anyone can access that service at the given port number on the EXTERNAL-IP
curl http://localhost:8080/api/contact-info
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> curl http://localhost:8080/api/contact-info


StatusCode        : 200
StatusDescription :
Content           : {"message":"Hey, welcome to EazyBank accounts related webhook APIs","contactDetails":{"name":"Reine Aishwarya - Product
                    Owner","email":"aishwarya@eazybank.com"},"onCallSupport":["(453) 392-4829","(236...
RawContent        : HTTP/1.1 200


#we change service type to ClusterIP  in acccounts.yml file 

PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl apply -f 5_accounts.yml
	deployment.apps/accounts-deployment configured
	service/accounts configured
	
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl get services
	NAME            TYPE           CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE
	accounts        ClusterIP      10.97.193.111    <none>        8080/TCP         92m
	cards           LoadBalancer   10.111.158.247   localhost     9000:30408/TCP   91m
	configserver    LoadBalancer   10.110.73.236    localhost     8071:32237/TCP   12h
	eurekaserver    LoadBalancer   10.104.53.88     localhost     8070:31763/TCP   94m
	gatewayserver   LoadBalancer   10.101.96.217    localhost     8072:30606/TCP   88m
	keycloak        LoadBalancer   10.110.23.99     localhost     7080:31982/TCP   98m
	kubernetes      ClusterIP      10.96.0.1        <none>        443/TCP          22d
	loans           LoadBalancer   10.111.16.168    localhost     8090:30569/TCP   92m
	PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> curl http://localhost:8080/api/contact-info
	curl : Unable to connect to the remote server
	At line:1 char:1
	+ curl http://localhost:8080/api/contact-info

 ==> we cannot reach accounts service because service type is ClusterIP



# Delete deployments

PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl delete -f 8_gateway.yml
deployment.apps "gatewayserver-deployment" deleted
service "gatewayserver" deleted
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl delete -f 7_cards.yml
deployment.apps "cards-deployment" deleted
service "cards" deleted
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl delete -f 6_loans.yml
deployment.apps "loans-deployment" deleted
service "loans" deleted
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl delete -f 5_accounts.yml
deployment.apps "accounts-deployment" deleted
service "accounts" deleted
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl delete -f 4_eurekaserver.yml
deployment.apps "eurekaserver-deployment" deleted
service "eurekaserver" deleted
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl delete -f 3_configserver.yml
deployment.apps "configserver-deployment" deleted
service "configserver" deleted
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl delete -f 2_configmaps.yaml
configmap "eazybank-configmap" deleted
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section15-k8s\kubernetes> kubectl delete -f 1_keycloak.yml
deployment.apps "keycloak" deleted
service "keycloak" deleted