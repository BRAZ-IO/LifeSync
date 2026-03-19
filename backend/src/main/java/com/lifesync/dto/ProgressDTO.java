package com.lifesync.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProgressDTO {

    @NotNull(message = "Date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Min(value = 30, message = "Weight must be at least 30 kg")
    @Max(value = 300, message = "Weight cannot exceed 300 kg")
    private Double weight;

    @Min(value = 1, message = "Body fat must be at least 1%")
    @Max(value = 70, message = "Body fat cannot exceed 70%")
    private Integer bodyFat;

    @Min(value = 10, message = "Muscle mass must be at least 10 kg")
    @Max(value = 150, message = "Muscle mass cannot exceed 150 kg")
    private Double muscleMass;

    private Map<String, Double> measurements; // chest, waist, arms, legs, etc.

    private String photos; // JSON array of photo URLs

    private String notes;

    @Min(value = 30, message = "Body water must be at least 30%")
    @Max(value = 80, message = "Body water cannot exceed 80%")
    private Double bodyWater;

    @Min(value = 1, message = "Visceral fat must be at least 1")
    @Max(value = 30, message = "Visceral fat cannot exceed 30")
    private Integer visceralFat;

    @Min(value = 10, message = "Metabolic age must be at least 10")
    @Max(value = 100, message = "Metabolic age cannot exceed 100")
    private Integer metabolicAge;

    @Min(value = 1, message = "Bone mass must be at least 1 kg")
    @Max(value = 10, message = "Bone mass cannot exceed 10 kg")
    private Double boneMass;

    @Min(value = 1, message = "Muscle quality must be at least 1")
    @Max(value = 10, message = "Muscle quality cannot exceed 10")
    private Integer muscleQuality;

    public ProgressDTO() {
    }

    public ProgressDTO(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(Integer bodyFat) {
        this.bodyFat = bodyFat;
    }

    public Double getMuscleMass() {
        return muscleMass;
    }

    public void setMuscleMass(Double muscleMass) {
        this.muscleMass = muscleMass;
    }

    public Map<String, Double> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Map<String, Double> measurements) {
        this.measurements = measurements;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getBodyWater() {
        return bodyWater;
    }

    public void setBodyWater(Double bodyWater) {
        this.bodyWater = bodyWater;
    }

    public Integer getVisceralFat() {
        return visceralFat;
    }

    public void setVisceralFat(Integer visceralFat) {
        this.visceralFat = visceralFat;
    }

    public Integer getMetabolicAge() {
        return metabolicAge;
    }

    public void setMetabolicAge(Integer metabolicAge) {
        this.metabolicAge = metabolicAge;
    }

    public Double getBoneMass() {
        return boneMass;
    }

    public void setBoneMass(Double boneMass) {
        this.boneMass = boneMass;
    }

    public Integer getMuscleQuality() {
        return muscleQuality;
    }

    public void setMuscleQuality(Integer muscleQuality) {
        this.muscleQuality = muscleQuality;
    }
}
