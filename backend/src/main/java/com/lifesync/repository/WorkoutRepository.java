package com.lifesync.repository;

import com.lifesync.entity.Workout;
import com.lifesync.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    
    List<Workout> findByUserIdOrderByCreatedAtDesc(Long userId);
    
    List<Workout> findByUserIdAndMuscleGroupOrderByCreatedAtDesc(Long userId, String muscleGroup);
    
    List<Workout> findByUserIdAndDifficultyOrderByCreatedAtDesc(Long userId, Integer difficulty);
    
    List<Workout> findByUserIdAndEquipmentContainingOrderByCreatedAtDesc(Long userId, String equipment);
    
    @Query("SELECT w FROM Workout w WHERE w.user.id = :userId AND w.duration BETWEEN :minDuration AND :maxDuration ORDER BY w.createdAt DESC")
    List<Workout> findByUserIdAndDurationBetween(@Param("userId") Long userId, @Param("minDuration") Integer minDuration, @Param("maxDuration") Integer maxDuration);
    
    @Query("SELECT DISTINCT w.muscleGroup FROM Workout w WHERE w.user.id = :userId")
    List<String> findDistinctMuscleGroupsByUserId(@Param("userId") Long userId);
    
    @Query("SELECT DISTINCT w.equipment FROM Workout w WHERE w.user.id = :userId AND w.equipment IS NOT NULL")
    List<String> findDistinctEquipmentByUserId(@Param("userId") Long userId);
    
    @Query("SELECT COUNT(w) FROM Workout w WHERE w.user.id = :userId")
    long countTotalWorkoutsByUserId(@Param("userId") Long userId);
    
    @Query("SELECT COUNT(w) FROM Workout w WHERE w.user.id = :userId AND w.difficulty = :difficulty")
    long countWorkoutsByDifficulty(@Param("userId") Long userId, @Param("difficulty") Integer difficulty);
    
    @Query("SELECT AVG(w.duration) FROM Workout w WHERE w.user.id = :userId")
    Double getAverageDurationByUserId(@Param("userId") Long userId);
    
    @Query("SELECT SUM(w.duration) FROM Workout w WHERE w.user.id = :userId")
    Integer getTotalDurationByUserId(@Param("userId") Long userId);
    
    boolean existsByUserIdAndName(Long userId, String name);
    
    Optional<Workout> findByIdAndUserId(Long id, Long userId);
}
