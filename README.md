# crossmint-challenge
Crossming Challenge  https://challenge.crossmint.io/challenge

You can run application by executing <code>./mvnw spring-boot:run</code> on project directory. 

An app will be hosted and you can see it's running ok if you open browser on http://localhost:8080/actuator/health url. You should receive <code>UP</code> status

Also I created a small web with related links: http://localhost:8080/index.html

## Swagger-UI:
You can check service invocation here: http://localhost:8080/swagger-ui/index.html

### Phase 1
To execute phase 1 challenge, you have to call service <code>POST /polyanetsX </code>

### Phase 2
To execute phase 2 challenge, you have to call service <code>POST /crossmint-logo </code>

## Actuator
To check service health: 
http://localhost:8080/actuator/health
