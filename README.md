# 🏦 Banking Application

A simple banking application built with **Java, Spring Boot, and Microservices** architecture.  
The system provides user management, account services, authentication, and transaction handling.  
It follows a layered architecture (Controller → Service → Repository) and connects to a database for persistent storage.

---

## 🚀 Features

- 👤 **User Service**: Manage user details, create accounts  
- 💳 **Account Service**: Account creation, activation, deactivation  
- 🔐 **Authentication Service**: Secure login and user authentication with Spring Security  
- 💸 **Transaction Service**: Deposit, withdraw, and transfer between accounts  
- 🛡️ **Security**: Role-based authentication and authorization  
- 🗄️ **Database Integration**: Persistent storage using relational databases  

---

## 🏗️ Tech Stack

- **Backend**: Java, Spring Boot  
- **Security**: Spring Security  
- **Database**: MySQL (or PostgreSQL)  
- **Architecture**: Microservices (User, Account, Authentication, Transaction)  
- **Build Tool**: Maven/Gradle  

---

## 📂 Project Structure
banking-application/

│── user-service/ # Handles user details and profiles

│── account-service/ # Manages account lifecycle

│── auth-service/ # Authentication & authorization

│── transaction-service/ # Deposit, withdrawal, transfers

│── common/ # Shared DTOs, configs
