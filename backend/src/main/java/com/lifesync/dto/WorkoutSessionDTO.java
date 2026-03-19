package com.lifesync.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class WorkoutSessionDTO {

    @NotNull(message = "Start time is required")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    private Integer duration;

    @Min(value = 0, message = "Calories burned cannot be negative")
    private Integer caloriesBurned;

    private String notes;

    @NotNull(message = "Intensity is required")
    @Min(value = 1.0, message = "Intensity must be at least 1")
    @Max(value = 10.0, message = "Intensity cannot exceed 10")
    private Double intensity;

    private Boolean completed;

    @NotNull(message = "Workout ID is required")
    private Long workoutId;

    public WorkoutSessionDTO() {
    }

    public WorkoutSessionDTO(LocalDateTime startTime, Long workoutId, Double intensity) {
        this.startTime = startTime;
        this.workoutId = workoutId;
        this.intensity = intensity;
        this.completed = false;
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

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }
}
