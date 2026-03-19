package com.lifesync.service.impl;

import com.lifesync.entity.Habit;
import com.lifesync.dto.HabitDTO;
import com.lifesync.repository.HabitRepository;
import com.lifesync.repository.UserRepository;
import com.lifesync.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HabitServiceImpl implements HabitService {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Habit createHabit(HabitDTO habitDTO, Long userId) {
        // TODO: Implement habit creation logic
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Habit> getHabitById(Long id, Long userId) {
        return habitRepository.findByIdAndUserId(id, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Habit> getUserHabits(Long userId) {
        return habitRepository.findByUserId(userId);
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
        // TODO: Implement habit update logic
        return null;
    }

    @Override
    public void deleteHabit(Long id, Long userId) {
        // TODO: Implement habit deletion logic
    }

    @Override
    public Habit completeHabit(Long id, Long userId) {
        // TODO: Implement habit completion logic
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Habit> getHabitsByFrequency(Long userId, Integer frequency) {
        // TODO: Implement frequency-based habit filtering
        return null;
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
