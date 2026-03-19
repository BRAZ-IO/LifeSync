package com.lifesync.repository;

import com.lifesync.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    
    List<Progress> findByUserIdOrderByDateDesc(Long userId);
    
    Optional<Progress> findByUserIdAndDate(Long userId, LocalDate date);
    
    List<Progress> findByUserIdAndDateBetweenOrderByDateDesc(Long userId, LocalDate startDate, LocalDate endDate);
    
    List<Progress> findTop30ByUserIdOrderByDateDesc(Long userId);
    
    @Query("SELECT p FROM Progress p WHERE p.user.id = :userId AND p.weight IS NOT NULL ORDER BY p.date DESC")
    List<Progress> findWeightHistoryByUserId(@Param("userId") Long userId);
    
    @Query("SELECT p FROM Progress p WHERE p.user.id = :userId AND p.bodyFat IS NOT NULL ORDER BY p.date DESC")
    List<Progress> findBodyFatHistoryByUserId(@Param("userId") Long userId);
    
    @Query("SELECT p FROM Progress p WHERE p.user.id = :userId AND p.muscleMass IS NOT NULL ORDER BY p.date DESC")
    List<Progress> findMuscleMassHistoryByUserId(@Param("userId") Long userId);
    
    @Query("SELECT COUNT(p) FROM Progress p WHERE p.user.id = :userId")
    long countTotalProgressByUserId(@Param("userId") Long userId);
    
    @Query("SELECT COUNT(p) FROM Progress p WHERE p.user.id = :userId AND p.date >= :startDate")
    long countProgressSince(@Param("userId") Long userId, @Param("startDate") LocalDate startDate);
    
    @Query("SELECT MIN(p.weight) FROM Progress p WHERE p.user.id = :userId AND p.weight IS NOT NULL")
    Double getMinWeightByUserId(@Param("userId") Long userId);
    
    @Query("SELECT MAX(p.weight) FROM Progress p WHERE p.user.id = :userId AND p.weight IS NOT NULL")
    Double getMaxWeightByUserId(@Param("userId") Long userId);
    
    @Query("SELECT AVG(p.weight) FROM Progress p WHERE p.user.id = :userId AND p.weight IS NOT NULL AND p.date BETWEEN :startDate AND :endDate")
    Double getAverageWeightInPeriod(@Param("userId") Long userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    @Query("SELECT p FROM Progress p WHERE p.user.id = :userId ORDER BY p.date DESC LIMIT 1")
    Optional<Progress> getLatestProgressByUserId(@Param("userId") Long userId);
    
    @Query("SELECT p FROM Progress p WHERE p.user.id = :userId ORDER BY p.date ASC LIMIT 1")
    Optional<Progress> getFirstProgressByUserId(@Param("userId") Long userId);
    
    @Query("SELECT p FROM Progress p WHERE p.user.id = :userId AND p.date >= :startDate ORDER BY p.date ASC")
    List<Progress> getProgressSinceDate(@Param("userId") Long userId, @Param("startDate") LocalDate startDate);
    
    @Query("SELECT DATE(p.date) as date, COUNT(p) as count FROM Progress p WHERE p.user.id = :userId GROUP BY DATE(p.date) ORDER BY DATE(p.date) DESC")
    List<Object[]> getProgressCountByDate(@Param("userId") Long userId);
    
    boolean existsByUserIdAndDate(Long userId, LocalDate date);
    
    Optional<Progress> findByIdAndUserId(Long id, Long userId);
}
