export interface User {
  id: string;
  name: string;
  email: string;
  avatar?: string;
  level: number;
  createdAt: string;
  updatedAt: string;
}

export interface Workout {
  id: string;
  name: string;
  description: string;
  category: string;
  duration: number;
  difficulty: 'Iniciante' | 'Intermediário' | 'Avançado';
  exercises: Exercise[];
  createdAt: string;
  updatedAt: string;
}

export interface Exercise {
  id: string;
  name: string;
  description: string;
  category: string;
  muscleGroups: string[];
  equipment: string[];
  instructions: string[];
  tips: string[];
  imageUrl?: string;
  videoUrl?: string;
}

export interface WorkoutSession {
  id: string;
  workoutId: string;
  userId: string;
  startTime: string;
  endTime?: string;
  duration?: number;
  completedExercises: CompletedExercise[];
  notes?: string;
  rating?: number;
}

export interface CompletedExercise {
  exerciseId: string;
  sets: Set[];
  completed: boolean;
}

export interface Set {
  reps?: number;
  weight?: number;
  duration?: number;
  distance?: number;
  restTime?: number;
}

export interface Habit {
  id: string;
  userId: string;
  name: string;
  description: string;
  category: string;
  targetValue?: number;
  unit?: string;
  frequency: 'daily' | 'weekly' | 'monthly';
  isActive: boolean;
  createdAt: string;
  updatedAt: string;
}

export interface HabitCompletion {
  id: string;
  habitId: string;
  userId: string;
  completedAt: string;
  value?: number;
  notes?: string;
}

export interface Progress {
  id: string;
  userId: string;
  date: string;
  weight?: number;
  bodyFat?: number;
  muscleMass?: number;
  measurements?: BodyMeasurements;
  photos?: string[];
  notes?: string;
}

export interface BodyMeasurements {
  chest?: number;
  waist?: number;
  hips?: number;
  arms?: number;
  thighs?: number;
  calves?: number;
}

export interface Achievement {
  id: string;
  name: string;
  description: string;
  icon: string;
  category: string;
  criteria: AchievementCriteria;
  points: number;
  unlockedAt?: string;
}

export interface AchievementCriteria {
  type: 'workouts' | 'streak' | 'weight' | 'habits' | 'time';
  target: number;
  period?: 'daily' | 'weekly' | 'monthly' | 'yearly' | 'all-time';
}

export interface NutritionPlan {
  id: string;
  userId: string;
  name: string;
  description: string;
  calories: number;
  macros: {
    protein: number;
    carbs: number;
    fat: number;
  };
  meals: Meal[];
  startDate: string;
  endDate?: string;
}

export interface Meal {
  id: string;
  name: string;
  type: 'breakfast' | 'lunch' | 'dinner' | 'snack';
  foods: Food[];
  calories: number;
  macros: {
    protein: number;
    carbs: number;
    fat: number;
  };
}

export interface Food {
  id: string;
  name: string;
  brand?: string;
  calories: number;
  protein: number;
  carbs: number;
  fat: number;
  fiber?: number;
  sugar?: number;
  sodium?: number;
  servingSize: number;
  servingUnit: string;
}

export interface AIRecommendation {
  id: string;
  userId: string;
  type: 'workout' | 'nutrition' | 'habit' | 'recovery';
  title: string;
  description: string;
  priority: 'low' | 'medium' | 'high';
  actionItems: string[];
  createdAt: string;
  viewed: boolean;
  applied: boolean;
}

export interface NotificationSettings {
  id: string;
  userId: string;
  workoutReminders: boolean;
  habitReminders: boolean;
  progressUpdates: boolean;
  aiRecommendations: boolean;
  socialUpdates: boolean;
  pushEnabled: boolean;
  emailEnabled: boolean;
}

export interface AppSettings {
  theme: 'light' | 'dark' | 'system';
  language: string;
  units: 'metric' | 'imperial';
  notifications: NotificationSettings;
  privacy: {
    profileVisibility: 'public' | 'friends' | 'private';
    shareProgress: boolean;
    shareAchievements: boolean;
  };
}
