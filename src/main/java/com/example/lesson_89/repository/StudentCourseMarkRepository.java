package com.example.lesson_89.repository;

import com.example.lesson_89.entity.CourseEntity;
import com.example.lesson_89.entity.StudentCourseMarkEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentCourseMarkRepository extends CrudRepository<StudentCourseMarkEntity, Integer> {
}
