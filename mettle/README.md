# Back-end Pairing Service

This is a basic Spring Boot application connected to an in memory h2 database.

Have a browse of the code! You'll see we already have a simple web controller and a repository
with a DB table called 'car'.

Please complete the following tasks. Don't rush! Quality is
more important than quantity :)

Please feel free to google anything you need - in fact, it's encouraged.

### Part 1: 
You'll notice a method exists in `SimpleController` to get cars. 

Implement that to return all cars in the database as json.

### Part 2: 
We have a 3rd party providing cars. 

Create an endpoint to return the *count* of all cars from our third party with colour RED.

### Part 3: 
Add a new method to create a car.

### Part 4:
Have a look in `Makefile` in this repo (to check what you're about to run), and delete the repo with `make delete-test`
