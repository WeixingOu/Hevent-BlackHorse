<p align="center">
  <a href="#">
    <picture>
      <img src="/hevent-fe/src/assets/img/logo3.png" width=450 />
    </picture>
  </a>
</p>
<p align="center">
  
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0-brightgreen.svg)
![Vue 3](https://img.shields.io/badge/Vue_3-3.2.x-brightgreen.svg)
![TypeScript](https://img.shields.io/badge/TypeScript-4.1-blue.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)
![JPA](https://img.shields.io/badge/JPA-2.2-blue.svg)
![Redis](https://img.shields.io/badge/Redis-6.0-red.svg)
![JWT](https://img.shields.io/badge/JWT-Security-orange.svg)
![Validation](https://img.shields.io/badge/Validation-2.0-lightgrey.svg)
![JUnit](https://img.shields.io/badge/JUnit-5-orange.svg)
![Cloudinary](https://img.shields.io/badge/Cloudinary-Image_Management-yellowgreen.svg)
![Email SMTP](https://img.shields.io/badge/Email_SMTP-Integration-lightgrey.svg)
![Pinia](https://img.shields.io/badge/Pinia-State_Management-ff69b4.svg)
![Vue-Router](https://img.shields.io/badge/Vue_Router-4.0-lightgrey.svg)

</p>

> This full-stack application is a cutting-edge example of integrating modern back-end and front-end technologies to create a responsive, scalable, and secure online platform.

## Table of Contents

- [Technologies and Tools](#technologies-and-tools)
- [Project Structure](#project-structure)
- [Features](#features)
- [Development Practices](#development-practices)

## Technologies and Tools

This project uses a powerful combination of technologies and tools to ensure robust application development and easy maintenance:

- **Spring Boot**: Simplifies the bootstrapping and development of new Spring applications.
- **MySQL & JPA**: Handles relational data management with an object-relational mapping approach.
- **Redis**: Provides high performance data caching to enhance responsiveness.
- **JWT**: Secures sessions and API access through JSON Web Tokens.
- **Validation**: Ensures robust backend validation to maintain data integrity.
- **JUnit**: Facilitates extensive testing to ensure application reliability.
- **Vue 3 & TypeScript**: Creates an engaging and responsive user interface.
- **Pinia**: Manages state for Vue applications to improve UI consistency.
- **Vue-Router**: Handles in-app routing capabilities for a single-page application feel.
- **Cloudinary**: Manages media assets in the cloud, reducing hosting bandwidth and improving load times.
- **Email SMTP**: Integrates email sending functionalities for notifications and user communications.

## Project Structure

The project is divided into two main parts: the backend and the frontend.

### Backend

Developed using Spring Boot, the backend is configured to interact seamlessly with MySQL databases through JPA and utilize Redis for caching. Security features are implemented with JWT for authentication processes.

```bash
cd backend
./mvnw spring-boot:run
```

### Frontend

The frontend features Vue 3 combined with TypeScript, offering a modern, modular approach to building rich user interfaces. It also includes Vue-Router for navigation and Pinia for state management.

```bash
cd hevent-fe
npm install
npm run serve
```

## Features

- **Secure Authentication System**: Utilizing JWT for secure login processes ensuring that all user data remains confidential.
- **Dynamic Data Presentation**: Interactive components that display data in real-time, leveraging Vue's reactive capabilities.
- **Caching Mechanisms**: Implementing Redis to reduce latency and load times, enhancing the user experience.
- **Comprehensive Unit and Integration Tests**: Using JUnit to ensure that each component functions correctly and integrates perfectly with other parts of the application.
- **Responsive Design**: Mobile-first design approach, ensuring the application is usable on various devices and screen sizes.

## Development Practices

- **Continuous Integration/Deployment**: Using tools like Jenkins and Docker for automated testing and deployment.
- **Code Quality Assurance**: Implementing ESLint and Prettier in the development process to ensure that all code meets industry standards.
- **Scalable Architecture**: Designing the application to handle increases in load gracefully, ensuring long-term sustainability.
