package com.lifesync.service.impl;

import com.lifesync.entity.Workout;
import com.lifesync.entity.User;
import com.lifesync.dto.WorkoutDTO;
import com.lifesync.repository.WorkoutRepository;
import com.lifesync.repository.UserRepository;
import com.lifesync.service.WorkoutService;
import com.lifesync.utility.BackendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WorkoutServiceImpl implements WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public Workout createWorkout(WorkoutDTO workoutDTO, Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new BackendException("User not found with id: " + userId));
        
        // Check if workout name already exists for this user
        if (workoutRepository.existsByUserIdAndName(userId, workoutDTO.getName())) {
            throw new BackendException("Workout with name '" + workoutDTO.getName() + "' already exists");
        }
        
        Workout workout = new Workout();
        workout.setName(workoutDTO.getName());
        workout.setDescription(workoutDTO.getDescription());
        workout.setDuration(workoutDTO.getDuration());
        workout.setDifficulty(workoutDTO.getDifficulty());
        workout.setMuscleGroup(workoutDTO.getMuscleGroup());
        workout.setEquipment(workoutDTO.getEquipment());
        workout.setCaloriesEstimate(workoutDTO.getCaloriesEstimate());
        workout.setUser(user);
        
        return workoutRepository.save(workout);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Workout> getWorkoutById(Long id, Long userId) {
        return workoutRepository.findByIdAndUserId(id, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Workout> getUserWorkouts(Long userId) {
        return workoutRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Workout> getWorkoutsByMuscleGroup(Long userId, String muscleGroup) {
        return workoutRepository.findByUserIdAndMuscleGroupOrderByCreatedAtDesc(userId, muscleGroup);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Workout> getWorkoutsByDifficulty(Long userId, Integer difficulty) {
        return workoutRepository.findByUserIdAndDifficultyOrderByCreatedAtDesc(userId, difficulty);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Workout> getWorkoutsByEquipment(Long userId, String equipment) {
        return workoutRepository.findByUserIdAndEquipmentContainingOrderByCreatedAtDesc(userId, equipment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Workout> getWorkoutsByDurationRange(Long userId, Integer minDuration, Integer maxDuration) {
        return workoutRepository.findByUserIdAndDurationBetween(userId, minDuration, maxDuration);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getMuscleGroups(Long userId) {
        return workoutRepository.findDistinctMuscleGroupsByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getEquipment(Long userId) {
        return workoutRepository.findDistinctEquipmentByUserId(userId);
    }

    @Override
    public Workout updateWorkout(Long id, WorkoutDTO workoutDTO, Long userId) {
        Workout workout = workoutRepository.findByIdAndUserId(id, userId)
            .orElseThrow(() -> new BackendException("Workout not found with id: " + id));
        
        // Check if name is being changed and if it already exists
        if (!workout.getName().equals(workoutDTO.getName()) && 
            workoutRepository.existsByUserIdAndName(userId, workoutDTO.getName())) {
            throw new BackendException("Workout with name '" + workoutDTO.getName() + "' already exists");
        }
        
        workout.setName(workoutDTO.getName());
        workout.setDescription(workoutDTO.getDescription());
        workout.setDuration(workoutDTO.getDuration());
        workout.setDifficulty(workoutDTO.getDifficulty());
        workout.setMuscleGroup(workoutDTO.getMuscleGroup());
        workout.setEquipment(workoutDTO.getEquipment());
        workout.setCaloriesEstimate(workoutDTO.getCaloriesEstimate());
        
        return workoutRepository.save(workout);
    }

    @Override
    public void deleteWorkout(Long id, Long userId) {
        Workout workout = workoutRepository.findByIdAndUserId(id, userId)
            .orElseThrow(() -> new BackendException("Workout not found with id: " + id));
        workoutRepository.delete(workout);
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalWorkoutsCount(Long userId) {
        return workoutRepository.countTotalWorkoutsByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public long getWorkoutsCountByDifficulty(Long userId, Integer difficulty) {
        return workoutRepository.countWorkoutsByDifficulty(userId, difficulty);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getAverageDuration(Long userId) {
        return workoutRepository.getAverageDurationByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getTotalDuration(Long userId) {
        return workoutRepository.getTotalDurationByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUserIdAndName(Long userId, String name) {
        return workoutRepository.existsByUserIdAndName(userId, name);
    }
}
