package com.lifesync.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "workout_sessions")
@EntityListeners(AuditingEntityListener.class)
public class WorkoutSession {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;
    
    @Column(name = "end_time")
    private LocalDateTime endTime;
    
    @Column(nullable = false)
    private Integer duration; // minutos
    
    @Column(name = "calories_burned")
    private Integer caloriesBurned;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    @Column(nullable = false)
    private Double intensity; // 1-10 scale
    
    @Column(name = "completed")
    private Boolean completed = false;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    public WorkoutSession() {
    }
    
    public WorkoutSession(User user, Workout workout, LocalDateTime startTime, Double intensity) {
        this.user = user;
        this.workout = workout;
        this.startTime = startTime;
        this.intensity = intensity;
        this.duration = workout.getDuration(); // Default to workout duration
        this.completed = false;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDateTime getStartTime() {
        return startTime;
    }
    
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    
    public LocalDateTime getEndTime() {
        return endTime;
    }
    
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    
    public Integer getDuration() {
        return duration;
    }
    
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    
    public Integer getCaloriesBurned() {
        return caloriesBurned;
    }
    
    public void setCaloriesBurned(Integer caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public Double getIntensity() {
        return intensity;
    }
    
    public void setIntensity(Double intensity) {
        this.intensity = intensity;
    }
    
    public Boolean getCompleted() {
        return completed;
    }
    
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Workout getWorkout() {
        return workout;
    }
    
    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    // Business logic methods
    public void completeSession() {
        this.endTime = LocalDateTime.now();
        this.completed = true;
        if (this.startTime != null && this.endTime != null) {
            this.duration = (int) java.time.Duration.between(startTime, endTime).toMinutes();
        }
        // Estimate calories based on workout and intensity
        if (workout != null && workout.getCaloriesEstimate() != null) {
            this.caloriesBurned = (int) (workout.getCaloriesEstimate() * (intensity / 10.0));
        }
    }
}
