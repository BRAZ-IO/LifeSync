# LifeSync API Documentation

## 📖 Overview

This document describes the LifeSync API - a comprehensive fitness tracking and workout planning system with AI-powered recommendations.

## 🚀 Quick Start

### Accessing the API Documentation

1. **Development Environment:**
   - Swagger UI: `http://localhost:8080/api/swagger-ui.html`
   - OpenAPI JSON: `http://localhost:8080/api/v3/api-docs`

2. **Production Environment:**
   - Swagger UI: `https://api.lifesync.com/api/swagger-ui.html`
   - OpenAPI JSON: `https://api.lifesync.com/api/v3/api-docs`

### Authentication

The API uses JWT (JSON Web Token) for authentication. Include the token in the Authorization header:

```bash
Authorization: Bearer <your-jwt-token>
```

## 📚 API Groups

### 🔐 Authentication API
- **Base Path:** `/api/auth`
- **Endpoints:** User registration, login, token management
- **Authentication:** Not required for registration and login

### 👤 Users API
- **Base Path:** `/api/users`
- **Endpoints:** User profile management
- **Authentication:** Required

### 📈 Habits API
- **Base Path:** `/api/habits`
- **Endpoints:** Habit tracking and completion
- **Authentication:** Required

### 💪 Workouts API
- **Base Path:** `/api/workouts`
- **Endpoints:** Workout management and planning
- **Authentication:** Required

### ⏱️ Sessions API
- **Base Path:** `/api/sessions`
- **Endpoints:** Workout session tracking
- **Authentication:** Required

### 📊 Progress API
- **Base Path:** `/api/progress`
- **Endpoints:** Progress tracking and analytics
- **Authentication:** Required

### 🤖 AI Coach API
- **Base Path:** `/api/ai`
- **Endpoints:** AI-powered recommendations and coaching
- **Authentication:** Required

## 🔧 Development Features

### Enhanced Swagger UI

The development environment includes enhanced features:

- **Try It Out:** Test endpoints directly from the browser
- **Syntax Highlighting:** Improved JSON/XML formatting
- **Request Duration:** Display response times
- **Detailed Examples:** Comprehensive request/response examples
- **Multiple Themes:** Various syntax highlighting themes

### API Groups

Endpoints are organized into logical groups for better navigation:

- **Auth API:** Authentication endpoints
- **Users API:** User management
- **Habits API:** Habit tracking
- **Workouts API:** Workout planning
- **Sessions API:** Session tracking
- **Progress API:** Analytics
- **AI API:** AI coaching

## 🏭 Production Features

### Security

- **Try It Out Disabled:** Prevents direct testing in production
- **Reduced Information:** Hides sensitive implementation details
- **Rate Limiting:** 1000 requests/hour per user
- **HTTPS Only:** Enforced secure connections

### Performance

- **Caching Enabled:** Improved response times
- **Minimal UI:** Faster loading
- **Optimized Responses:** Reduced payload sizes

## 📝 Response Format

All API responses follow a consistent format:

### Success Response
```json
{
  "data": { ... },
  "message": "Operation successful",
  "timestamp": "2024-01-01T12:00:00Z"
}
```

### Error Response
```json
{
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "Invalid input data",
    "details": { ... }
  },
  "timestamp": "2024-01-01T12:00:00Z"
}
```

## 🔄 Versioning

The API follows semantic versioning:

- **Current Version:** v2.0.0
- **Version Format:** `MAJOR.MINOR.PATCH`
- **Backward Compatibility:** Maintained within major versions

### Version Information

- **Development:** v2.0.0-SNAPSHOT
- **Staging:** v2.0.0-rc.1
- **Production:** v2.0.0

## 🛠️ Configuration

### Environment Variables

```yaml
# Swagger Configuration
springdoc:
  api-docs:
    enabled: true
    path: /v3/api-docs
  swagger-ui:
    enabled: true
    path: /swagger-ui.html
```

### Profile-Specific Settings

- **Development:** Full features enabled
- **Staging:** Production-like settings
- **Production:** Security-focused configuration

## 📞 Support

For API-related questions:

- **Email:** api-support@lifesync.com
- **Documentation:** https://docs.lifesync.com
- **GitHub:** https://github.com/lifesync/api
- **Twitter:** @lifesync_api

## 📄 License

MIT License - see LICENSE file for details.

---

**Note:** This documentation is automatically generated from the OpenAPI specification. For the most up-to-date information, always refer to the live Swagger UI.
