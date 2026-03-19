package com.lifesync.controller;

import com.lifesync.entity.Habit;
import com.lifesync.dto.HabitDTO;
import com.lifesync.service.HabitService;
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
@RequestMapping("/habits")
@CrossOrigin(origins = "*")
public class HabitController {

    @Autowired
    private HabitService habitService;

    // Using a temporary user ID for development (without Spring Security)
    private static final Long TEMP_USER_ID = 1L;
    
    @GetMapping
    public ResponseEntity<List<Habit>> getAllHabits() {
        List<Habit> habits = habitService.getUserHabits(TEMP_USER_ID);
        return ResponseEntity.ok(habits);
    }
    
    @PostMapping
    public ResponseEntity<Habit> createHabit(@Valid @RequestBody HabitDTO habitDTO) {
        Habit habit = habitService.createHabit(habitDTO, TEMP_USER_ID);
        return ResponseEntity.status(HttpStatus.CREATED).body(habit);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Habit> getHabitById(@PathVariable Long id) {
        Habit habit = habitService.getHabitById(id, TEMP_USER_ID)
            .orElseThrow(() -> new BackendException("Habit not found with id: " + id));
        return ResponseEntity.ok(habit);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Habit> updateHabit(@PathVariable Long id, @Valid @RequestBody HabitDTO habitDTO) {
        Habit habit = habitService.updateHabit(id, habitDTO, TEMP_USER_ID);
        return ResponseEntity.ok(habit);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteHabit(@PathVariable Long id) {
        habitService.deleteHabit(id, TEMP_USER_ID);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Habit deleted successfully");
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/{id}/complete")
    public ResponseEntity<Habit> completeHabit(@PathVariable Long id) {
        Habit habit = habitService.completeHabit(id, TEMP_USER_ID);
        return ResponseEntity.ok(habit);
    }
    
    @GetMapping("/completed")
    public ResponseEntity<List<Habit>> getCompletedHabits() {
        List<Habit> habits = habitService.getCompletedHabits(TEMP_USER_ID);
        return ResponseEntity.ok(habits);
    }
    
    @GetMapping("/pending")
    public ResponseEntity<List<Habit>> getPendingHabits() {
        List<Habit> habits = habitService.getPendingHabits(TEMP_USER_ID);
        return ResponseEntity.ok(habits);
    }
    
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getHabitStats() {
        long total = habitService.getTotalHabitsCount(TEMP_USER_ID);
        long completed = habitService.getCompletedHabitsCount(TEMP_USER_ID);
        
        Map<String, Long> stats = new HashMap<>();
        stats.put("total", total);
        stats.put("completed", completed);
        stats.put("pending", total - completed);
        
        return ResponseEntity.ok(stats);
    }
}
