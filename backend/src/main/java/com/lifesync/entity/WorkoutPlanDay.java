package com.lifesync.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "workout_plan_days")
@EntityListeners(AuditingEntityListener.class)
public class WorkoutPlanDay {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Integer dayNumber;
    
    @Column(nullable = false)
    private String focus; // upper_body, lower_body, full_body, rest
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_plan_id", nullable = false)
    private WorkoutPlan workoutPlan;
    
    @OneToMany(mappedBy = "workoutPlanDay", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WorkoutPlanExercise> exercises;
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    public WorkoutPlanDay() {
    }
    
    public WorkoutPlanDay(Integer dayNumber, String focus, WorkoutPlan workoutPlan) {
        this.dayNumber = dayNumber;
        this.focus = focus;
        this.workoutPlan = workoutPlan;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getDayNumber() {
        return dayNumber;
    }
    
    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }
    
    public String getFocus() {
        return focus;
    }
    
    public void setFocus(String focus) {
        this.focus = focus;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public WorkoutPlan getWorkoutPlan() {
        return workoutPlan;
    }
    
    public void setWorkoutPlan(WorkoutPlan workoutPlan) {
        this.workoutPlan = workoutPlan;
    }
    
    public List<WorkoutPlanExercise> getExercises() {
        return exercises;
    }
    
    public void setExercises(List<WorkoutPlanExercise> exercises) {
        this.exercises = exercises;
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
    
    public boolean isRestDay() {
        return "rest".equalsIgnoreCase(focus);
    }
}
