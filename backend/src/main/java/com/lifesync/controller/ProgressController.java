package com.lifesync.controller;

import com.lifesync.entity.Progress;
import com.lifesync.dto.ProgressDTO;
import com.lifesync.service.ProgressService;
import com.lifesync.utility.BackendException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/progress")
@CrossOrigin(origins = "*")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    // Using a temporary user ID for development (without Spring Security)
    private static final Long TEMP_USER_ID = 1L;

    @PostMapping
    public ResponseEntity<Progress> createProgress(@Valid @RequestBody ProgressDTO progressDTO) {
        Progress progress = progressService.createProgress(progressDTO, TEMP_USER_ID);
        return ResponseEntity.status(HttpStatus.CREATED).body(progress);
    }

    @GetMapping
    public ResponseEntity<List<Progress>> getAllProgress() {
        List<Progress> progress = progressService.getUserProgress(TEMP_USER_ID);
        return ResponseEntity.ok(progress);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Progress> getProgressById(@PathVariable Long id) {
        Progress progress = progressService.getProgressById(id, TEMP_USER_ID)
            .orElseThrow(() -> new BackendException("Progress not found with id: " + id));
        return ResponseEntity.ok(progress);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<Progress> getProgressByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Progress progress = progressService.getProgressByDate(TEMP_USER_ID, date)
            .orElseThrow(() -> new BackendException("Progress not found for date: " + date));
        return ResponseEntity.ok(progress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Progress> updateProgress(@PathVariable Long id, @Valid @RequestBody ProgressDTO progressDTO) {
        Progress progress = progressService.updateProgress(id, progressDTO, TEMP_USER_ID);
        return ResponseEntity.ok(progress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProgress(@PathVariable Long id) {
        progressService.deleteProgress(id, TEMP_USER_ID);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Progress deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/period")
    public ResponseEntity<List<Progress>> getProgressInPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Progress> progress = progressService.getProgressInPeriod(TEMP_USER_ID, startDate, endDate);
        return ResponseEntity.ok(progress);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Progress>> getRecentProgress(@RequestParam(defaultValue = "30") int limit) {
        List<Progress> progress = progressService.getRecentProgress(TEMP_USER_ID, limit);
        return ResponseEntity.ok(progress);
    }

    @GetMapping("/weight-history")
    public ResponseEntity<List<Progress>> getWeightHistory() {
        List<Progress> progress = progressService.getWeightHistory(TEMP_USER_ID);
        return ResponseEntity.ok(progress);
    }

    @GetMapping("/bodyfat-history")
    public ResponseEntity<List<Progress>> getBodyFatHistory() {
        List<Progress> progress = progressService.getBodyFatHistory(TEMP_USER_ID);
        return ResponseEntity.ok(progress);
    }

    @GetMapping("/musclemass-history")
    public ResponseEntity<List<Progress>> getMuscleMassHistory() {
        List<Progress> progress = progressService.getMuscleMassHistory(TEMP_USER_ID);
        return ResponseEntity.ok(progress);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getProgressStats() {
        long total = progressService.getTotalProgressCount(TEMP_USER_ID);
        Double minWeight = progressService.getMinWeight(TEMP_USER_ID);
        Double maxWeight = progressService.getMaxWeight(TEMP_USER_ID);
        Double weightChange = progressService.getWeightChange(TEMP_USER_ID);
        Double bodyFatChange = progressService.getBodyFatChange(TEMP_USER_ID);
        Double muscleMassChange = progressService.getMuscleMassChange(TEMP_USER_ID);
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalRecords", total);
        stats.put("minWeight", minWeight);
        stats.put("maxWeight", maxWeight);
        stats.put("weightChange", weightChange);
        stats.put("bodyFatChange", bodyFatChange);
        stats.put("muscleMassChange", muscleMassChange);
        
        // Add latest progress info
        progressService.getLatestProgress(TEMP_USER_ID).ifPresent(latest -> {
            stats.put("latestWeight", latest.getWeight());
            stats.put("latestBodyFat", latest.getBodyFat());
            stats.put("latestMuscleMass", latest.getMuscleMass());
            stats.put("latestDate", latest.getDate());
        });
        
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/stats/weekly")
    public ResponseEntity<Map<String, Object>> getWeeklyStats() {
        LocalDate weekStart = LocalDate.now().minusDays(7);
        LocalDate weekEnd = LocalDate.now();
        
        List<Progress> weeklyProgress = progressService.getProgressInPeriod(TEMP_USER_ID, weekStart, weekEnd);
        Double avgWeight = progressService.getAverageWeightInPeriod(TEMP_USER_ID, weekStart, weekEnd);
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("weeklyRecords", weeklyProgress.size());
        stats.put("averageWeight", avgWeight);
        stats.put("weekStart", weekStart);
        stats.put("weekEnd", weekEnd);
        
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/stats/monthly")
    public ResponseEntity<Map<String, Object>> getMonthlyStats() {
        LocalDate monthStart = LocalDate.now().withDayOfMonth(1);
        LocalDate monthEnd = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        
        long thisMonthCount = progressService.getProgressCountSince(TEMP_USER_ID, monthStart);
        Double avgWeight = progressService.getAverageWeightInPeriod(TEMP_USER_ID, monthStart, monthEnd);
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("monthlyRecords", thisMonthCount);
        stats.put("averageWeight", avgWeight);
        stats.put("monthStart", monthStart);
        stats.put("monthEnd", monthEnd);
        
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/check/date/{date}")
    public ResponseEntity<Map<String, Boolean>> checkProgressDateAvailability(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        boolean exists = progressService.existsByUserIdAndDate(TEMP_USER_ID, date);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }
}
