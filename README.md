## User-Service
User Service REST API

## Author
Arnab Mondal

### This Rest API supports the following endpoints

|   Endpoint      | HTTP Method | Description                          |  Access |       Comments      |
|-----------------|-------------|--------------------------------------|---------|---------------------|
|/users           | GET         | List of all users                    | GENERAL |                     |
|/users/{user_id} | GET         | Get user by id                       | GENERAL | Login, view profile |
|/users/{user_id} | POST        | Add a user                           | GENERAL | User Registration   |
|/users/{user_id} | DELETE      | Delete a user                        | ADMIN   |                     |
|/users/{user_id} | PUT         | Update a user                        | GENERAL | Update user profile |
