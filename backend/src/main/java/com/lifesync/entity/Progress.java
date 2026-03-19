package com.lifesync.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "progress")
@EntityListeners(AuditingEntityListener.class)
public class Progress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Column(name = "weight")
    private Double weight; // kg
    
    @Column(name = "body_fat")
    private Integer bodyFat; // percentage
    
    @Column(name = "muscle_mass")
    private Double muscleMass; // kg
    
    @Column(columnDefinition = "TEXT")
    private String measurements; // JSON: {"chest":95,"waist":80,"arms":40,"legs":60}
    
    @Column(columnDefinition = "TEXT")
    private String photos; // JSON array de URLs das fotos
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    @Column(name = "body_water")
    private Double bodyWater; // percentage
    
    @Column(name = "visceral_fat")
    private Integer visceralFat; // percentage
    
    @Column(name = "metabolic_age")
    private Integer metabolicAge;
    
    @Column(name = "bone_mass")
    private Double boneMass; // kg
    
    @Column(name = "muscle_quality")
    private Integer muscleQuality; // 1-10 scale
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    public Progress() {
    }
    
    public Progress(LocalDate date, User user) {
        this.date = date;
        this.user = user;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    public String getMeasurements() {
        return measurements;
    }
    
    public void setMeasurements(String measurements) {
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
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
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
    public boolean hasWeightData() {
        return weight != null && weight > 0;
    }
    
    public boolean hasBodyFatData() {
        return bodyFat != null && bodyFat > 0;
    }
    
    public boolean hasCompleteData() {
        return hasWeightData() && hasBodyFatData() && muscleMass != null;
    }
}
