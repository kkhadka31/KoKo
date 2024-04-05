# KoKo
My Webapp

Details:

This is Java Spring boot app.
Use Case -> Simple Blog application, so far powered by java spring boot and mysql.

How to run:

You'll need to run a sql server, and create tables which is available in the path -> ./KoKo/src/main/java/com/koshish/SQL

Make sure to change the DB password in the following paths ./KoKo/src/main/resources/application.properties and ./KoKo/src/main/java/com/koshish/Repository/DB.java

The server port is 8080, and to run it simply you can use a IDE like Intellij and run the main method, path -> /Users/koshishkhadka/Projects/KoKo/src/main/java/com/koshish/KoKo/KoKoApplication.java
Or, use .jar file available at target directory.

You can now use postman as a client to run different services available in this app, you can refer to the openapi.yml file for it.