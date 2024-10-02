Project Overview
The Task Management Application is a simple CRUD-based web application built using Spring Boot. It allows users to register,
log in, and manage their own tasks. Each user has their own list of tasks, and the application provides functionality to create, update, view, and delete tasks. 
It also includes Spring Security for user authentication and authorization.

Note: Screenshot folder into project.

Open a browser and go to http://localhost:8080 to access the application.
Landing Page link: http://localhost:8080

Register page: http://localhost:8080/register
login page: http://localhost:8080/login

Common Endpoints
/register - Register a new user.
/login - Login page.
/user/tasks - View tasks.
/user/tasks/new - Add a new task.
/user/tasks/edit/{id} - Edit an existing task.
/user/tasks/delete/{id} - Delete a task.

Security Configuration
The application uses Spring Security for authentication.
Public pages: /register, /login, and landing page.
Restricted pages: /user/tasks/** (only accessible after login).

Used Technologies: Java, Spring Boot, Maven, Hibernate, MySQL, Apache Tomcat, Spring Security, Thymeleaf, Html, css, Javascript, Bootstrap.

PostMan: 

Authentication Endpoints
1. Register a New User
Endpoint: /register

Method: POST
Request URL: http://localhost:8080/register
Headers:

Key	Value
Content-Type	application/json
Request Body:

json:
{
  "username": "john_doe",
  "password": "password123"
}

Example Response:

json
Copy code
{
  "message": "User registered successfully."
}


Postman API Documentation: Task Management System 📬
This document provides a guide for testing the Task Management System APIs using Postman. Each endpoint corresponds to the CRUD operations in the application and includes details such as request type, request body, and expected responses.

Table of Contents
Getting Started with Postman
Authentication Endpoints
Register a New User
User Login
Task Management Endpoints
Create a New Task
Fetch All Tasks
Fetch a Single Task by ID
Update a Task
Delete a Task
Global Exception Handling
Postman Collection File
Getting Started with Postman
Install Postman if you haven’t already: Download Postman.
Create a New Request for each of the endpoints listed below.
Set up the Base URL: Replace localhost with your EC2 instance’s public DNS (e.g., http://ec2-xx-xxx-xxx-xxx.compute-1.amazonaws.com) if running on AWS.
Base URL
For local testing:

arduino
Copy code
http://localhost:8080
For AWS EC2 deployment:

arduino
Copy code
http://ec2-xx-xxx-xxx-xxx.compute-1.amazonaws.com
Authentication Endpoints
1. Register a New User
Endpoint: /register

Method: POST

Request URL:

bash
Copy code
http://localhost:8080/register
Headers:

Key	Value
Content-Type	application/json
Request Body:

json
Copy code
{
  "username": "john_doe",
  "password": "password123"
}
Response:

201 Created: If registration is successful.
400 Bad Request: If the username is already taken.
Example Response:

json
Copy code
{
  "message": "User registered successfully."
}
2. User Login
Endpoint: /login

Method: POST

Request URL:

bash
Copy code
http://localhost:8080/login
Headers:

Key	Value
Content-Type	application/x-www-form-urlencoded
Request Body (Form-Encoded):

username: The registered username.
password: The registered password.
Key	Value
username	john_doe
password	password123
Response:

200 OK: If login is successful.
401 Unauthorized: If the credentials are invalid.
Task Management Endpoints
1. Create a New Task
Endpoint: /user/tasks

Method: POST

Request URL:

bash

http://localhost:8080/user/tasks
Headers:
Key	Value
Content-Type	application/json
Request Body:
json

{
  "name": "New Task",
  "description": "This is a new task to complete.",
  "completed": false,
  "taskDateTime": "2024-10-01T15:00:00"
}

Example Response:

json
{
  "id": 1,
  "name": "New Task",
  "description": "This is a new task to complete.",
  "completed": false,
  "taskDateTime": "2024-10-01T15:00:00",
  "user": {
    "username": "john_doe"
  }
}

2. Fetch All Tasks
Endpoint: /user/tasks
Method: GET
Request URL:

bash

http://localhost:8080/user/tasks

Example Response:

json

[
  {
    "id": 1,
    "name": "New Task",
    "description": "This is a new task to complete.",
    "completed": false,
    "taskDateTime": "2024-10-01T15:00:00",
    "formattedTaskDateTime": "2024-10-01 15:00",
    "user": {
      "username": "john_doe"
    }
  }
]
