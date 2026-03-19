package com.lifesync.service;

import com.lifesync.entity.Workout;
import com.lifesync.dto.WorkoutDTO;

import java.util.List;
import java.util.Optional;

public interface WorkoutService {
    
    Workout createWorkout(WorkoutDTO workoutDTO, Long userId);
    
    Optional<Workout> getWorkoutById(Long id, Long userId);
    
    List<Workout> getUserWorkouts(Long userId);
    
    List<Workout> getWorkoutsByMuscleGroup(Long userId, String muscleGroup);
    
    List<Workout> getWorkoutsByDifficulty(Long userId, Integer difficulty);
    
    List<Workout> getWorkoutsByEquipment(Long userId, String equipment);
    
    List<Workout> getWorkoutsByDurationRange(Long userId, Integer minDuration, Integer maxDuration);
    
    List<String> getMuscleGroups(Long userId);
    
    List<String> getEquipment(Long userId);
    
    Workout updateWorkout(Long id, WorkoutDTO workoutDTO, Long userId);
    
    void deleteWorkout(Long id, Long userId);
    
    long getTotalWorkoutsCount(Long userId);
    
    long getWorkoutsCountByDifficulty(Long userId, Integer difficulty);
    
    Double getAverageDuration(Long userId);
    
    Integer getTotalDuration(Long userId);
    
    boolean existsByUserIdAndName(Long userId, String name);
}
