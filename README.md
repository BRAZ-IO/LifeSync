# LifeSync - AI Fitness Coach

LifeSync is your personal AI fitness coach, designed to guide you through every workout. Get customized training plans, track your progress, and stay motivated to reach your fitness goals faster and smarter.

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Maven 3.6+
- Node.js 16+ (for frontend)

### Backend Setup
```bash
cd backend
mvn spring-boot:run
```

The backend will start on `http://localhost:8080/api`

### Frontend Setup
```bash
cd frontend
npm install
npm run dev
```

The frontend will start on `http://localhost:5173`

## 📱 Features

- **Habit Management** - Create, track, and manage fitness habits
- **Progress Tracking** - Monitor your workout progress
- **User Authentication** - Secure login and registration
- **REST API** - Full CRUD operations for habits and users

## 🏗️ Architecture

**Backend (Spring Boot)**
- Java 17 with Spring Boot 3.x
- H2 Database (in-memory for development)
- Spring Data JPA for data persistence
- RESTful API with validation

**Frontend (React)**
- React 18 with TypeScript
- Modern UI components
- Axios for API communication

## 📊 API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - User login
- `POST /api/auth/logout` - User logout

### Habits
- `GET /api/habits` - Get all habits
- `POST /api/habits` - Create new habit
- `PUT /api/habits/{id}` - Update habit
- `DELETE /api/habits/{id}` - Delete habit
- `POST /api/habits/{id}/complete` - Mark habit as complete

### Users
- `GET /api/users/{id}` - Get user by ID
- `PUT /api/users/{id}` - Update user profile

## 🛠️ Development

### Database
- **Development**: H2 in-memory database
- **Console**: http://localhost:8080/api/h2-console
- **Credentials**: Username `sa`, Password (blank)

### API Documentation
- **Swagger UI**: http://localhost:8080/api/swagger-ui.html

## 📝 Project Status

### ✅ Completed
- Backend API with Spring Boot
- User authentication system
- Habit CRUD operations
- Database setup with H2
- API documentation

### 🚧 In Progress
- Frontend React application
- UI components for habit management
- Authentication flow in frontend

### 📋 Upcoming
- Advanced habit analytics
- Mobile app development
- AI-powered workout recommendations

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.

## 📞 Support

For questions or support, please open an issue on GitHub.
