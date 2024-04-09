# KoKo
My Webapp

**Details:**

This is Java Spring boot app.
Use Case -> Simple Blog application, so far powered by java spring boot and mysql. Currently the application spring boot is deployed on Amazon EC2 and the mysql server is deployed on Amazon RDS. The frontend is under development along with lots of other features, services and tech stacks. 

**Test this application:**
To test this application, you can use client like postman, please refer to the openapi.yml file to test different api provided by this application. URL -> http://3.144.218.85:8080/rc/
Note: Please don't flood the host with heavy traffic because Amazon charges good money for heavy use cases :)

How to run in local:

You'll need to run a sql server, and create tables which is available in the path -> ./KoKo/src/main/java/com/koshish/SQL

Make sure to change the DB password in the following paths ./KoKo/src/main/resources/application.properties and ./KoKo/src/main/java/com/koshish/Repository/DB.java

The server port is 8080, and to run it simply you can use a IDE like Intellij and run the main method, path -> /Users/koshishkhadka/Projects/KoKo/src/main/java/com/koshish/KoKo/KoKoApplication.java
Or, use .jar file available at target directory.

You can now use postman as a client to run different services available in this app, you can refer to the openapi.yml file for it.

Make sure to use JWT token for the requests that are not whitelisted. You can obtain JWT token by using login or register services.