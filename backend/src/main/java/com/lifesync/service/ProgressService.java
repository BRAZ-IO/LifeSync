package com.lifesync.service;

import com.lifesync.entity.Progress;
import com.lifesync.dto.ProgressDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProgressService {
    
    Progress createProgress(ProgressDTO progressDTO, Long userId);
    
    Optional<Progress> getProgressById(Long id, Long userId);
    
    Optional<Progress> getProgressByDate(Long userId, LocalDate date);
    
    List<Progress> getUserProgress(Long userId);
    
    List<Progress> getProgressInPeriod(Long userId, LocalDate startDate, LocalDate endDate);
    
    List<Progress> getRecentProgress(Long userId, int limit);
    
    List<Progress> getWeightHistory(Long userId);
    
    List<Progress> getBodyFatHistory(Long userId);
    
    List<Progress> getMuscleMassHistory(Long userId);
    
    Progress updateProgress(Long id, ProgressDTO progressDTO, Long userId);
    
    void deleteProgress(Long id, Long userId);
    
    long getTotalProgressCount(Long userId);
    
    long getProgressCountSince(Long userId, LocalDate startDate);
    
    Optional<Progress> getLatestProgress(Long userId);
    
    Optional<Progress> getFirstProgress(Long userId);
    
    Double getMinWeight(Long userId);
    
    Double getMaxWeight(Long userId);
    
    Double getAverageWeightInPeriod(Long userId, LocalDate startDate, LocalDate endDate);
    
    Double getWeightChange(Long userId);
    
    Double getBodyFatChange(Long userId);
    
    Double getMuscleMassChange(Long userId);
    
    boolean existsByUserIdAndDate(Long userId, LocalDate date);
}
