## Overview

This repository contains the backend code for the Learner Application, a Spring Boot based application that provides user authentication, user management, post management, category management, and comment management functionalities.

## API Endpoints

### Authentication Endpoints

#### Login
- **URL:** `/api/v1/auth/login`
- **Method:** `POST`
- **Description:** Authenticates a user and returns a JWT token.
- **Request Body:**
  ```json
  {
    "userName": "string",
    "password": "string"
  }
  ```
- **Response:**
  ```json
  {
    "token": "token is : {JWT_TOKEN}"
  }
  ```

#### Register
- **URL:** `/api/v1/auth/register`
- **Method:** `POST`
- **Description:** Registers a new user.
- **Request Body:**
  ```json
  {
    "name": "string",
    "email": "string",
    "password": "string",
    "about": "string"
  }
  ```
- **Response:**
  ```json
  {
    "id": "integer",
    "name": "string",
    "email": "string",
    "password": "string",
    "about": "string"
  }
  ```

### User Endpoints

#### Create User
- **URL:** `/api/users`
- **Method:** `POST`
- **Description:** Creates a new user.
- **Request Body:**
  ```json
  {
    "name": "string",
    "email": "string",
    "password": "string",
    "about": "string"
  }
  ```
- **Response:**
  ```json
  {
    "id": "integer",
    "name": "string",
    "email": "string",
    "password": "string",
    "about": "string"
  }
  ```

#### Update User
- **URL:** `/api/users/{userId}`
- **Method:** `PUT`
- **Description:** Updates an existing user.
- **Request Body:**
  ```json
  {
    "name": "string",
    "email": "string",
    "password": "string",
    "about": "string"
  }
  ```
- **Response:**
  ```json
  {
    "id": "integer",
    "name": "string",
    "email": "string",
    "password": "string",
    "about": "string"
  }
  ```

#### Delete User
- **URL:** `/api/users/{userId}`
- **Method:** `DELETE`
- **Description:** Deletes an existing user. Only accessible by users with ADMIN role.
- **Response:**
  ```json
  {
    "message": "User Deleted successfully",
    "success": true
  }
  ```

#### Get All Users
- **URL:** `/api/users`
- **Method:** `GET`
- **Description:** Retrieves all users.
- **Response:**
  ```json
  [
    {
      "id": "integer",
      "name": "string",
      "email": "string",
      "password": "string",
      "about": "string"
    }
  ]
  ```

#### Get Single User
- **URL:** `/api/users/{userId}`
- **Method:** `GET`
- **Description:** Retrieves a user by ID.
- **Response:**
  ```json
  {
    "id": "integer",
    "name": "string",
    "email": "string",
    "password": "string",
    "about": "string"
  }
  ```

### Post Endpoints

#### Create Post
- **URL:** `/api/user/{userId}/category/{categoryId}/posts`
- **Method:** `POST`
- **Description:** Creates a new post for a user in a specific category.
- **Request Body:**
  ```json
  {
    "title": "string",
    "content": "string",
    "imageName": "string"
  }
  ```
- **Response:**
  ```json
  {
    "id": "integer",
    "title": "string",
    "content": "string",
    "imageName": "string",
    "userId": "integer",
    "categoryId": "integer"
  }
  ```

#### Get Posts by User
- **URL:** `/api/user/{userId}/posts`
- **Method:** `GET`
- **Description:** Retrieves all posts by a specific user.
- **Response:**
  ```json
  [
    {
      "id": "integer",
      "title": "string",
      "content": "string",
      "imageName": "string",
      "userId": "integer",
      "categoryId": "integer"
    }
  ]
  ```

#### Get Posts by Category
- **URL:** `/api/category/{categoryId}/posts`
- **Method:** `GET`
- **Description:** Retrieves all posts in a specific category.
- **Response:**
  ```json
  [
    {
      "id": "integer",
      "title": "string",
      "content": "string",
      "imageName": "string",
      "userId": "integer",
      "categoryId": "integer"
    }
  ]
  ```

#### Get All Posts
- **URL:** `/api/posts`
- **Method:** `GET`
- **Description:** Retrieves all posts with pagination and sorting.
- **Request Params:**
  - `pageNumber` (default: 0
