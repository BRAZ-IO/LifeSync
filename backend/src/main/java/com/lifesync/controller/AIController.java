package com.lifesync.controller;

import com.lifesync.service.AIService;
import com.lifesync.dto.AIWorkoutRequestDTO;
import com.lifesync.entity.WorkoutPlan;
import com.lifesync.utility.BackendException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "AI Coach", description = "AI-powered fitness coaching and recommendations endpoints")
public class AIController {

    @Autowired
    private AIService aiService;

    // Using a temporary user ID for development (without Spring Security)
    private static final Long TEMP_USER_ID = 1L;

    @PostMapping("/workout-plan")
    @Operation(summary = "Generate personalized workout plan", description = "Creates a personalized workout plan using AI based on user profile and goals")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Workout plan generated successfully",
            content = @Content(schema = @Schema(implementation = WorkoutPlan.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<WorkoutPlan> generateWorkoutPlan(@Valid @RequestBody AIWorkoutRequestDTO request) {
        request.setUserId(TEMP_USER_ID); // Override with temp user ID
        WorkoutPlan plan = aiService.generatePersonalizedWorkoutPlan(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(plan);
    }

    @GetMapping("/recommendations")
    @Operation(summary = "Get workout recommendations", description = "Provides AI-powered exercise recommendations based on muscle group and available equipment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recommendations generated successfully",
            content = @Content(schema = @Schema(implementation = List.class))),
        @ApiResponse(responseCode = "400", description = "Invalid parameters")
    })
    public ResponseEntity<List<String>> getWorkoutRecommendations(
            @Parameter(description = "Target muscle group (chest, back, legs, shoulders, arms, core)") 
            @RequestParam String muscleGroup,
            @Parameter(description = "Available equipment (none, basic, full_gym)") 
            @RequestParam(required = false) String equipment) {
        List<String> recommendations = aiService.generateWorkoutRecommendations(TEMP_USER_ID, muscleGroup, equipment);
        return ResponseEntity.ok(recommendations);
    }

    @GetMapping("/progress-analysis")
    @Operation(summary = "Analyze user progress", description = "Provides AI-powered analysis of user's progress trends and insights")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Progress analysis completed successfully"),
        @ApiResponse(responseCode = "404", description = "No progress data found")
    })
    public ResponseEntity<Map<String, Object>> analyzeProgress() {
        Map<String, Object> analysis = aiService.analyzeUserProgress(TEMP_USER_ID);
        return ResponseEntity.ok(analysis);
    }

    @GetMapping("/goals")
    @Operation(summary = "Get goal suggestions", description = "Provides AI-powered personalized goal suggestions based on user profile and current stats")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Goal suggestions generated successfully"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<Map<String, Object>> getGoalSuggestions() {
        Map<String, Object> goals = aiService.suggestGoals(TEMP_USER_ID);
        return ResponseEntity.ok(goals);
    }

    @GetMapping("/adjustments")
    @Operation(summary = "Get workout adjustments", description = "Provides AI-powered workout plan adjustments based on current progress")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Workout adjustments generated successfully"),
        @ApiResponse(responseCode = "404", description = "No recent progress data found")
    })
    public ResponseEntity<Map<String, Object>> getWorkoutAdjustments(
            @Parameter(description = "Current workout plan ID for context") 
            @RequestParam(required = false) Long currentWorkoutPlanId) {
        Map<String, Object> adjustments = aiService.getWorkoutAdjustments(TEMP_USER_ID, currentWorkoutPlanId);
        return ResponseEntity.ok(adjustments);
    }

    @GetMapping("/status")
    @Operation(summary = "Get AI service status", description = "Returns the current status and capabilities of the AI service")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "AI service status retrieved successfully")
    })
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
    @Operation(summary = "AI service health check", description = "Simple health check endpoint for the AI service")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "AI service is healthy")
    })
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> health = new HashMap<>();
        health.put("status", "healthy");
        health.put("service", "AI Coach");
        health.put("timestamp", java.time.LocalDateTime.now().toString());
        return ResponseEntity.ok(health);
    }
}
