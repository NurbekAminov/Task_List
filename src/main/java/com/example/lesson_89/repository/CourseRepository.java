package com.example.lesson_89.repository;

import com.example.lesson_89.entity.CourseEntity;
import com.example.lesson_89.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {

}
