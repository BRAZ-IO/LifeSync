import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

// Mock responses para desenvolvimento
const mockLoginResponse = {
  token: 'mock-jwt-token-12345',
  user: {
    id: '1',
    email: 'user@example.com',
    name: 'Usuário Teste',
  },
};

const mockRegisterResponse = {
  message: 'User created successfully',
  user: {
    id: '1',
    email: 'user@example.com',
    name: 'Usuário Teste',
  },
};

export const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Mock interceptor para desenvolvimento
api.interceptors.request.use(
  (config) => {
    console.log('API Request:', config.method?.toUpperCase(), config.url);
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

api.interceptors.response.use(
  (response) => {
    console.log('API Response:', response.status, response.config.url);
    return response;
  },
  (error) => {
    console.error('API Error:', error.message);
    // Mock successful responses for development
    if (error.code === 'ECONNREFUSED') {
      console.log('Backend not available, using mock responses');
      
      if (error.config.url?.includes('/auth/login')) {
        return Promise.resolve({ data: mockLoginResponse });
      }
      if (error.config.url?.includes('/auth/register')) {
        return Promise.resolve({ data: mockRegisterResponse });
      }
    }
    return Promise.reject(error);
  }
);

export interface LoginRequest {
  email: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  user: {
    id: string;
    email: string;
    name: string;
  };
}

export const authService = {
  login: async (data: LoginRequest): Promise<LoginResponse> => {
    console.log('=== LOGIN ATTEMPT ===');
    console.log('Email:', data.email);
    console.log('Password:', data.password);
    console.log('Password length:', data.password?.length);
    
    try {
      const response = await api.post('/auth/login', data);
      console.log('Real API response:', response.data);
      return response.data;
    } catch (error: any) {
      console.log('=== API ERROR ===');
      console.log('Error code:', error.code);
      console.log('Error message:', error.message);
      console.log('Error config:', error.config?.url);
      
      // Mock responses for development when backend is not available
      if (error.code === 'ECONNREFUSED' || 
          error.message.includes('Network Error') || 
          error.message.includes('ERR_NETWORK') ||
          error.code === 'ERR_NETWORK') {
        console.log('=== USING MOCK RESPONSE ===');
        
        // Simplified validation - accept any reasonable input
        if (data.email && data.password && data.password.length >= 3) {
          const mockResponse = {
            token: `mock-jwt-token-${Date.now()}`,
            user: {
              id: '1',
              email: data.email,
              name: data.email.split('@')[0] || 'Usuário',
            }
          };
          console.log('Mock response generated:', mockResponse);
          return mockResponse;
        }
        
        console.log('Validation failed - insufficient data');
        throw new Error('Por favor, preencha email e senha (mín. 3 caracteres)');
      }
      
      console.log('Unknown error type');
      throw new Error('Email ou senha inválidos');
    }
  },
  
  register: async (data: any) => {
    try {
      const response = await api.post('/auth/register', data);
      return response.data;
    } catch (error: any) {
      console.log('Register attempt:', data.email, data.name);
      
      // Mock successful registration for development
      if (error.code === 'ECONNREFUSED' || error.message.includes('Network Error')) {
        console.log('Using mock register response');
        
        if (data.email && data.password && data.name) {
          return {
            message: 'Usuário criado com sucesso!',
            user: {
              id: Date.now().toString(),
              email: data.email,
              name: data.name,
            }
          };
        }
        
        throw new Error('Dados de cadastro incompletos');
      }
      
      throw new Error('Não foi possível criar sua conta');
    }
  },
  
  setAuthToken: (token: string) => {
    api.defaults.headers.common['Authorization'] = `Bearer ${token}`;
  },
  
  clearAuthToken: () => {
    delete api.defaults.headers.common['Authorization'];
  }
};

export const workoutService = {
  getWorkouts: async () => {
    const response = await api.get('/workouts');
    return response.data;
  },
  
  getWorkoutById: async (id: string) => {
    const response = await api.get(`/workouts/${id}`);
    return response.data;
  },
  
  createWorkoutSession: async (workoutId: string) => {
    const response = await api.post('/sessions', { workoutId });
    return response.data;
  }
};

export const habitService = {
  getHabits: async () => {
    const response = await api.get('/habits');
    return response.data;
  },
  
  completeHabit: async (habitId: string) => {
    const response = await api.post(`/habits/${habitId}/complete`);
    return response.data;
  }
};
