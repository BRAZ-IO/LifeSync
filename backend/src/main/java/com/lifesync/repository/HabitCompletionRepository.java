package com.lifesync.repository;

import com.lifesync.entity.HabitCompletion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HabitCompletionRepository extends JpaRepository<HabitCompletion, Long> {
    
    List<HabitCompletion> findByHabitId(Long habitId);
    
    List<HabitCompletion> findByHabitUserId(Long userId);
    
    @Query("SELECT hc FROM HabitCompletion hc WHERE hc.habit.id = :habitId ORDER BY hc.completedAt DESC")
    List<HabitCompletion> findByHabitIdOrderByCompletedAtDesc(@Param("habitId") Long habitId);
    
    @Query("SELECT hc FROM HabitCompletion hc WHERE hc.habit.user.id = :userId AND hc.completedAt BETWEEN :startDate AND :endDate")
    List<HabitCompletion> findByUserIdAndDateRange(@Param("userId") Long userId, 
                                                  @Param("startDate") LocalDateTime startDate, 
                                                  @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT COUNT(hc) FROM HabitCompletion hc WHERE hc.habit.id = :habitId")
    long countByHabitId(@Param("habitId") Long habitId);
}
