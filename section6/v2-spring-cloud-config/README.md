# Refresh configuration best approche:  Refresh configurations using Spring cloud bus & Spring cloud Config monitor and github webhook

Run Rabbitmq in local
https://www.rabbitmq.com/docs/download

    docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.13-management


to configure github webhook for localhost => we can use hookdeck but not working on my local machine
https://console.hookdeck.com/

    docker pull hookdeck/hookdeck-cli
    docker run --rm -it hookdeck/hookdeck-cli version
    hookdeck login --cli-key 5oxkzts2ncvzhn3ilyz2on6qzqinpfv2gil3pfyj45zrbkuino

==> hookdeck installation not working on my local machine so let's use the powerfull of ngrok alternatively 

    ngrok http 8071
we will get https url to configure our webhook in github  
https://4847-2001-861-45c1-9250-34c2-3f64-1d41-bb9c.ngrok-free.app/monitor 
to configure hebhook on github to go setting of https://github.com/mawuliadjoda/eazybank-config/settings



# configserver liveness and readiness 
http://localhost:8071/actuator/health
http://localhost:8071/actuator/health/liveness
http://localhost:8071/actuator/health/readiness