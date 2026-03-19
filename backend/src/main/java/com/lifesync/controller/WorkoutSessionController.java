package com.lifesync.controller;

import com.lifesync.entity.WorkoutSession;
import com.lifesync.dto.WorkoutSessionDTO;
import com.lifesync.service.WorkoutSessionService;
import com.lifesync.utility.BackendException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workout-sessions")
@CrossOrigin(origins = "*")
public class WorkoutSessionController {

    @Autowired
    private WorkoutSessionService sessionService;

    // Using a temporary user ID for development (without Spring Security)
    private static final Long TEMP_USER_ID = 1L;

    @PostMapping
    public ResponseEntity<WorkoutSession> startSession(@Valid @RequestBody WorkoutSessionDTO sessionDTO) {
        WorkoutSession session = sessionService.startSession(sessionDTO, TEMP_USER_ID);
        return ResponseEntity.status(HttpStatus.CREATED).body(session);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutSession>> getAllSessions() {
        List<WorkoutSession> sessions = sessionService.getUserSessions(TEMP_USER_ID);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutSession> getSessionById(@PathVariable Long id) {
        WorkoutSession session = sessionService.getSessionById(id, TEMP_USER_ID)
            .orElseThrow(() -> new BackendException("Session not found with id: " + id));
        return ResponseEntity.ok(session);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutSession> updateSession(@PathVariable Long id, @Valid @RequestBody WorkoutSessionDTO sessionDTO) {
        WorkoutSession session = sessionService.updateSession(id, sessionDTO, TEMP_USER_ID);
        return ResponseEntity.ok(session);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id, TEMP_USER_ID);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Workout session deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<WorkoutSession> completeSession(@PathVariable Long id) {
        WorkoutSession session = sessionService.completeSession(id, TEMP_USER_ID);
        return ResponseEntity.ok(session);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<WorkoutSession>> getCompletedSessions() {
        List<WorkoutSession> sessions = sessionService.getCompletedSessions(TEMP_USER_ID);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/workout/{workoutId}")
    public ResponseEntity<List<WorkoutSession>> getSessionsByWorkout(@PathVariable Long workoutId) {
        List<WorkoutSession> sessions = sessionService.getSessionsByWorkout(TEMP_USER_ID, workoutId);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/period")
    public ResponseEntity<List<WorkoutSession>> getSessionsInPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<WorkoutSession> sessions = sessionService.getSessionsInPeriod(TEMP_USER_ID, startDate, endDate);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<WorkoutSession>> getRecentCompletedSessions() {
        List<WorkoutSession> sessions = sessionService.getRecentCompletedSessions(TEMP_USER_ID);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getSessionStats() {
        long completedCount = sessionService.getCompletedSessionsCount(TEMP_USER_ID);
        Integer totalDuration = sessionService.getTotalDuration(TEMP_USER_ID);
        Integer totalCalories = sessionService.getTotalCalories(TEMP_USER_ID);
        Double avgIntensity = sessionService.getAverageIntensity(TEMP_USER_ID);
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("completedSessions", completedCount);
        stats.put("totalDuration", totalDuration);
        stats.put("totalCalories", totalCalories);
        stats.put("averageIntensity", avgIntensity);
        
        // Add sessions completed this month
        LocalDateTime monthStart = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        long thisMonthCount = sessionService.getCompletedSessionsCountSince(TEMP_USER_ID, monthStart);
        stats.put("thisMonthSessions", thisMonthCount);
        
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/stats/weekly")
    public ResponseEntity<Map<String, Object>> getWeeklyStats() {
        LocalDateTime weekStart = LocalDateTime.now().minusDays(7);
        List<WorkoutSession> weeklySessions = sessionService.getSessionsInPeriod(TEMP_USER_ID, weekStart, LocalDateTime.now());
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("weeklySessions", weeklySessions.size());
        stats.put("weeklyDuration", weeklySessions.stream().mapToInt(WorkoutSession::getDuration).sum());
        stats.put("weeklyCalories", weeklySessions.stream().mapToInt(s -> s.getCaloriesBurned() != null ? s.getCaloriesBurned() : 0).sum());
        
        return ResponseEntity.ok(stats);
    }
}
