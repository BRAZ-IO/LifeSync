package com.lifesync.service.impl;

import com.lifesync.entity.Habit;
import com.lifesync.entity.HabitCompletion;
import com.lifesync.entity.User;
import com.lifesync.dto.HabitDTO;
import com.lifesync.repository.HabitCompletionRepository;
import com.lifesync.repository.HabitRepository;
import com.lifesync.repository.UserRepository;
import com.lifesync.service.HabitService;
import com.lifesync.utility.BackendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HabitServiceImpl implements HabitService {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HabitCompletionRepository completionRepository;

    @Override
    public Habit createHabit(HabitDTO habitDTO, Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new BackendException("User not found with id: " + userId));
        
        Habit habit = new Habit();
        habit.setName(habitDTO.getName());
        habit.setDescription(habitDTO.getDescription());
        habit.setDuration(habitDTO.getDuration());
        habit.setFrequency(habitDTO.getFrequency());
        habit.setCompleted(habitDTO.getCompleted() != null ? habitDTO.getCompleted() : false);
        habit.setUser(user);
        
        return habitRepository.save(habit);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Habit> getHabitById(Long id, Long userId) {
        return habitRepository.findByIdAndUserId(id, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Habit> getUserHabits(Long userId) {
        return habitRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Habit> getCompletedHabits(Long userId) {
        return habitRepository.findByUserIdAndCompleted(userId, true);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Habit> getPendingHabits(Long userId) {
        return habitRepository.findByUserIdAndCompleted(userId, false);
    }

    @Override
    public Habit updateHabit(Long id, HabitDTO habitDTO, Long userId) {
        Habit habit = habitRepository.findByIdAndUserId(id, userId)
            .orElseThrow(() -> new BackendException("Habit not found with id: " + id));
        
        habit.setName(habitDTO.getName());
        habit.setDescription(habitDTO.getDescription());
        habit.setDuration(habitDTO.getDuration());
        habit.setFrequency(habitDTO.getFrequency());
        if (habitDTO.getCompleted() != null) {
            habit.setCompleted(habitDTO.getCompleted());
        }
        
        return habitRepository.save(habit);
    }

    @Override
    public void deleteHabit(Long id, Long userId) {
        Habit habit = habitRepository.findByIdAndUserId(id, userId)
            .orElseThrow(() -> new BackendException("Habit not found with id: " + id));
        habitRepository.delete(habit);
    }

    @Override
    public Habit completeHabit(Long id, Long userId) {
        Habit habit = habitRepository.findByIdAndUserId(id, userId)
            .orElseThrow(() -> new BackendException("Habit not found with id: " + id));
        
        habit.setCompleted(true);
        
        // Create completion record
        HabitCompletion completion = new HabitCompletion();
        completion.setHabit(habit);
        completion.setCompletedAt(LocalDateTime.now());
        completion.setNotes("Completed via API");
        completionRepository.save(completion);
        
        return habitRepository.save(habit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Habit> getHabitsByFrequency(Long userId, Integer frequency) {
        return habitRepository.findByUserId(userId).stream()
            .filter(h -> h.getFrequency().equals(frequency))
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalHabitsCount(Long userId) {
        return habitRepository.countTotalHabitsByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public long getCompletedHabitsCount(Long userId) {
        return habitRepository.countCompletedHabitsByUserId(userId);
    }
}
