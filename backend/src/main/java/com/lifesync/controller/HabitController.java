package com.lifesync.controller;

import com.lifesync.entity.Habit;
import com.lifesync.dto.HabitDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
@CrossOrigin(origins = "*")
public class HabitController {
    
    @GetMapping
    public ResponseEntity<List<Habit>> getAllHabits() {
        // TODO: Implement get all habits for authenticated user
        return ResponseEntity.ok(List.of());
    }
    
    @PostMapping
    public ResponseEntity<Habit> createHabit(@RequestBody HabitDTO habitDTO) {
        // TODO: Implement habit creation
        return ResponseEntity.ok(new Habit());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Habit> getHabitById(@PathVariable Long id) {
        // TODO: Implement get habit by id
        return ResponseEntity.ok(new Habit());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Habit> updateHabit(@PathVariable Long id, @RequestBody HabitDTO habitDTO) {
        // TODO: Implement habit update
        return ResponseEntity.ok(new Habit());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long id) {
        // TODO: Implement habit deletion
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{id}/complete")
    public ResponseEntity<Habit> completeHabit(@PathVariable Long id) {
        // TODO: Implement habit completion
        return ResponseEntity.ok(new Habit());
    }
    
    @GetMapping("/completed")
    public ResponseEntity<List<Habit>> getCompletedHabits() {
        // TODO: Implement get completed habits
        return ResponseEntity.ok(List.of());
    }
    
    @GetMapping("/pending")
    public ResponseEntity<List<Habit>> getPendingHabits() {
        // TODO: Implement get pending habits
        return ResponseEntity.ok(List.of());
    }
}
