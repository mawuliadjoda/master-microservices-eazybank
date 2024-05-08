

kubectl -n kubernetes-dashboard port-forward svc/kubernetes-dashboard-kong-proxy 8443:443

Kubernetes dashboard:
https://localhost:8443/



First install helm in our local machine 
https://helm.sh/

if choco is not install = > https://chocolatey.org/install


PS C:\WINDOWS\system32> choco install kubernetes-helm
PS C:\WINDOWS\system32> helm version
version.BuildInfo{Version:"v3.14.3", GitCommit:"f03cc04caaa8f6d7c3e67cf918929150cf6f3f12", GitTreeState:"clean", GoVersion:"go1.21.7"}


cmd 

> helm ls


https://collabnix.github.io/kubelabs/Helm101/installing-a-chart.html

> helm repo add bitnami https://charts.bitnami.com/bitnami
	"bitnami" has been added to your repositories


> helm install happy-panda bitnami/wordpress
> helm uninstall happy-panda

> helm create eazybank-common

koffi@ADJODA /cygdrive/d/dev/java/microservices/master-microservices-udemy/master-microservices-udemy-jump/section16-helm/helm/eazybank-services

$ helm create accounts
Creating accounts

$ helm dependencies build



--

kubectl -n kubernetes-dashboard port-forward svc/kubernetes-dashboard-kong-proxy 8443:443


kubectl get pvc

kubectl delete pvc data-happy-panda-mariadb-0


--

$ helm dependencies build
$ helm install grafana grafana


helm ls
NAME            NAMESPACE       REVISION        UPDATED                                 STATUS          CHART                   APP VERSION
grafana         default         1               2024-05-08 13:37:15.6608575 +0200 CEST  deployed        grafana-10.0.10         10.4.2
kafka           default         1               2024-05-08 12:39:53.0490656 +0200 CEST  deployed        kafka-28.2.1            3.7.0
keycloak        default         1               2024-05-08 11:42:03.1936254 +0200 CEST  deployed        keycloak-21.1.2         24.0.3
loki            default         1               2024-05-08 12:58:40.6109358 +0200 CEST  deployed        grafana-loki-4.0.1      3.0.0
prometheus      default         1               2024-05-08 12:49:34.3062438 +0200 CEST  deployed        kube-prometheus-9.0.7   0.73.2
tempo           default         1               2024-05-08 13:02:59.2059882 +0200 CEST  deployed        grafana-tempo-3.1.1     2.4.1



helm dependencies build
helm upgrade eazybank prod-env

helm history eazybank
REVISION        UPDATED                         STATUS          CHART           APP VERSION     DESCRIPTION
1               Wed May  8 13:53:44 2024        superseded      pod-env-0.1.0   1.0.0           Install complete
2               Wed May  8 14:26:05 2024        superseded      pod-env-0.1.0   1.0.0           Upgrade complete
3               Wed May  8 14:30:11 2024        deployed        pod-env-0.1.0   1.0.0           Upgrade complete
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section16-helm\helm\environments> helm rollback eazybank 1
Rollback was a success! Happy Helming!
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section16-helm\helm\environments> helm history eazybank
REVISION        UPDATED                         STATUS          CHART           APP VERSION     DESCRIPTION
1               Wed May  8 13:53:44 2024        superseded      pod-env-0.1.0   1.0.0           Install complete
2               Wed May  8 14:26:05 2024        superseded      pod-env-0.1.0   1.0.0           Upgrade complete
3               Wed May  8 14:30:11 2024        superseded      pod-env-0.1.0   1.0.0           Upgrade complete
4               Wed May  8 14:34:45 2024        deployed        pod-env-0.1.0   1.0.0           Rollback to 1


-- 

PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section16-helm\helm\environments> helm ls
NAME            NAMESPACE       REVISION        UPDATED                                 STATUS          CHART                   APP VERSION
eazybank        default         4               2024-05-08 14:34:45.1995242 +0200 CEST  deployed        pod-env-0.1.0           1.0.0
grafana         default         1               2024-05-08 13:37:15.6608575 +0200 CEST  deployed        grafana-10.0.10         10.4.2
kafka           default         1               2024-05-08 12:39:53.0490656 +0200 CEST  deployed        kafka-28.2.1            3.7.0
keycloak        default         1               2024-05-08 11:42:03.1936254 +0200 CEST  deployed        keycloak-21.1.2         24.0.3
loki            default         1               2024-05-08 12:58:40.6109358 +0200 CEST  deployed        grafana-loki-4.0.1      3.0.0
prometheus      default         1               2024-05-08 12:49:34.3062438 +0200 CEST  deployed        kube-prometheus-9.0.7   0.73.2
tempo           default         1               2024-05-08 13:02:59.2059882 +0200 CEST  deployed        grafana-tempo-3.1.1     2.4.1
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section16-helm\helm\environments> helm uninstall eazybank
release "eazybank" uninstalled
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section16-helm\helm\environments> helm uninstall grafana
release "grafana" uninstalled
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section16-helm\helm\environments> helm uninstall tempo
release "tempo" uninstalled
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section16-helm\helm\environments> helm uninstall loki
release "loki" uninstalled
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section16-helm\helm\environments> helm uninstall prometheus
release "prometheus" uninstalled
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section16-helm\helm\environments> helm uninstall kafka
release "kafka" uninstalled
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section16-helm\helm\environments> helm uninstall keycloak
release "keycloak" uninstalled
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section16-helm\helm\environments> helm ls
NAME    NAMESPACE       REVISION        UPDATED STATUS  CHART   APP VERSION
PS D:\dev\java\microservices\master-microservices-udemy\master-microservices-udemy-jump\section16-helm\helm\environments>