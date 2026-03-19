package com.lifesync.service;

import com.lifesync.dto.AIWorkoutRequestDTO;
import com.lifesync.entity.WorkoutPlan;
import java.util.List;
import java.util.Map;

public interface AIService {
  
    WorkoutPlan generatePersonalizedWorkoutPlan(AIWorkoutRequestDTO request);

    List<String> generateWorkoutRecommendations(Long userId, String muscleGroup, String equipment);

    Map<String, Object> analyzeUserProgress(Long userId);
 
    Map<String, Object> suggestGoals(Long userId);
    
    Map<String, Object> getWorkoutAdjustments(Long userId, Long currentWorkoutPlanId);
}
