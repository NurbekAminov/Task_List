package com.example.lesson_89.service;

import com.example.lesson_89.dto.StudentCourseMarkDTO;
import com.example.lesson_89.dto.StudentDTO;
import com.example.lesson_89.entity.CourseEntity;
import com.example.lesson_89.entity.StudentCourseMarkEntity;
import com.example.lesson_89.entity.StudentEntity;
import com.example.lesson_89.repository.StudentCourseMarkRepository;
import com.example.lesson_89.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentCourseMarkService {
    @Autowired
    private StudentCourseMarkRepository repository;
    @Autowired
    private StudentRepository studentRepository;

    public StudentCourseMarkDTO add(StudentCourseMarkDTO dto){
        StudentCourseMarkEntity entity = toEntity(dto);
        repository.save(entity);
        dto.setId(entity.getId());

        return dto;
    }
    public Boolean updateMark(Integer id, StudentCourseMarkDTO markDTO) {
        int effectedRows = repository.updateMark(id, markDTO.getStudentId(), markDTO.getCourseId(), markDTO.getMark());
        return effectedRows != 0;
    }

    private StudentCourseMarkEntity toEntity(StudentCourseMarkDTO dto) {
        StudentEntity student = new StudentEntity();
        student.setId(dto.getStudentId());

        CourseEntity course = new CourseEntity();
        course.setId(dto.getCourseId());

        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();
        entity.setStudentId(student);
        entity.setCourseId(course);
        entity.setMark(dto.getMark());
        entity.setCreatedDate(LocalDateTime.now());

        return entity;
    }
}
