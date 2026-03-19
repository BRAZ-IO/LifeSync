package com.lifesync.service;

import com.lifesync.entity.Habit;
import com.lifesync.dto.HabitDTO;

import java.util.List;
import java.util.Optional;

public interface HabitService {
    
    Habit createHabit(HabitDTO habitDTO, Long userId);
    
    Optional<Habit> getHabitById(Long id, Long userId);
    
    List<Habit> getUserHabits(Long userId);
    
    List<Habit> getCompletedHabits(Long userId);
    
    List<Habit> getPendingHabits(Long userId);
    
    Habit updateHabit(Long id, HabitDTO habitDTO, Long userId);
    
    void deleteHabit(Long id, Long userId);
    
    Habit completeHabit(Long id, Long userId);
    
    List<Habit> getHabitsByFrequency(Long userId, Integer frequency);
    
    long getTotalHabitsCount(Long userId);
    
    long getCompletedHabitsCount(Long userId);
}
