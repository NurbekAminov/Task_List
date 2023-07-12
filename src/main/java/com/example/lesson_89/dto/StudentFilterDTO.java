package com.example.lesson_89.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class StudentFilterDTO {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private Integer level;
    private Enum gender;
    private LocalDate createdDate;
}
