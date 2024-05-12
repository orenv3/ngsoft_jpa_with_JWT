### This is a Spring Boot application that exposes a REST API.
### The propose of this Application is to manage users, user's tasks and user's.comments.

##### The API should allow clients to perform the following actions:

The user must to login and retrieve an oauth2 bearer token, 
in order to use the below actions!
  * Retrieve the list of tasks
  * Add a new task to the list
  * Update the details of a task
  * Mark a task as completed
  * Remove a task from the list
  * Retrieve the list of users
  * Add a new user to the list
  * Update the details of a user
  * Remove a user from the list
  * Add comment to task

Description (more details):
1. A user can only fetch and see his tasks.
2. When a user see the task, he may see **all** comments 
(other users comments - even if he did not create them).
3. Regular user can:
   a. View his tasks and their comments,
   b. Create a comment on his tasks,
   c. Mark a comment as completed.
4. Admin can:
   a. Create users,
   b. Activate/deactivate user,
   c. Create tasks,
   d. Assign/re-assign tasks,
   e. Comment on tasks
   f. Mark completed tasks as archived
5. An archived task is not visible to the user.
6. I used an in-memory database (H2) to store the tasks and users.
7. Password hashing and authentication/authorization for the user API using Spring Security

>#### Task table:
> * ID (generated automatically)
> * Title
> * Description
> * Status with fixed possibilities(pending/completed/archived)
> * Assignee (user to whom the task is assigned)

>#### Comment table:
> * ID (generated automatically)
> * UserId (foreign key)
> * TaskId (foreign key)
> * TimeStamp (Date)
> * Comment (Text)

> #### User table:
> * ID (generated automatically)
> * Name
> * Email
> * IsAdmin (true,false)
> * Active (true,false)
> * Password (hashed)



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