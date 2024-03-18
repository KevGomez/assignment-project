# Project Title

Java Rest API Project for E-Commerce Client Data Maintenance and Retrieval

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)

## Installation

1. Need to install mysql database and adjust the configs under properties file
2. Need to install Redis Caching Server and adjust the configs under properties file

## Usage

1. The project gets up on 9090 port and can be tested through Postman or any other API clients
2. Need to get the auth token before using the main rest API
   
## Endpoints

1. http://localhost:9090/springService/api/auth/register [POST] - To Register a User Login
   Request Body:

   {
    "username": "admin",
    "password": "admin"
   }

2. http://localhost:9090/springService/api/auth/login [POST] - To Authenticate a token through user login
   Request Body:

   {
    "username": "admin",
    "password": "admin"
   }

3. http://localhost:9090/springService/api/main/product-metadata [POST] - To Save a new Product
   Request Header: Authorization: Bearer <Token>
   Request Body:

   {
    "productId": "FD-2144746766",
    "category": "Fadies",
    "brand": "Fadyom"
   }

4. http://localhost:9090/springService/api/main/shop-metadata [POST] - To save a new Shopper
   Request Header: Authorization: Bearer <Token>
   Request Body:

   {
    "shopperId": "S-1004"
   }

5. http://localhost:9090/springService/api/main/personalized-data [POST] - To save personalized data based on Shopper
   Request Header: Authorization: Bearer <Token>
   Request Body:

   {
    "shopperID": "S-1002",
    "shelf": [Shelf Data as per the doc]
   }

6. http://localhost:9090/springService/api/main/products?shopperId=S-1003&category=&brand=&limit= [GET] - To search personlized products with filters
   Request Header: Authorization: Bearer <Token>
