package com.lifesync.controller;

import com.lifesync.service.AIService;
import com.lifesync.dto.AIWorkoutRequestDTO;
import com.lifesync.entity.WorkoutPlan;
import com.lifesync.utility.BackendException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = "*")
public class AIController {

    @Autowired
    private AIService aiService;

    // Using a temporary user ID for development (without Spring Security)
    private static final Long TEMP_USER_ID = 1L;

    @PostMapping("/workout-plan")
    public ResponseEntity<WorkoutPlan> generateWorkoutPlan(@Valid @RequestBody AIWorkoutRequestDTO request) {
        request.setUserId(TEMP_USER_ID); // Override with temp user ID
        WorkoutPlan plan = aiService.generatePersonalizedWorkoutPlan(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(plan);
    }

    @GetMapping("/recommendations")
    public ResponseEntity<List<String>> getWorkoutRecommendations(
            @RequestParam String muscleGroup,
            @RequestParam(required = false) String equipment) {
        List<String> recommendations = aiService.generateWorkoutRecommendations(TEMP_USER_ID, muscleGroup, equipment);
        return ResponseEntity.ok(recommendations);
    }

    @GetMapping("/progress-analysis")
    public ResponseEntity<Map<String, Object>> analyzeProgress() {
        Map<String, Object> analysis = aiService.analyzeUserProgress(TEMP_USER_ID);
        return ResponseEntity.ok(analysis);
    }

    @GetMapping("/goals")
    public ResponseEntity<Map<String, Object>> getGoalSuggestions() {
        Map<String, Object> goals = aiService.suggestGoals(TEMP_USER_ID);
        return ResponseEntity.ok(goals);
    }

    @GetMapping("/adjustments")
    public ResponseEntity<Map<String, Object>> getWorkoutAdjustments(@RequestParam(required = false) Long currentWorkoutPlanId) {
        Map<String, Object> adjustments = aiService.getWorkoutAdjustments(TEMP_USER_ID, currentWorkoutPlanId);
        return ResponseEntity.ok(adjustments);
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getAIStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("aiService", "active");
        status.put("version", "1.0.0");
        status.put("features", List.of(
            "workout_plan_generation",
            "exercise_recommendations",
            "progress_analysis",
            "goal_suggestions",
            "workout_adjustments"
        ));
        status.put("status", "operational");
        return ResponseEntity.ok(status);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> health = new HashMap<>();
        health.put("status", "healthy");
        health.put("service", "AI Coach");
        health.put("timestamp", java.time.LocalDateTime.now().toString());
        return ResponseEntity.ok(health);
    }
}
