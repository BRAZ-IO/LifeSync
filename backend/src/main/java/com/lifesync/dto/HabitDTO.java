package com.lifesync.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

public class HabitDTO {

    @NotBlank(message = "Habit name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 minute")
    @Max(value = 480, message = "Duration cannot exceed 480 minutes (8 hours)")
    private Integer duration;

    @NotNull(message = "Frequency is required")
    @Min(value = 1, message = "Frequency must be at least 1 time per week")
    @Max(value = 7, message = "Frequency cannot exceed 7 times per week")
    private Integer frequency;

    private Boolean completed;

    public HabitDTO() {
    }

    public HabitDTO(String name, String description, Integer duration, Integer frequency) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.frequency = frequency;
        this.completed = false;
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

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
