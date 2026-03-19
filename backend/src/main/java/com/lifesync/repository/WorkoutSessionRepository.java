package com.lifesync.repository;

import com.lifesync.entity.WorkoutSession;
import com.lifesync.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {
    
    List<WorkoutSession> findByUserIdOrderByStartTimeDesc(Long userId);
    
    List<WorkoutSession> findByUserIdAndCompletedOrderByStartTimeDesc(Long userId, Boolean completed);
    
    List<WorkoutSession> findByUserIdAndWorkoutIdOrderByStartTimeDesc(Long userId, Long workoutId);
    
    List<WorkoutSession> findByUserIdAndStartTimeBetweenOrderByStartTimeDesc(Long userId, LocalDateTime startTime, LocalDateTime endTime);
    
    @Query("SELECT ws FROM WorkoutSession ws WHERE ws.user.id = :userId AND ws.completed = true AND ws.startTime >= :startDate ORDER BY ws.startTime DESC")
    List<WorkoutSession> findCompletedSessionsSince(@Param("userId") Long userId, @Param("startDate") LocalDateTime startDate);
    
    @Query("SELECT ws FROM WorkoutSession ws WHERE ws.user.id = :userId AND ws.completed = true AND ws.startTime >= :startDate AND ws.startTime <= :endDate ORDER BY ws.startTime DESC")
    List<WorkoutSession> findCompletedSessionsInPeriod(@Param("userId") Long userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT COUNT(ws) FROM WorkoutSession ws WHERE ws.user.id = :userId AND ws.completed = true")
    long countCompletedSessionsByUserId(@Param("userId") Long userId);
    
    @Query("SELECT COUNT(ws) FROM WorkoutSession ws WHERE ws.user.id = :userId AND ws.completed = true AND ws.startTime >= :startDate")
    long countCompletedSessionsSince(@Param("userId") Long userId, @Param("startDate") LocalDateTime startDate);
    
    @Query("SELECT SUM(ws.duration) FROM WorkoutSession ws WHERE ws.user.id = :userId AND ws.completed = true")
    Integer getTotalDurationByUserId(@Param("userId") Long userId);
    
    @Query("SELECT SUM(ws.caloriesBurned) FROM WorkoutSession ws WHERE ws.user.id = :userId AND ws.completed = true")
    Integer getTotalCaloriesByUserId(@Param("userId") Long userId);
    
    @Query("SELECT AVG(ws.intensity) FROM WorkoutSession ws WHERE ws.user.id = :userId AND ws.completed = true")
    Double getAverageIntensityByUserId(@Param("userId") Long userId);
    
    @Query("SELECT ws FROM WorkoutSession ws WHERE ws.user.id = :userId AND ws.completed = true ORDER BY ws.startTime DESC")
    List<WorkoutSession> findRecentCompletedSessions(@Param("userId") Long userId);
    
    @Query("SELECT ws.workout FROM WorkoutSession ws WHERE ws.user.id = :userId AND ws.completed = true GROUP BY ws.workout ORDER BY COUNT(ws.workout) DESC")
    List<Workout> findMostPerformedWorkouts(@Param("userId") Long userId);
}
