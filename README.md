# kotlin-challenge-c

Run ``docker-compose up``

if you can create a docker image you have to run this command

``docker build -t kotlin-challenge:1.1 .``

and then run 

``docker run --network  myNet  -p 8081:8081 kotlin-challenge:1.1  ``

------
If you want to run this project in Intellij you have to change the application.properties

From
``spring.datasource.url=jdbc:postgresql://postgres:5432/postgres``

TO
``spring.datasource.url=jdbc:postgresql://localhost:5432/postgres``

And also you have to change de kafka Address

FROM ``kafka.bootstrapAddress=kafka:9092``

TO ``kafka.bootstrapAddress=localhost:9094``

How it works 

![img.png](img.png)