# 🏃‍♂️ LifeSync - AI Fitness Coach

<div align="center">

[![Java Version](https://img.shields.io/badge/Java-17+-orange.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Build Status](https://img.shields.io/badge/Build-Passing-brightgreen.svg)]()

**Enterprise-grade AI-powered fitness coaching platform for personalized workout planning and progress tracking**

[📖 Documentation](#-documentation) • [🚀 Quick Start](#-quick-start) • [📊 API](#-api-documentation) • [🏗️ Architecture](#️-architecture)

</div>

## 📋 Overview

LifeSync is a comprehensive **AI Fitness Coach** platform that leverages artificial intelligence to provide personalized workout plans, track fitness progress, and deliver intelligent coaching recommendations. Built with enterprise-grade architecture and following industry best practices.

### 🎯 Key Features

- 🤖 **AI-Powered Coaching**: Intelligent workout recommendations and personalized training plans
- 📊 **Advanced Analytics**: Comprehensive progress tracking with trend analysis
- 🎯 **Habit Management**: Science-based habit formation and tracking system
- 🔐 **Enterprise Security**: JWT-based authentication with role-based access control
- 📱 **Mobile-Ready**: RESTful API optimized for mobile applications
- 🏗️ **Scalable Architecture**: Microservices-ready with Spring Boot 3.x
- 📚 **Rich Documentation**: Comprehensive API docs with Swagger/OpenAPI 3.0

## 🚀 Quick Start

### Prerequisites

- **Java 17+** - OpenJDK or Oracle JDK
- **Maven 3.6+** - Build tool and dependency management
- **Docker** - Optional, for containerized deployment
- **PostgreSQL** - Production database (H2 for development)

### Installation

```bash
# Clone the repository
git clone https://github.com/BRAZ-IO/LifeSync.git
cd LifeSync

# Backend setup
cd backend
mvn clean install
mvn spring-boot:run

# Application will be available at http://localhost:8080/api
```

### Docker Deployment (Optional)

```bash
# Build and run with Docker
docker-compose up -d

# Access the application
curl http://localhost:8080/api/actuator/health
```

## 📊 API Documentation

### Interactive Documentation

- **Swagger UI**: [http://localhost:8080/api/swagger-ui.html](http://localhost:8080/api/swagger-ui.html)
- **OpenAPI JSON**: [http://localhost:8080/api/v3/api-docs](http://localhost:8080/api/v3/api-docs)
- **API Guide**: See [API_DOCUMENTATION.md](backend/API_DOCUMENTATION.md)

### Authentication

The API uses **JWT Bearer Token** authentication:

```bash
# Login and get token
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"user@example.com","password":"password"}'

# Use token for authenticated requests
curl -X GET http://localhost:8080/api/users/profile \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Core Endpoints

| Module | Base Path | Description |
|--------|-----------|-------------|
| 🔐 Authentication | `/api/auth` | User registration, login, token management |
| 👤 Users | `/api/users` | Profile management and user operations |
| 📈 Habits | `/api/habits` | Habit tracking and completion |
| 💪 Workouts | `/api/workouts` | Workout planning and management |
| ⏱️ Sessions | `/api/sessions` | Workout session tracking |
| 📊 Progress | `/api/progress` | Analytics and progress tracking |
| 🤖 AI Coach | `/api/ai` | AI-powered recommendations |

## 🏗️ Architecture

### Technology Stack

**Backend**
- **Framework**: Spring Boot 3.2.0 with Java 17
- **Security**: Spring Security with JWT authentication
- **Database**: H2 (development) / PostgreSQL (production)
- **ORM**: Spring Data JPA with Hibernate
- **Documentation**: SpringDoc OpenAPI 3.0
- **Validation**: Jakarta Bean Validation
- **Testing**: JUnit 5, Mockito, Spring Test

**Infrastructure**
- **Build Tool**: Maven 3.6+
- **Containerization**: Docker support
- **Monitoring**: Spring Boot Actuator
- **Logging**: Logback with structured logging
- **Profiles**: Multi-environment configuration (dev/staging/prod)

### System Design

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Frontend      │    │   Backend API   │    │   Database      │
│   (React TS)    │◄──►│  (Spring Boot)  │◄──►│  (PostgreSQL)   │
│                 │    │                 │    │                 │
│ - User Interface│    │ - REST API      │    │ - User Data     │
│ - State Mgmt    │    │ - JWT Auth      │    │ - Workouts      │
│ - API Client    │    │ - AI Services   │    │ - Progress      │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Database Schema

- **Users**: Authentication and profile data
- **Habits**: Daily fitness habits and completions
- **Workouts**: Exercise library and workout plans
- **Sessions**: Tracked workout sessions
- **Progress**: Measurements and analytics

## 🛠️ Development

### Environment Setup

```bash
# Development profile (default)
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Production profile
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

### Database Configuration

**Development (H2)**
- Console: [http://localhost:8080/api/h2-console](http://localhost:8080/api/h2-console)
- Username: `sa`
- Password: (blank)

**Production (PostgreSQL)**
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/lifesync
    username: ${DB_USERNAME:lifesync}
    password: ${DB_PASSWORD:password}
```

### Testing

```bash
# Run all tests
mvn test

# Run with coverage
mvn test jacoco:report

# Integration tests
mvn test -P integration-test
```

## � Project Status

### ✅ Completed Features

- **Backend API**: Complete REST API with 70+ endpoints
- **Authentication System**: JWT-based security with token management
- **AI Coaching**: Intelligent workout recommendations and analysis
- **Habit Tracking**: Science-based habit formation system
- **Progress Analytics**: Comprehensive tracking and trend analysis
- **Database Design**: Optimized schema with proper relationships
- **API Documentation**: Enterprise-grade Swagger/OpenAPI documentation
- **Multi-environment**: Dev/staging/production configurations

### 🚧 In Development

- **Frontend Application**: React TypeScript interface
- **Mobile App**: React Native cross-platform application
- **Advanced AI**: Machine learning model integration
- **Real-time Features**: WebSocket support for live updates

### 📋 Roadmap

- **Q2 2024**: Frontend completion and mobile app beta
- **Q3 2024**: Advanced AI features and ML integration
- **Q4 2024**: Microservices architecture migration
- **Q1 2025**: Enterprise features and SaaS platform

## 🔧 Configuration

### Environment Variables

```bash
# Database Configuration
DB_USERNAME=lifesync
DB_PASSWORD=your_password
DB_HOST=localhost
DB_PORT=5432
DB_NAME=lifesync

# JWT Configuration
JWT_SECRET=your_super_secret_key
JWT_EXPIRATION=86400000

# API Configuration
API_BASE_URL=https://api.lifesync.com
CORS_ORIGINS=https://lifesync.com
```

### Profiles

- **dev**: Development with H2 database and verbose logging
- **staging**: Pre-production with PostgreSQL and limited features
- **prod**: Production optimized with security hardening

## 🤝 Contributing

We welcome contributions! Please follow our guidelines:

### Development Workflow

1. **Fork** the repository
2. **Create** a feature branch: `git checkout -b feature/amazing-feature`
3. **Commit** your changes: `git commit -m 'feat: Add amazing feature'`
4. **Push** to the branch: `git push origin feature/amazing-feature`
5. **Open** a Pull Request

### Code Standards

- Follow **Java Code Conventions**
- Use **Semantic Versioning** for commits
- Add **unit tests** for new features
- Update **documentation** for API changes
- Ensure **CI/CD** pipeline passes

### Reporting Issues

- **Bug Reports**: Use the issue template with reproduction steps
- **Feature Requests**: Describe the use case and expected behavior
- **Security Issues**: Report privately to maintainers


## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.




