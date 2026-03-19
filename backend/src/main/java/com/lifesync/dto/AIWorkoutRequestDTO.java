package com.lifesync.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public class AIWorkoutRequestDTO {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Objective is required")
    private String objective; // "weight_loss", "muscle_gain", "endurance", "general_fitness"

    @NotNull(message = "Difficulty is required")
    @Min(value = 1, message = "Difficulty must be at least 1")
    @Max(value = 5, message = "Difficulty cannot exceed 5")
    private Integer difficulty;

    @Min(value = 1, message = "Duration must be at least 1 week")
    @Max(value = 12, message = "Duration cannot exceed 12 weeks")
    private Integer durationWeeks;

    private Integer daysPerWeek;

    private String equipment; // "none", "basic", "full_gym"

    private String focusArea; // "full_body", "upper_body", "lower_body", "specific_muscle"

    public AIWorkoutRequestDTO() {
    }

    public AIWorkoutRequestDTO(Long userId, String objective, Integer difficulty) {
        this.userId = userId;
        this.objective = objective;
        this.difficulty = difficulty;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getDurationWeeks() {
        return durationWeeks;
    }

    public void setDurationWeeks(Integer durationWeeks) {
        this.durationWeeks = durationWeeks;
    }

    public Integer getDaysPerWeek() {
        return daysPerWeek;
    }

    public void setDaysPerWeek(Integer daysPerWeek) {
        this.daysPerWeek = daysPerWeek;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getFocusArea() {
        return focusArea;
    }

    public void setFocusArea(String focusArea) {
        this.focusArea = focusArea;
    }
}
