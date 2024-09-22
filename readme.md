# Stripe Manager
## Overview
This project is a simple stripe wrapper for common general stripe operations, e.g managing products, prices, invoice items, and invoices

## Features
- **Product Management :**  create and add products .
- **Price Management :**  Send emails to many recipients in a single action.
- **Invoice Items Management :**  Send emails to many recipients in a single action.
- **Invoice Management :**  Send emails to many recipients in a single action.
- **Balance Management :**  Send emails to many recipients in a single action.
- **Transactions Management :**  Send emails to many recipients in a single action.
- **Event Management :**  Send emails to many recipients in a single action.

## Technologies Used

#### languages
- **java 21 :** The programming language used for developing the application.
#### framework
- ** Spring Version :** `Spring 3.2.8` The framework used to build and run the application.
- **Spring Web :** Used to implement stateless URLs in web applications.
- **aspect :** Designed to handle multiple parts of an application, such as logging .
- **actuator :** using to monitoring application .
- **swagger :** use to implement documentation to your project

#### tools
- **Maven :** The build automation tool used for managing project dependencies and building the application.
- **SMTP Server :** Sends emails using Google’s SMTP server with your personal email.

#### tech
- **Exeption handler :** Provides custom error messages for different types of exceptions in one place.
- ** `.env` secret management :** use to secret the sensitive information
- **yml File :** the project use YAML file to handle Configuration



## Prerequisites
- **java 21**
- **Maven**
- **spring boot**
- **SMTP Server**



## Before Running the Project

To run the project, you need to configure an email SMTP server. For this demo, we are using Google SMTP.

### 1- Creating an SMTP Server with Google

1. Go to [this link ](https://myaccount.google.com/apppasswords) and log in with your Google account.
2. If you are informed that your account is unavailable for this service, follow these steps:
    - Go to your account settings.
    - Enable "2-Step Verification."

This will allow you to create an App Password for your SMTP configuration.
##### 3- after log in with google you tell you  "name application "
##### 4- after write name  application ( any name )and click `create `
##### 5- now you must see you APP Passwords
![image](https://github.com/user-attachments/assets/b1ea14df-fb8f-4970-a8e2-a3c9aaf7e7c3)

### 2- Create a `.env` File for Sensitive Security Information
```yml
HOST=smtp.gmail.com
PORT=smtp_port
USERNAME=your_email_address_setup_with_provider
PASSWORD=your_generated_password
```
#### the default ports are as follows:
- **Port `587` ** : For TLS/STARTTLS (recommended for most email clients).
- **Port `465` ** : For SSL (less commonly used).
#### HOST
-  If you are using Google SMTP, do not change this value: `smtp.gmail.com`.

#### USERNAME & PASSWORD

- **USERNAME :** Enter the email address you used to create the App Password.
- **PASSWORD :** Enter the password that you generated above.


### 3- now you ready to run application

## Running the Project

- In this project, I used `IntelliJ`.
- To run the application, go to the class `SenderEmailApplication` and click `Run`.

## After Running the Project

### How to Interact with the Project

There are two ways to interact with the project to send emails to multiple users:

1. **Swagger**
2. **Postman**

In this demo, we will explain how to interact with the project using `Swagger` .

##### To access Swagger, use the following link: [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/).

- you will see only one endPoint `POST`
 ```Plantext
 http://localhost:8080/email/send-email 
 ```
This endpoint allows you to send an email to one or more recipients. Here’s how it works:


### Request Body

The request body must include the following fields:

- **`to`**: List of recipient email addresses .
    - *At least one email address is required.*
    - *Email addresses must follow a valid email format.*
- **`subject`**: The subject of the email.
    - *This field must not be null.*
- **`body`**: The content of the email.
    - *This field must not be null.*

### Error Handling

If any of the following fields are null or empty:
- `to`
- `subject`
- `body`

The server will return a `400 Bad Request` error with an appropriate exception message.

## Test Examples


#### First Test

```yml
{
  "body": " this is body",
  "subject": "subject of email",
  "to": [
    "mo********4@gmil.com"
  ]
}
```
#### Request

```yml
{
  "body": {
    "body": " this is body",
    "subject": "subject of email",
    "to": [
      "mo********4@gmil.com"
    ]
  },
  "message": "Email has been sent successfully ",
  "statusCode": "ACCEPTED"
}
```

### secound test
##### with body empty

```yml
{
  "body": "    ",
  "subject": " this is a subject",
  "to": [
    "mo********4@gmil.com"
  ]
}
```
#### Response
```yml
{
  "body": null,
  "message": "Error: The body of the email is empty.",
  "statusCode": "BAD_REQUEST"
}
```

### thired test
#### with subject empty
```yml
{
  "body": " this is a body    ",
  "subject": "     ",
  "to": [
    "mo********4@gmil.com"
  ]
}
```
#### Response
```yml
{
  "body": null,
  "message": "Error: The subject of the email is empty.",
  "statusCode": "BAD_REQUEST"
}
```

### fourth test
##### with incorrect email structure
```yml
{
  "body": " this is a body    ",
  "subject": " this is a subject    ",
  "to": [
    "Team Zero "
  ]
}
```

#### Response
```yml
{
  "body": null,
  "message": "Error: One or more recipient email addresses are invalid.",
  "statusCode": "BAD_REQUEST"
}
```


