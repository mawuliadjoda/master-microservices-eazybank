1. build image using Buildpacks
   https://buildpacks.io/features/
    
        lefort@ADJODA:/mnt/d/dev/java/microservices/master-microservices-udemy/section4/loans$ mvn spring-boot:build-image


2. Run image

         lefort@ADJODA:/mnt/d/dev/java/microservices/master-microservices-udemy/section4/loans$ docker run -d -p 8090:8090 adjodamawuli/loans:s4
         34db658a3244f1546294596697ff69fbfb68ae974108a38e87345a697d60cd0a

   3. push image to docker hub

            docker login
            lefort@ADJODA:/mnt/d/dev/java/microservices/master-microservices-udemy/section4/cards$ docker image push docker.io/adjodamawuli/loans:s4