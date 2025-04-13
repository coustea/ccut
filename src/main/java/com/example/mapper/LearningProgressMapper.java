package com.example.mapper;

import com.example.entity.LearningProgress;

import java.util.List;

public interface LearningProgressMapper {

    void insert(LearningProgress learningProgress);

    void update(LearningProgress learningProgress);

    void deleteById(Integer id);

    LearningProgress findById(Integer id);

    List<LearningProgress> findAll();

    List<LearningProgress> findByStudentId(Long studentId);
}