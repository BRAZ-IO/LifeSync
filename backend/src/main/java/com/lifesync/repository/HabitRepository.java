package com.lifesync.repository;

import com.lifesync.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {
    
    List<Habit> findByUserId(Long userId);
    
    List<Habit> findByUserIdAndCompleted(Long userId, Boolean completed);
    
    Optional<Habit> findByIdAndUserId(Long id, Long userId);
    
    @Query("SELECT h FROM Habit h WHERE h.user.id = :userId ORDER BY h.createdAt DESC")
    List<Habit> findByUserIdOrderByCreatedAtDesc(@Param("userId") Long userId);
    
    @Query("SELECT COUNT(h) FROM Habit h WHERE h.user.id = :userId AND h.completed = true")
    long countCompletedHabitsByUserId(@Param("userId") Long userId);
    
    @Query("SELECT COUNT(h) FROM Habit h WHERE h.user.id = :userId")
    long countTotalHabitsByUserId(@Param("userId") Long userId);
}
