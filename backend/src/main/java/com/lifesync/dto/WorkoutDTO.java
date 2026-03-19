package com.lifesync.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

public class WorkoutDTO {

    @NotBlank(message = "Workout name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @NotNull(message = "Duration is required")
    @Min(value = 5, message = "Duration must be at least 5 minutes")
    @Max(value = 300, message = "Duration cannot exceed 300 minutes (5 hours)")
    private Integer duration;

    @NotNull(message = "Difficulty is required")
    @Min(value = 1, message = "Difficulty must be at least 1")
    @Max(value = 5, message = "Difficulty cannot exceed 5")
    private Integer difficulty;

    @Size(max = 50, message = "Muscle group must not exceed 50 characters")
    private String muscleGroup;

    @Size(max = 100, message = "Equipment must not exceed 100 characters")
    private String equipment;

    @Min(value = 0, message = "Calories estimate cannot be negative")
    private Integer caloriesEstimate;

    public WorkoutDTO() {
    }

    public WorkoutDTO(String name, String description, Integer duration, Integer difficulty) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public Integer getCaloriesEstimate() {
        return caloriesEstimate;
    }

    public void setCaloriesEstimate(Integer caloriesEstimate) {
        this.caloriesEstimate = caloriesEstimate;
    }
}
