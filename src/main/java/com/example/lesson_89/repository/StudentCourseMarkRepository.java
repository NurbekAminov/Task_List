package com.example.lesson_89.repository;

import com.example.lesson_89.entity.CourseEntity;
import com.example.lesson_89.entity.StudentCourseMarkEntity;
import com.example.lesson_89.entity.StudentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StudentCourseMarkRepository extends CrudRepository<StudentCourseMarkEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update StudentCourseMarkEntity as s set s.studentId =:studentId, s.courseId=:courseId, s.mark=:mark where s.id  =:id ")
    int update(@Param("id") Integer id, @Param("studentId") Integer studentId, @Param("courseId") Integer courseId, @Param("mark") Integer mark);
}
