# LifeSync - AI Fitness Coach

LifeSync is your personal AI fitness coach, designed to guide you through every workout. Get customized training plans, track your progress, and stay motivated to reach your fitness goals faster and smarter.

## �️ Architecture

### Technology Stack

**Backend**:
- **Spring Boot 3.x** - Java framework for REST API development
- **Spring Data JPA** - Database ORM and repository pattern
- **Spring Security** - Authentication and authorization
- **PostgreSQL/MySQL** - Relational database for data persistence
- **Maven** - Dependency management and build tool

**Frontend**:
- **React 18** - Modern JavaScript library for UI development
- **TypeScript** - Type-safe JavaScript development
- **Tailwind CSS** - Utility-first CSS framework
- **React Router** - Client-side routing
- **Axios** - HTTP client for API communication
- **Vite** - Fast build tool and development server

**Infrastructure**:
- **RESTful API** - Standard HTTP-based communication
- **JWT Authentication** - Secure token-based authentication
- **Docker** - Containerization for deployment
- **GitHub Actions** - CI/CD pipeline

### System Architecture

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│   React SPA     │───▶│  Spring Boot     │───▶│   PostgreSQL    │
│   (Frontend)    │    │  Backend API     │    │   Database      │
└─────────────────┘    └──────────────────┘    └─────────────────┘
                              │
                              ▼
                       ┌──────────────────┐
                       │   JWT Auth       │
                       │   Security       │
                       └──────────────────┘
```

### Project Structure

```
LifeSync/
├── backend/
│   ├── src/main/java/com/lifesync/
│   │   ├── controller/     # REST API endpoints
│   │   ├── service/        # Business logic
│   │   ├── repository/     # Data access layer
│   │   ├── model/          # Entity classes
│   │   ├── dto/            # Data transfer objects
│   │   ├── config/         # Configuration classes
│   │   └── security/       # Security configuration
│   ├── src/main/resources/
│   │   ├── application.yml # Application configuration
│   │   └── db/migration/   # Database migrations
│   └── pom.xml             # Maven dependencies
├── frontend/
│   ├── src/
│   │   ├── components/     # React components
│   │   ├── pages/          # Page components
│   │   ├── hooks/          # Custom React hooks
│   │   ├── services/       # API service functions
│   │   ├── types/          # TypeScript type definitions
│   │   ├── utils/          # Utility functions
│   │   └── styles/         # CSS/Tailwind styles
│   ├── public/             # Static assets
│   ├── package.json        # npm dependencies
│   └── vite.config.ts      # Vite configuration
├── docker-compose.yml       # Multi-container setup
└── README.md               # This file
```

## 🎯 Features

- **Personalized Training Plans**: AI-powered workout routine generation
- **Habit Tracking**: Comprehensive fitness habit management system
- **Progress Monitoring**: Real-time analytics and achievement tracking
- **Modern Web Interface**: Responsive React-based user experience
- **RESTful API**: Scalable backend with Spring Boot
- **Secure Authentication**: JWT-based user authentication
- **Data Persistence**: Reliable database storage for user data
- **Mobile Responsive**: Optimized for desktop and mobile devices

## 🚀 Getting Started

### Prerequisites

**Backend Requirements**:
- Java Development Kit (JDK) 17 or higher
- Maven 3.6+ or Gradle 7+
- PostgreSQL 13+ or MySQL 8.0+
- Docker (optional, for containerized deployment)

**Frontend Requirements**:
- Node.js 16+ or npm 8+
- Modern web browser (Chrome, Firefox, Safari, Edge)

### Installation

#### Option 1: Docker Deployment (Recommended)

1. Clone the repository:
```bash
git clone https://github.com/your-username/LifeSync.git
cd LifeSync
```

2. Start all services with Docker Compose:
```bash
docker-compose up -d
```

3. Access the application at `http://localhost:3000`

#### Option 2: Local Development Setup

**Backend Setup**:
```bash
cd backend
./mvnw spring-boot:run
```

**Frontend Setup**:
```bash
cd frontend
npm install
npm run dev
```

**Database Setup**:
```bash
# Start PostgreSQL
docker run --name lifesync-db -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres:13

# Or use the provided docker-compose
docker-compose up -d database
```

### Environment Configuration

**Backend (application.yml)**:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/lifesync
    username: ${DB_USERNAME:lifesync}
    password: ${DB_PASSWORD:password}
  
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

jwt:
  secret: ${JWT_SECRET:your-secret-key}
  expiration: 86400000 # 24 hours
```

**Frontend (.env)**:
```env
VITE_API_BASE_URL=http://localhost:8080/api
VITE_APP_NAME=LifeSync
```

## 📖 Usage Guide

### User Interface

#### Authentication Flow
1. **Registration**: Create new user account
2. **Login**: Secure JWT-based authentication
3. **Dashboard**: Personal fitness overview

#### Main Features

**Dashboard**:
- Weekly progress overview
- Active habits summary
- Quick action buttons
- Achievement notifications

**Habit Management**:
- Create custom fitness habits
- Set duration and frequency goals
- Track daily completion
- View habit statistics

**Training Plans**:
- AI-generated personalized routines
- Weekly schedule visualization
- Progress tracking
- Plan customization

**Analytics**:
- Progress charts and graphs
- Streak tracking
- Performance metrics
- Goal achievement analysis

### API Endpoints

#### Authentication
- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User login
- `POST /api/auth/refresh` - Token refresh

#### Habits
- `GET /api/habits` - Get all user habits
- `POST /api/habits` - Create new habit
- `PUT /api/habits/{id}` - Update habit
- `DELETE /api/habits/{id}` - Delete habit
- `POST /api/habits/{id}/complete` - Mark habit as completed

#### Training Plans
- `GET /api/plans` - Get training plans
- `POST /api/plans/generate` - Generate new plan
- `GET /api/plans/{id}/progress` - Get plan progress

#### Analytics
- `GET /api/analytics/progress` - Get user progress
- `GET /api/analytics/statistics` - Get detailed statistics

## 🔧 Technical Implementation

### Backend Architecture (Spring Boot)

#### Entity Models
```java
@Entity
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int duration; // in minutes
    private int frequency; // times per week
    private boolean completed;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToMany(mappedBy = "habit")
    private List<HabitCompletion> completions;
}

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password; // encrypted
    
    @OneToMany(mappedBy = "user")
    private List<Habit> habits;
}
```

#### Repository Layer
```java
@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findByUserId(Long userId);
    List<Habit> findByUserIdAndCompleted(Long userId, boolean completed);
}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
```

#### Service Layer
```java
@Service
@Transactional
public class HabitService {
    @Autowired
    private HabitRepository habitRepository;
    
    public Habit createHabit(HabitDTO habitDTO, Long userId) {
        Habit habit = new Habit();
        // Map DTO to entity and save
        return habitRepository.save(habit);
    }
    
    public List<Habit> getUserHabits(Long userId) {
        return habitRepository.findByUserId(userId);
    }
}
```

#### REST Controllers
```java
@RestController
@RequestMapping("/api/habits")
@CrossOrigin(origins = "http://localhost:3000")
public class HabitController {
    
    @Autowired
    private HabitService habitService;
    
    @GetMapping
    public ResponseEntity<List<Habit>> getUserHabits(
            @AuthenticationPrincipal UserDetails userDetails) {
        List<Habit> habits = habitService.getUserHabits(getUserId(userDetails));
        return ResponseEntity.ok(habits);
    }
    
    @PostMapping
    public ResponseEntity<Habit> createHabit(
            @RequestBody HabitDTO habitDTO,
            @AuthenticationPrincipal UserDetails userDetails) {
        Habit habit = habitService.createHabit(habitDTO, getUserId(userDetails));
        return ResponseEntity.ok(habit);
    }
}
```

### Frontend Architecture (React + TypeScript)

#### Type Definitions
```typescript
// types/habit.ts
export interface Habit {
  id: number;
  name: string;
  description: string;
  duration: number;
  frequency: number;
  completed: boolean;
  userId: number;
}

export interface HabitDTO {
  name: string;
  description: string;
  duration: number;
  frequency: number;
}

// types/user.ts
export interface User {
  id: number;
  username: string;
  email: string;
}
```

#### API Service Layer
```typescript
// services/api.ts
import axios from 'axios';
import { Habit, HabitDTO } from '../types/habit';

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export const habitAPI = {
  getHabits: async (): Promise<Habit[]> => {
    const response = await axios.get(`${API_BASE_URL}/habits`);
    return response.data;
  },
  
  createHabit: async (habit: HabitDTO): Promise<Habit> => {
    const response = await axios.post(`${API_BASE_URL}/habits`, habit);
    return response.data;
  },
  
  updateHabit: async (id: number, habit: Partial<HabitDTO>): Promise<Habit> => {
    const response = await axios.put(`${API_BASE_URL}/habits/${id}`, habit);
    return response.data;
  },
  
  deleteHabit: async (id: number): Promise<void> => {
    await axios.delete(`${API_BASE_URL}/habits/${id}`);
  }
};
```

#### React Components
```typescript
// components/HabitList.tsx
import React, { useState, useEffect } from 'react';
import { Habit } from '../types/habit';
import { habitAPI } from '../services/api';

export const HabitList: React.FC = () => {
  const [habits, setHabits] = useState<Habit[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadHabits();
  }, []);

  const loadHabits = async () => {
    try {
      const data = await habitAPI.getHabits();
      setHabits(data);
    } catch (error) {
      console.error('Failed to load habits:', error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="habit-list">
      {habits.map(habit => (
        <HabitCard key={habit.id} habit={habit} onUpdate={loadHabits} />
      ))}
    </div>
  );
};
```

### Database Schema

```sql
-- Users table
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Habits table
CREATE TABLE habits (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    duration INTEGER NOT NULL,
    frequency INTEGER NOT NULL,
    completed BOOLEAN DEFAULT FALSE,
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Habit completions table
CREATE TABLE habit_completions (
    id BIGSERIAL PRIMARY KEY,
    habit_id BIGINT REFERENCES habits(id) ON DELETE CASCADE,
    completed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    notes TEXT
);
```

## 📊 Data Flow

```
React Component → API Service → Spring Controller → Service Layer → Repository → Database
     ↓              ↓              ↓               ↓            ↓
  UI Updates    HTTP Request   Validation    Business Logic   SQL Queries
```

## 🔒 Security Implementation

### JWT Authentication Flow
```java
@Component
public class JwtTokenProvider {
    
    public String generateToken(UserDetails userDetails) {
        // Generate JWT token with user claims
    }
    
    public boolean validateToken(String token) {
        // Validate token signature and expiration
    }
    
    public String getUsernameFromToken(String token) {
        // Extract username from JWT token
    }
}
```

### React Authentication Hook
```typescript
// hooks/useAuth.ts
export const useAuth = () => {
  const [user, setUser] = useState<User | null>(null);
  const [token, setToken] = useState<string | null>(localStorage.getItem('token'));

  const login = async (credentials: LoginCredentials) => {
    const response = await authAPI.login(credentials);
    setToken(response.token);
    setUser(response.user);
    localStorage.setItem('token', response.token);
  };

  const logout = () => {
    setToken(null);
    setUser(null);
    localStorage.removeItem('token');
  };

  return { user, token, login, logout };
};
```

## 🔮 Future Enhancements

### Backend Improvements
- [ ] Microservices architecture with Spring Cloud
- [ ] Redis caching for improved performance
- [ ] WebSocket support for real-time updates
- [ ] Advanced AI/ML integration for personalized recommendations
- [ ] Integration with fitness wearables (Fitbit, Apple Watch)
- [ ] Email/SMS notifications for habit reminders

### Frontend Enhancements
- [ ] Progressive Web App (PWA) capabilities
- [ ] Offline functionality with service workers
- [ ] Advanced charts and analytics with D3.js
- [ ] Social features for community challenges
- [ ] Mobile app development with React Native
- [ ] Voice commands integration

### Infrastructure
- [ ] Kubernetes deployment
- [ ] CI/CD pipeline with GitHub Actions
- [ ] Monitoring and logging with ELK stack
- [ ] Load balancing and auto-scaling
- [ ] Multi-region deployment
- [ ] GraphQL API implementation

## 🤝 Contributing

### Development Workflow

1. **Fork the repository**
2. **Create a feature branch**:
```bash
git checkout -b feature/amazing-feature
```

3. **Set up development environment**:
```bash
# Backend
cd backend
./mvnw spring-boot:run

# Frontend
cd frontend
npm install
npm run dev
```

4. **Make your changes** with proper testing
5. **Commit your changes**:
```bash
git commit -m 'feat: add amazing feature'
```

6. **Push to the branch**:
```bash
git push origin feature/amazing-feature
```

7. **Open a Pull Request** with detailed description

### Code Standards

**Java (Backend)**:
- Follow Google Java Style Guide
- Use meaningful variable and method names
- Write unit tests for all business logic
- Document public APIs with Javadoc

**TypeScript (Frontend)**:
- Use ESLint and Prettier for code formatting
- Follow React best practices
- Write tests with Jest and React Testing Library
- Use TypeScript interfaces for type safety

### Testing Strategy

**Backend Tests**:
```bash
# Unit tests
./mvnw test

# Integration tests
./mvnw verify

# Code coverage
./mvnw jacoco:report
```

**Frontend Tests**:
```bash
# Unit tests
npm test

# E2E tests
npm run test:e2e

# Code coverage
npm run test:coverage
```


## 🚀 Deployment

### Production Deployment

#### Docker Compose (Staging)
```yaml
version: '3.8'
services:
  backend:
    build: ./backend
    environment:
      - SPRING_PROFILES_ACTIVE=prod
      - DB_HOST=database
    ports:
      - "8080:8080"
    depends_on:
      - database

  frontend:
    build: ./frontend
    ports:
      - "3000:80"
    depends_on:
      - backend

  database:
    image: postgres:13
    environment:
      - POSTGRES_DB=lifesync
      - POSTGRES_USER=lifesync
      - POSTGRES_PASSWORD=${DB_PASSWORD}
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
```

#### Kubernetes (Production)
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: lifesync-backend
spec:
  replicas: 3
  selector:
    matchLabels:
      app: lifesync-backend
  template:
    metadata:
      labels:
        app: lifesync-backend
    spec:
      containers:
      - name: backend
        image: lifesync/backend:latest
        ports:
        - containerPort: 8080
        env:
        - name: SPRING_PROFILES_ACTIVE
          value: "prod"
```

### Environment Variables

**Backend (.env)**:
```env
# Database Configuration
DB_HOST=localhost
DB_PORT=5432
DB_NAME=lifesync
DB_USERNAME=lifesync
DB_PASSWORD=your-secure-password

# JWT Configuration
JWT_SECRET=your-super-secret-jwt-key
JWT_EXPIRATION=86400000

# Application Configuration
SPRING_PROFILES_ACTIVE=prod
SERVER_PORT=8080
```

**Frontend (.env.production)**:
```env
VITE_API_BASE_URL=https://api.lifesync.com
VITE_APP_NAME=LifeSync
VITE_ENVIRONMENT=production
```

### Monitoring & Logging

**Application Monitoring**:
- Spring Boot Actuator endpoints
- Prometheus metrics collection
- Grafana dashboards
- Health checks and alerts

**Logging Strategy**:
- Structured logging with Logback
- ELK stack for log aggregation
- Error tracking with Sentry
- Performance monitoring with New Relic

---

**LifeSync** - Transform your fitness journey with intelligent coaching, modern technology, and personalized guidance. Built with passion for fitness and technology. 💪🚀


