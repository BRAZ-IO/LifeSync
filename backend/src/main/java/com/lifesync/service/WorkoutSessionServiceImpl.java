package com.lifesync.service.impl;

import com.lifesync.entity.WorkoutSession;
import com.lifesync.entity.Workout;
import com.lifesync.entity.User;
import com.lifesync.dto.WorkoutSessionDTO;
import com.lifesync.repository.WorkoutSessionRepository;
import com.lifesync.repository.WorkoutRepository;
import com.lifesync.repository.UserRepository;
import com.lifesync.service.WorkoutSessionService;
import com.lifesync.utility.BackendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WorkoutSessionServiceImpl implements WorkoutSessionService {

    @Autowired
    private WorkoutSessionRepository workoutSessionRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public WorkoutSession startWorkoutSession(WorkoutSessionDTO sessionDTO, Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new BackendException("User not found with id: " + userId));
        
        Workout workout = workoutRepository.findById(sessionDTO.getWorkoutId())
            .orElseThrow(() -> new BackendException("Workout not found with id: " + sessionDTO.getWorkoutId()));
        
        WorkoutSession session = new WorkoutSession();
        session.setStartTime(sessionDTO.getStartTime());
        session.setWorkout(workout);
        session.setUser(user);
        session.setCompleted(false);
        session.setIntensity(sessionDTO.getIntensity());
        session.setNotes(sessionDTO.getNotes());
        
        return workoutSessionRepository.save(session);
    }

    @Override
    public WorkoutSession completeWorkoutSession(Long id, Long userId) {
        WorkoutSession session = workoutSessionRepository.findByUserIdOrderByStartTimeDesc(userId).stream()
            .filter(s -> s.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new BackendException("Workout session not found with id: " + id));
        
        session.completeSession();
        return workoutSessionRepository.save(session);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkoutSession> getWorkoutSessionById(Long id, Long userId) {
        return workoutSessionRepository.findByUserIdOrderByStartTimeDesc(userId).stream()
            .filter(s -> s.getId().equals(id))
            .findFirst();
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkoutSession> getUserWorkoutSessions(Long userId) {
        return workoutSessionRepository.findByUserIdOrderByStartTimeDesc(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkoutSession> getWorkoutSessionsByWorkout(Long userId, Long workoutId) {
        return workoutSessionRepository.findByUserIdAndWorkoutIdOrderByStartTimeDesc(userId, workoutId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkoutSession> getCompletedWorkoutSessions(Long userId) {
        return workoutSessionRepository.findByUserIdAndCompletedOrderByStartTimeDesc(userId, true);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkoutSession> getWorkoutSessionsByDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return workoutSessionRepository.findByUserIdAndStartTimeBetweenOrderByStartTimeDesc(userId, startDate, endDate);
    }

    @Override
    public WorkoutSession updateWorkoutSession(Long id, WorkoutSessionDTO sessionDTO, Long userId) {
        WorkoutSession session = workoutSessionRepository.findByUserIdOrderByStartTimeDesc(userId).stream()
            .filter(s -> s.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new BackendException("Workout session not found with id: " + id));
        
        session.setStartTime(sessionDTO.getStartTime());
        session.setEndTime(sessionDTO.getEndTime());
        session.setDuration(sessionDTO.getDuration());
        session.setCaloriesBurned(sessionDTO.getCaloriesBurned());
        session.setIntensity(sessionDTO.getIntensity());
        session.setNotes(sessionDTO.getNotes());
        session.setCompleted(sessionDTO.getCompleted());
        
        return workoutSessionRepository.save(session);
    }

    @Override
    public void deleteWorkoutSession(Long id, Long userId) {
        WorkoutSession session = workoutSessionRepository.findByUserIdOrderByStartTimeDesc(userId).stream()
            .filter(s -> s.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new BackendException("Workout session not found with id: " + id));
        workoutSessionRepository.delete(session);
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalWorkoutSessionsCount(Long userId) {
        return workoutSessionRepository.findByUserIdOrderByStartTimeDesc(userId).size();
    }

    @Override
    @Transactional(readOnly = true)
    public long getCompletedWorkoutSessionsCount(Long userId) {
        return workoutSessionRepository.findByUserIdAndCompletedOrderByStartTimeDesc(userId, true).size();
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getTotalDuration(Long userId) {
        List<WorkoutSession> sessions = workoutSessionRepository.findByUserIdAndCompletedOrderByStartTimeDesc(userId, true);
        return sessions.stream()
            .filter(session -> session.getDuration() != null)
            .mapToInt(WorkoutSession::getDuration)
            .sum();
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getTotalCaloriesBurned(Long userId) {
        List<WorkoutSession> sessions = workoutSessionRepository.findByUserIdAndCompletedOrderByStartTimeDesc(userId, true);
        return sessions.stream()
            .filter(session -> session.getCaloriesBurned() != null)
            .mapToInt(WorkoutSession::getCaloriesBurned)
            .sum();
    }

    @Override
    @Transactional(readOnly = true)
    public Double getAverageIntensity(Long userId) {
        List<WorkoutSession> sessions = workoutSessionRepository.findByUserIdAndCompletedOrderByStartTimeDesc(userId, true);
        return sessions.stream()
            .filter(session -> session.getIntensity() != null)
            .mapToDouble(WorkoutSession::getIntensity)
            .average()
            .orElse(0.0);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getAverageSessionDuration(Long userId) {
        List<WorkoutSession> sessions = workoutSessionRepository.findByUserIdAndCompletedOrderByStartTimeDesc(userId, true);
        return (int) sessions.stream()
            .filter(session -> session.getDuration() != null)
            .mapToInt(WorkoutSession::getDuration)
            .average()
            .orElse(0.0);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkoutSession> getRecentWorkoutSessions(Long userId, int limit) {
        if (limit <= 0) {
            return workoutSessionRepository.findByUserIdOrderByStartTimeDesc(userId).stream()
                .limit(10)
                .toList();
        }
        return workoutSessionRepository.findByUserIdOrderByStartTimeDesc(userId).stream()
            .limit(limit)
            .toList();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<WorkoutSession> getRecentCompletedSessions(Long userId) {
        return workoutSessionRepository.findByUserIdAndCompletedOrderByStartTimeDesc(userId, true).stream()
            .limit(10)
            .toList();
    }
    
    @Override
    @Transactional(readOnly = true)
    public long getCompletedSessionsCountSince(Long userId, LocalDateTime since) {
        return workoutSessionRepository.findCompletedSessionsSince(userId, since).size();
    }
}
