1. create mysql DB as container 

        lefort@ADJODA:~$ docker run -p 3306:3306 --name accountsdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=accountsdb -d mysql
        lefort@ADJODA:~$ docker run -p 3307:3306 --name loansdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=loansdb -d mysql
        lefort@ADJODA:~$ docker run -p 3308:3306 --name cardsdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=cardsdb -d mysql

2. Install mysql client like https://sqlectron.github.io/
3. Information

In real production, MySQL DBAs will attach a storage or volume where the data can be stored by the MySQL container. 
That's why in real prod MySQL containers, we never lost data even if we delete or replace the MySQL container






      docker compose up or docker compose up -d
      docker compose down

4. To show docker network
      
       docker network ls
5.  inspect


      lefort@ADJODA:/mnt/d/dev/java/microservices/master-microservices-udemy/section7/cards$ docker network ls
      NETWORK ID     NAME                           DRIVER    SCOPE
      eb3c81695542   accounts_eazybank              bridge    local
      32975a2fc035   bridge                         bridge    local
      87dd7a9249e4   ekwateur-billing-app_default   bridge    local
      d5d935d8ae3a   host                           host      local
      974e56635e14   minikube                       bridge    local
      8520f4143387   none                           null      local
      66ed24c2c076   qa_eazybank                    bridge    local
      lefort@ADJODA:/mnt/d/dev/java/microservices/master-microservices-udemy/section7/cards$ docker inspect eb3
      [
      {
      "Name": "accounts_eazybank",
      "Id": "eb3c81695542ef178bf37f901f4986a586682fcb21e248ee64c58dadda8b294d",
      "Created": "2024-04-12T21:00:41.163522239+02:00",
      "Scope": "local",
      "Driver": "bridge",
      "EnableIPv6": false,
      "IPAM": {
      "Driver": "default",
      "Options": null,
      "Config": [
      {
      "Subnet": "172.21.0.0/16",
      "Gateway": "172.21.0.1"
      }
      ]
      },
      "Internal": false,
      "Attachable": false,
      "Ingress": false,
      "ConfigFrom": {
      "Network": ""
      },
      "ConfigOnly": false,
      "Containers": {},
      "Options": {},
      "Labels": {
      "com.docker.compose.network": "eazybank",
      "com.docker.compose.project": "accounts",
      "com.docker.compose.version": "2.24.6"
      }
      }
      ]