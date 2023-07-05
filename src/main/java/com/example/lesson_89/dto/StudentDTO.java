package com.example.lesson_89.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentDTO {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private Integer level;
    private String gender;
    private LocalDateTime createdDate;
}
