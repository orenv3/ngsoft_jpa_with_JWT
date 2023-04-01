* run app via CMD: java -jar ngsoft-0.1.jar
* localhost:8080/swagger-ui.html: swagger works with bearer token

* @ControllerAdvice enabled
* Validation on CRUD queries.
* Create task with the same title is forbidden
* Creating user with the same email is forbidden 
* Task status in creation - if(status == null || status.isBlank()) is PENDING by default
* Task status update - if(status == null || status.isBlank()) do nothing



1- register - first need to register user - you will get register's token in the response
2- authenticate - user login. you will get login user token in the response and work with it.
3- set the given token in the swagger
4- call needed API


** You will need Auth token of admin user to begin testing **

** First ADMIN user will always be:
* user: orenv@vinogura
* password: 1234

orenv@vinogura token will be in the console:
for example : AuthResponse(token=eyJhbGciOiJIUzI1NiJ9.sdfsd....)