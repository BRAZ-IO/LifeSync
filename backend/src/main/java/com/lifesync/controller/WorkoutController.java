package com.lifesync.controller;

import com.lifesync.entity.Workout;
import com.lifesync.dto.WorkoutDTO;
import com.lifesync.service.WorkoutService;
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
@RequestMapping("/workouts")
@CrossOrigin(origins = "*")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    // Using a temporary user ID for development (without Spring Security)
    private static final Long TEMP_USER_ID = 1L;

    @PostMapping
    public ResponseEntity<Workout> createWorkout(@Valid @RequestBody WorkoutDTO workoutDTO) {
        Workout workout = workoutService.createWorkout(workoutDTO, TEMP_USER_ID);
        return ResponseEntity.status(HttpStatus.CREATED).body(workout);
    }

    @GetMapping
    public ResponseEntity<List<Workout>> getAllWorkouts() {
        List<Workout> workouts = workoutService.getUserWorkouts(TEMP_USER_ID);
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable Long id) {
        Workout workout = workoutService.getWorkoutById(id, TEMP_USER_ID)
            .orElseThrow(() -> new BackendException("Workout not found with id: " + id));
        return ResponseEntity.ok(workout);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable Long id, @Valid @RequestBody WorkoutDTO workoutDTO) {
        Workout workout = workoutService.updateWorkout(id, workoutDTO, TEMP_USER_ID);
        return ResponseEntity.ok(workout);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteWorkout(@PathVariable Long id) {
        workoutService.deleteWorkout(id, TEMP_USER_ID);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Workout deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/muscle-group/{muscleGroup}")
    public ResponseEntity<List<Workout>> getWorkoutsByMuscleGroup(@PathVariable String muscleGroup) {
        List<Workout> workouts = workoutService.getWorkoutsByMuscleGroup(TEMP_USER_ID, muscleGroup);
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<Workout>> getWorkoutsByDifficulty(@PathVariable Integer difficulty) {
        List<Workout> workouts = workoutService.getWorkoutsByDifficulty(TEMP_USER_ID, difficulty);
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/equipment/{equipment}")
    public ResponseEntity<List<Workout>> getWorkoutsByEquipment(@PathVariable String equipment) {
        List<Workout> workouts = workoutService.getWorkoutsByEquipment(TEMP_USER_ID, equipment);
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/duration-range")
    public ResponseEntity<List<Workout>> getWorkoutsByDurationRange(
            @RequestParam Integer minDuration, 
            @RequestParam Integer maxDuration) {
        List<Workout> workouts = workoutService.getWorkoutsByDurationRange(TEMP_USER_ID, minDuration, maxDuration);
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/muscle-groups")
    public ResponseEntity<List<String>> getMuscleGroups() {
        List<String> muscleGroups = workoutService.getMuscleGroups(TEMP_USER_ID);
        return ResponseEntity.ok(muscleGroups);
    }

    @GetMapping("/equipment")
    public ResponseEntity<List<String>> getEquipment() {
        List<String> equipment = workoutService.getEquipment(TEMP_USER_ID);
        return ResponseEntity.ok(equipment);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getWorkoutStats() {
        long total = workoutService.getTotalWorkoutsCount(TEMP_USER_ID);
        Double avgDuration = workoutService.getAverageDuration(TEMP_USER_ID);
        Integer totalDuration = workoutService.getTotalDuration(TEMP_USER_ID);
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalWorkouts", total);
        stats.put("averageDuration", avgDuration);
        stats.put("totalDuration", totalDuration);
        
        // Add difficulty breakdown
        Map<String, Long> difficultyStats = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            long count = workoutService.getWorkoutsCountByDifficulty(TEMP_USER_ID, i);
            difficultyStats.put("difficulty" + i, count);
        }
        stats.put("difficultyBreakdown", difficultyStats);
        
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/check/name/{name}")
    public ResponseEntity<Map<String, Boolean>> checkWorkoutNameAvailability(@PathVariable String name) {
        boolean exists = workoutService.existsByUserIdAndName(TEMP_USER_ID, name);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }
}
