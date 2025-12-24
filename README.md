# Blog Platform with OAuth2 Authentication

**Secure, production-oriented blog backend built with Spring Boot and OAuth2**

This project is a backend-focused blog platform that demonstrates how to design a **secure, authentication-driven REST API** using Spring Boot, OAuth2, and PostgreSQL.

The application emphasizes **clean architecture, secure authentication, and proper separation of concerns**, rather than frontend complexity.

---

## Overview

The Blog Platform allows users to create, update, delete, and view blog posts and comments.

Authentication and authorization are handled using **OAuth2 with an external identity provider** (such as Auth0), ensuring secure login, token management, and session handling.

All sensitive endpoints are protected using **Spring Security**, and data is persisted in **PostgreSQL** via Spring Data JPA.

---

## Key Features

- OAuth2 authentication via external identity provider (Auth0)
- Secure endpoint protection with Spring Security
- CRUD operations for users, posts, and comments
- Role-based access control
- PostgreSQL persistence with JPA & Hibernate
- Clean layered architecture (Controller / Service / Repository)
- BCrypt password hashing
- Custom logout handling

---

## Technology Stack

- **Backend:** Spring Boot
- **Security:** Spring Security, OAuth2, JWT
- **Database:** PostgreSQL
- **ORM:** Spring Data JPA (Hibernate)
- **Authentication Provider:** Auth0
- **Build Tool:** Maven / Gradle
- **Testing:** Spring Boot Starter Test

---

## Security Design

- OAuth2 login flow using an external identity provider
- JWT-based authentication for API access
- BCrypt password hashing for secure credential storage
- Protected endpoints via Spring Security configuration
- Custom logout handler for proper session termination with Auth0

---

## Project Structure

controller/
├─ AuthController
├─ PostController
└─ CommentController

service/
├─ AuthService
├─ UserService
├─ PostService
└─ CommentService

model/
├─ User
├─ Post
└─ Comment

repository/
├─ UserRepository
├─ PostRepository
└─ CommentRepository

markdown
Kodu kopyala

---

## Domain Models

- **User**
  - Represents platform users and authentication-related data
- **Post**
  - Represents blog posts with content, metadata, and timestamps
- **Comment**
  - Represents comments on posts, including approval status and parent-child relations

---

## API Endpoints

### Authentication
- `POST /api/auth/login` – Authenticate user
- `POST /api/auth/register` – Register new user
- `POST /api/auth/logout` – Logout user

### Posts
- `GET /api/posts` – Retrieve all blog posts
- `GET /api/posts/{id}` – Retrieve post by ID
- `POST /api/posts` – Create a new blog post
- `PUT /api/posts/{id}` – Update an existing post
- `DELETE /api/posts/{id}` – Delete a blog post

### Comments
- `GET /api/comments` – Retrieve all comments
- `POST /api/comments` – Create a new comment
- `PUT /api/comments/{id}` – Update an existing comment
- `DELETE /api/comments/{id}` – Delete a comment

---

## Setup & Installation

### Prerequisites

- Java 17 or later
- PostgreSQL
- OAuth2 Provider (e.g. Auth0)

### Configuration

Update `application.yml` with:
- OAuth2 client ID and client secret
- PostgreSQL connection details

### Run the Application

```bash
git clone https://github.com/your-username/blog-platform.git
cd blog-platform

mvn clean install
mvn spring-boot:run
or with Gradle:

bash
Kodu kopyala
./gradlew bootRun
Access
The application runs at:

arduino
Kodu kopyala
http://localhost:8080
Engineering Focus
This project demonstrates experience in:

Secure REST API design

OAuth2-based authentication flows

Spring Security configuration

Clean backend architecture

Relational data modeling with JPA

Real-world authentication and authorization patterns

License
MIT License
