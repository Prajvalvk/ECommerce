🛒 E-Commerce Management System

📌 Project Overview

The E-Commerce Management System is a RESTful backend application developed using Java and Spring Boot to manage the core operations of an online shopping platform. It provides APIs for user registration and login, product and category management, shopping cart operations, order processing, payments, and order tracking.

The application follows a layered architecture and uses PostgreSQL for persistent data storage. REST APIs can be tested using Postman.

🚀 Features

- User Management: Create, retrieve, update, and delete user records.
- Authentication: User registration and login with password hashing.
- Product Management: Manage products and product categories.
- Address Management: Store and manage user addresses.
- Shopping Cart: Add products, update quantities, view cart items, and remove products.
- Order Management: Create and manage customer orders.
- Payment Management: Maintain payment details.
- Order Tracking: Track order status and delivery progress.
- Checkout: Process orders, calculate totals, create payment and tracking records, and clear the cart.
- Admin APIs: Retrieve users, orders, payments, products, and categories.

🛠️ Tech Stack

- Language: Java 17
- Backend Framework: Spring Boot 3.3.4
- ORM: Spring Data JPA, Hibernate
- Database: PostgreSQL
- Build Tool: Maven
- Validation: Jakarta Bean Validation
- Libraries: Lombok
- API Testing: Postman

🏗️ Architecture

The project follows a layered architecture:

"Controller → Service → Repository → Database"

- Controller Layer: Handles HTTP requests and responses.
- Service Layer: Implements business logic.
- Repository Layer: Performs database operations.
- Entity Layer: Represents database tables.
- DTO Layer: Transfers request and response data.
- Exception Handling: Provides centralized API error handling.

⚙️ Getting Started

1. Clone the repository.
2. Configure your PostgreSQL database.
3. Update the database connection settings in "application.properties" or configure the supported environment variables.
4. Open the project in your preferred Java IDE.
5. Build and run the application using Maven:

mvn clean install
mvn spring-boot:run

The application is configured to run on:

"http://localhost:8080"

🧪 API Testing

Use Postman to test the REST endpoints for authentication, products, categories, addresses, shopping cart, checkout, orders, payments, and tracking.

🎯 Learning Outcomes

- Building RESTful APIs using Spring Boot.
- Implementing CRUD operations with Spring Data JPA.
- Designing entity relationships and database schemas.
- Integrating PostgreSQL with a Java application.
- Implementing password hashing and request validation.
- Managing transactions and exception handling.
- Developing an end-to-end e-commerce backend workflow.

🔐 Security Note

This project uses password hashing but does not currently implement Spring Security, token-based authentication, or role-based authorization. Admin endpoints are not protected, so additional security measures are required before production deployment.

👨‍💻 Author

Prajval V K

A Java and Spring Boot project demonstrating backend development, database integration, and REST API design.
