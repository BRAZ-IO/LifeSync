package com.lifesync.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "workout_plan_exercises")
@EntityListeners(AuditingEntityListener.class)
public class WorkoutPlanExercise {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String exerciseName;
    
    @Column(nullable = false)
    private Integer sets;
    
    @Column(nullable = false)
    private Integer reps;
    
    @Column(name = "rest_time_seconds")
    private Integer restTimeSeconds;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_plan_day_id", nullable = false)
    private WorkoutPlanDay workoutPlanDay;
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    public WorkoutPlanExercise() {
    }
    
    public WorkoutPlanExercise(String exerciseName, Integer sets, Integer reps, WorkoutPlanDay workoutPlanDay) {
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.reps = reps;
        this.workoutPlanDay = workoutPlanDay;
        this.restTimeSeconds = 60; // Default 60 seconds
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getExerciseName() {
        return exerciseName;
    }
    
    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }
    
    public Integer getSets() {
        return sets;
    }
    
    public void setSets(Integer sets) {
        this.sets = sets;
    }
    
    public Integer getReps() {
        return reps;
    }
    
    public void setReps(Integer reps) {
        this.reps = reps;
    }
    
    public Integer getRestTimeSeconds() {
        return restTimeSeconds;
    }
    
    public void setRestTimeSeconds(Integer restTimeSeconds) {
        this.restTimeSeconds = restTimeSeconds;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public WorkoutPlanDay getWorkoutPlanDay() {
        return workoutPlanDay;
    }
    
    public void setWorkoutPlanDay(WorkoutPlanDay workoutPlanDay) {
        this.workoutPlanDay = workoutPlanDay;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    // Business logic methods
    public String getDisplayInfo() {
        return String.format("%s: %d sets x %d reps (%ds rest)", 
            exerciseName, sets, reps, restTimeSeconds);
    }
}
