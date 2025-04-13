package com.example.mapper;

import com.example.entity.LearningProgress;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface LearningProgressMapper {

    int insert(LearningProgress learningProgress);

    int update(LearningProgress learningProgress);

    int deleteById(@Param("id") Integer id);

    Optional<LearningProgress> findById(@Param("id") Integer id);

    List<LearningProgress> findAll();

    List<LearningProgress> findByStudentId(@Param("studentId") Long studentId);

    Optional<LearningProgress> findByStudentIdAndCourseId(
            @Param("studentId") Long studentId,
            @Param("courseId") Long courseId);
}