

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