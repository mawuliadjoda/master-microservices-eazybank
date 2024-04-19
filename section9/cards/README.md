
build docker image using Google Jib

      https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin#quickstart


1. add config to pom


    <plugin>
        <groupId>com.google.cloud.tools</groupId>
        <artifactId>jib-maven-plugin</artifactId>
        <version>3.4.1</version>
        <configuration>
          <to>
            <image>myimage</image>
          </to>
        </configuration>
    </plugin>


2. build image 

        mvn compile jib:dockerBuild

3. Run image 
   
       docker run -d -p 8080:8080 adjodamawuli/accounts:s4

4push image to docker hub

      docker login
      lefort@ADJODA:/mnt/d/dev/java/microservices/master-microservices-udemy/section4/cards$ docker image push docker.io/adjodamawuli/cards:s4