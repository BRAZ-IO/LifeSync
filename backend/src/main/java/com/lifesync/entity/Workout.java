package com.lifesync.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "workouts")
@EntityListeners(AuditingEntityListener.class)
public class Workout {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false)
    private Integer duration; // minutos
    
    @Column(nullable = false)
    private Integer difficulty; // 1-5 scale
    
    @Column(name = "muscle_group")
    private String muscleGroup; // peito, pernas, costas, etc.
    
    @Column(name = "equipment")
    private String equipment; // pesos, máquina, corpo, etc.
    
    @Column(name = "calories_estimate")
    private Integer caloriesEstimate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WorkoutSession> sessions;
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    public Workout() {
    }
    
    public Workout(String name, String description, Integer duration, Integer difficulty, User user) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.difficulty = difficulty;
        this.user = user;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public List<WorkoutSession> getSessions() {
        return sessions;
    }
    
    public void setSessions(List<WorkoutSession> sessions) {
        this.sessions = sessions;
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
}
