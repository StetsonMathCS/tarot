# Spring Backend
###### *© Riedl Kevin, 2018*

## What is it?
This document describes the backend of Tarot made with the Java-framework 'Spring' (using the MVC pattern).

## Features
As we had to do our work according to specific features, I decided to simply list all features which have been done and then briefly describing how to use/test/execute them etc.

Features done: 
- As a regular user of the application, I want to receive a simple error page if sth. went wrong so potential user might find a solution for that issue. 
- As a regular user of the application, I want to login via a password/pin without having separate users in the backend so that there is no issue in managing different accounts.
- As a regular user of the application, I want to be able to share my generated data via an unique link (e.g. by generating a hash of that file and using that hash as a get-param) 
- As a regular user of the application, I want to be able to download the generated file as e.g. a CSV-file so that files don't necessarily need to be shared via a link. 
- As a regular user of the application, I want some sort of session handling (implement some sort of middleware), so that I won't need to login again after every click.
- As a regular user of the application, I want some sort of session handling (implement some sort of middleware), so that I won't need to login again after every click.
- As a maintainer of the application, I want to have e.g. some small interfaces (cohesion!) to configure/change hardcoded variables, so that I won't need to look through old code. 
- Establish db connection, create interface
- Tomcat logs should be accessible outside of docker
- Add analytics scripts. 
- Dockerize
- JUnit tests
- Merge code with Bailey
- hook into real db (only localhost but outside of docker)
- parse data from CPlex directly (algo guys) -> currently Json?!!
- rowMap HashMap load Data from Db and save back (mapping). HashMap is enough. 
- show CPLEX data result as table and make it downloadable as csv (shareable via hashed link). PARSE REALLY, shorten table, human readable etc.

### Error page
> As a regular user of the application, I want to receive a simple error page if sth. went wrong so potential user might find a solution for that issue.

Basically, there are many different kinds of errors, but we separate them here only into client- and server-side errors. Server-side errors result in a simple error page every time and whenever they occur. 

Client-side errors (like 404 Resource not found) on the other hand are mostly only shown to the user when he is already authenticated/logged in. Therefore, when a user tries to access a page which does not exist and he is not logged in, he will be simply redirected to the login panel. I chose this approach for usability and security reasons.

#### Test
You can test this by accessing a random url, e.g.: [http://tarot.artifice.cc:8080/jsdjds](http://tarot.artifice.cc:8080/jsdjds) when logged in and when unauthenticated. 
**NOTE: The server was accessible via port 80, but as Mikhael wanted to add a proxy or similar what worked, too. But, today seemingly his proxy is down so you might need to access the site via port 8080.**

### Login
 > As a regular user of the application, I want to login via a password/pin without having separate users in the backend so that there is no issue in managing different accounts.
 
 
 