package com.example.service;

import com.example.entity.LearningProgress;
import com.example.mapper.LearningProgressMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearningProgressService {

    @Resource
    LearningProgressMapper learningProgressMapper;

    //添加学习进度记录
    public void addLearningProgress(LearningProgress learningProgress) {
        learningProgressMapper.insert(learningProgress);
    }
    //更新学习进度记录
    public void updateLearningProgress(LearningProgress learningProgress) {
        learningProgressMapper.updateById(learningProgress);

    }
    //查看学习进度记录
    public List<LearningProgress> findAll(LearningProgress learningProgress) {
        return learningProgressMapper.findAll(learningProgress);
    }
    public List<LearningProgress> findByStudentId(Long studentId) {
        return learningProgressMapper.findByStudentId(studentId);
    }
    public Optional<LearningProgress> findByStudentIdAndCourseId(Long studentId, Long courseId) {
        return learningProgressMapper.findByStudentIdAndCourseId(studentId, courseId);
    }
}
