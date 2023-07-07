package com.example.lesson_89.repository;

import com.example.lesson_89.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentCourseMarkRepository extends CrudRepository<CourseEntity, Integer> {
}
