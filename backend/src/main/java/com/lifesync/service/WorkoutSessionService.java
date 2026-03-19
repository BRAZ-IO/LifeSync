package com.lifesync.service;

import com.lifesync.entity.WorkoutSession;
import com.lifesync.dto.WorkoutSessionDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WorkoutSessionService {
    
    // Basic CRUD
    WorkoutSession startWorkoutSession(WorkoutSessionDTO sessionDTO, Long userId);
    WorkoutSession completeWorkoutSession(Long id, Long userId);
    Optional<WorkoutSession> getWorkoutSessionById(Long id, Long userId);
    List<WorkoutSession> getUserWorkoutSessions(Long userId);
    WorkoutSession updateWorkoutSession(Long id, WorkoutSessionDTO sessionDTO, Long userId);
    void deleteWorkoutSession(Long id, Long userId);
    
    // Alternative method names for controller compatibility
    default WorkoutSession startSession(WorkoutSessionDTO sessionDTO, Long userId) {
        return startWorkoutSession(sessionDTO, userId);
    }
    
    default List<WorkoutSession> getUserSessions(Long userId) {
        return getUserWorkoutSessions(userId);
    }
    
    default Optional<WorkoutSession> getSessionById(Long id, Long userId) {
        return getWorkoutSessionById(id, userId);
    }
    
    default WorkoutSession updateSession(Long id, WorkoutSessionDTO sessionDTO, Long userId) {
        return updateWorkoutSession(id, sessionDTO, userId);
    }
    
    default void deleteSession(Long id, Long userId) {
        deleteWorkoutSession(id, userId);
    }
    
    default WorkoutSession completeSession(Long id, Long userId) {
        return completeWorkoutSession(id, userId);
    }
    
    // Filtering methods
    List<WorkoutSession> getWorkoutSessionsByWorkout(Long userId, Long workoutId);
    List<WorkoutSession> getCompletedWorkoutSessions(Long userId);
    List<WorkoutSession> getWorkoutSessionsByDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate);
    
    // Alternative names for controller compatibility
    default List<WorkoutSession> getCompletedSessions(Long userId) {
        return getCompletedWorkoutSessions(userId);
    }
    
    default List<WorkoutSession> getSessionsByWorkout(Long userId, Long workoutId) {
        return getWorkoutSessionsByWorkout(userId, workoutId);
    }
    
    default List<WorkoutSession> getSessionsInPeriod(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return getWorkoutSessionsByDateRange(userId, startDate, endDate);
    }
    
    // Statistics
    long getTotalWorkoutSessionsCount(Long userId);
    long getCompletedWorkoutSessionsCount(Long userId);
    Integer getTotalDuration(Long userId);
    Integer getTotalCaloriesBurned(Long userId);
    Double getAverageIntensity(Long userId);
    Integer getAverageSessionDuration(Long userId);
    
    // Alternative names for controller compatibility
    default long getCompletedSessionsCount(Long userId) {
        return getCompletedWorkoutSessionsCount(userId);
    }
    
    default Integer getTotalCalories(Long userId) {
        return getTotalCaloriesBurned(userId);
    }
    
    // Additional methods for controller
    List<WorkoutSession> getRecentCompletedSessions(Long userId);
    long getCompletedSessionsCountSince(Long userId, LocalDateTime since);
    
    // Recent sessions
    List<WorkoutSession> getRecentWorkoutSessions(Long userId, int limit);
}
