package com.lifesync.service.impl;

import com.lifesync.entity.Progress;
import com.lifesync.entity.User;
import com.lifesync.dto.ProgressDTO;
import com.lifesync.repository.ProgressRepository;
import com.lifesync.repository.UserRepository;
import com.lifesync.service.ProgressService;
import com.lifesync.utility.BackendException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProgressServiceImpl implements ProgressService {

    @Autowired
    private ProgressRepository progressRepository;

    @Autowired
    private UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Progress createProgress(ProgressDTO progressDTO, Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new BackendException("User not found with id: " + userId));
        
        // Check if progress already exists for this date
        if (progressRepository.existsByUserIdAndDate(userId, progressDTO.getDate())) {
            throw new BackendException("Progress already exists for date: " + progressDTO.getDate());
        }
        
        Progress progress = new Progress(progressDTO.getDate(), user);
        progress.setWeight(progressDTO.getWeight());
        progress.setBodyFat(progressDTO.getBodyFat());
        progress.setMuscleMass(progressDTO.getMuscleMass());
        progress.setBodyWater(progressDTO.getBodyWater());
        progress.setVisceralFat(progressDTO.getVisceralFat());
        progress.setMetabolicAge(progressDTO.getMetabolicAge());
        progress.setBoneMass(progressDTO.getBoneMass());
        progress.setMuscleQuality(progressDTO.getMuscleQuality());
        progress.setNotes(progressDTO.getNotes());
        
        // Convert measurements map to JSON
        if (progressDTO.getMeasurements() != null) {
            try {
                progress.setMeasurements(objectMapper.writeValueAsString(progressDTO.getMeasurements()));
            } catch (Exception e) {
                throw new BackendException("Error converting measurements to JSON");
            }
        }
        
        progress.setPhotos(progressDTO.getPhotos());
        
        return progressRepository.save(progress);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Progress> getProgressById(Long id, Long userId) {
        return progressRepository.findByIdAndUserId(id, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Progress> getProgressByDate(Long userId, LocalDate date) {
        return progressRepository.findByUserIdAndDate(userId, date);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Progress> getUserProgress(Long userId) {
        return progressRepository.findByUserIdOrderByDateDesc(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Progress> getProgressInPeriod(Long userId, LocalDate startDate, LocalDate endDate) {
        return progressRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Progress> getRecentProgress(Long userId, int limit) {
        if (limit <= 0) {
            return progressRepository.findTop30ByUserIdOrderByDateDesc(userId);
        }
        return progressRepository.findByUserIdOrderByDateDesc(userId).stream()
            .limit(limit)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Progress> getWeightHistory(Long userId) {
        return progressRepository.findWeightHistoryByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Progress> getBodyFatHistory(Long userId) {
        return progressRepository.findBodyFatHistoryByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Progress> getMuscleMassHistory(Long userId) {
        return progressRepository.findMuscleMassHistoryByUserId(userId);
    }

    @Override
    public Progress updateProgress(Long id, ProgressDTO progressDTO, Long userId) {
        Progress progress = progressRepository.findByIdAndUserId(id, userId)
            .orElseThrow(() -> new BackendException("Progress not found with id: " + id));
        
        // Check if date is being changed and if it conflicts with existing record
        if (!progress.getDate().equals(progressDTO.getDate()) && 
            progressRepository.existsByUserIdAndDate(userId, progressDTO.getDate())) {
            throw new BackendException("Progress already exists for date: " + progressDTO.getDate());
        }
        
        progress.setDate(progressDTO.getDate());
        progress.setWeight(progressDTO.getWeight());
        progress.setBodyFat(progressDTO.getBodyFat());
        progress.setMuscleMass(progressDTO.getMuscleMass());
        progress.setBodyWater(progressDTO.getBodyWater());
        progress.setVisceralFat(progressDTO.getVisceralFat());
        progress.setMetabolicAge(progressDTO.getMetabolicAge());
        progress.setBoneMass(progressDTO.getBoneMass());
        progress.setMuscleQuality(progressDTO.getMuscleQuality());
        progress.setNotes(progressDTO.getNotes());
        
        // Convert measurements map to JSON
        if (progressDTO.getMeasurements() != null) {
            try {
                progress.setMeasurements(objectMapper.writeValueAsString(progressDTO.getMeasurements()));
            } catch (Exception e) {
                throw new BackendException("Error converting measurements to JSON");
            }
        }
        
        progress.setPhotos(progressDTO.getPhotos());
        
        return progressRepository.save(progress);
    }

    @Override
    public void deleteProgress(Long id, Long userId) {
        Progress progress = progressRepository.findByIdAndUserId(id, userId)
            .orElseThrow(() -> new BackendException("Progress not found with id: " + id));
        progressRepository.delete(progress);
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalProgressCount(Long userId) {
        return progressRepository.countTotalProgressByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public long getProgressCountSince(Long userId, LocalDate startDate) {
        return progressRepository.countProgressSince(userId, startDate);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Progress> getLatestProgress(Long userId) {
        return progressRepository.getLatestProgressByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Progress> getFirstProgress(Long userId) {
        return progressRepository.getFirstProgressByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getMinWeight(Long userId) {
        return progressRepository.getMinWeightByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getMaxWeight(Long userId) {
        return progressRepository.getMaxWeightByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getAverageWeightInPeriod(Long userId, LocalDate startDate, LocalDate endDate) {
        return progressRepository.getAverageWeightInPeriod(userId, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getWeightChange(Long userId) {
        Optional<Progress> first = getFirstProgress(userId);
        Optional<Progress> latest = getLatestProgress(userId);
        
        if (first.isPresent() && latest.isPresent() && 
            first.get().hasWeightData() && latest.get().hasWeightData()) {
            return latest.get().getWeight() - first.get().getWeight();
        }
        
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Double getBodyFatChange(Long userId) {
        Optional<Progress> first = getFirstProgress(userId);
        Optional<Progress> latest = getLatestProgress(userId);
        
        if (first.isPresent() && latest.isPresent() && 
            first.get().hasBodyFatData() && latest.get().hasBodyFatData()) {
            return (double) (latest.get().getBodyFat() - first.get().getBodyFat());
        }
        
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Double getMuscleMassChange(Long userId) {
        Optional<Progress> first = getFirstProgress(userId);
        Optional<Progress> latest = getLatestProgress(userId);
        
        if (first.isPresent() && latest.isPresent() && 
            first.get().getMuscleMass() != null && latest.get().getMuscleMass() != null) {
            return latest.get().getMuscleMass() - first.get().getMuscleMass();
        }
        
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUserIdAndDate(Long userId, LocalDate date) {
        return progressRepository.existsByUserIdAndDate(userId, date);
    }
}
