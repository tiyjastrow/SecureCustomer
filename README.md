# SecureCustomer

## Description:
  Add web security to the SpringYard project.
---

### Getting Started

For this project you will be enhancing your SpringYard project to use security for authentication and authorization (A&A).

### Setup GitHub

At the end of the last daily project finished on a `jpa` branch. For this project create a `security` branch from `jpa`.

### Secure the Web Page Access

Go through the same steps from the lesson/activity and apply them to your customer project. (You may have accomplished some of these already as part of your weekend project - feel free to port them over and just make updates for security as needed).

1. Create an `index.html` file in the `resource/static` directory that has a link to show all customers
2. Create Thymeleaf web pages to view customers and add a customer
3. Create a `CustomerController` with the routes to view customers and add customers
4. Add the security dependency to the pom.xml
5. Create the `users` and `authorities` tables and add at least one user to both tables
6. Add the WebSecurityConfig class making sure that the `configureGlobal` method is using the `Datasource` to query the `users` and `authorities` tables. Make sure to protect the route that adds a user
7. Create the login web page
8. Add the login route to the controller

### You will NEED the users table 
Create a users table:
* `CREATE TABLE users (username text not null, password text not null, enabled boolean, constraint users_pkey primary key (username));`

Create a authorities table:
* `CREATE TABLE authorities (username text not null, authority text not null, constraint authorities_pkey primary key (username, authority));`

Add users
* `insert into users (username, password, enabled) values ('zoe', 'verse', true);`
* `insert into users (username, password, enabled) values ('kaylee', 'shiny', true);`

Add roles
* `insert into authorities (username, authority) values ('zoe', 'ROLE_USER');`
* `insert into authorities (username, authority) values ('kaylee', 'ROLE_USER');`
* `insert into authorities (username, authority) values ('zoe', 'ROLE_ADMIN');`

## Extra
* Add the ability to logout.
* Add at least one more web page the is only accessible by an admin user
