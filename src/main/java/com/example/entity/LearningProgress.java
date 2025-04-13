package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LearningProgress {
    @Getter
    public enum Status {
        not_started(0), in_progress(1), paused(2), completed(3);

        private final int code;
        Status(int code) {
            this.code = code;
        }

        public static Status fromCode(int code) {
            for (Status status : Status.values()) {
                if (status.getCode() == code) {
                    return status;
                }
            }
            throw new IllegalArgumentException("无效状态码： " + code);
        }
    }

    private Integer id;
    private BigDecimal completionPercentage;
    private Status status;
    private Long studentId;
    private Long courseId;

    // 关联对象
    private Student student;
    private Course course;
}