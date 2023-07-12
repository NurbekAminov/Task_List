package com.example.lesson_89.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CourseFilterDTO {
    private Integer id;
    private String name;
    private Integer price;
    private Integer duration;
    private LocalDateTime createdDate;
}
