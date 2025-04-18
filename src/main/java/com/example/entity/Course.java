package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    
    @Getter
    public enum Status {
        PENDING(0), ACTIVE(1), COMPLETED(2), CANCELLED(3);

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
            throw new IllegalArgumentException("Invalid status code: " + code);
        }
    }

    private Integer id;
    private String title;
    private String term;
    private Status status;
    private Long teacherId;

    private Teacher teacher;
}
