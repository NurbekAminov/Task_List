package com.example.lesson_89.service;

import com.example.lesson_89.dto.CourseDTO;
import com.example.lesson_89.dto.StudentDTO;
import com.example.lesson_89.entity.CourseEntity;
import com.example.lesson_89.entity.StudentEntity;
import com.example.lesson_89.exp.ItemNotFoundException;
import com.example.lesson_89.repository.CourseRepository;
import com.example.lesson_89.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    public CourseDTO add(CourseDTO dto) {

        CourseEntity entity = new CourseEntity();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDuration(dto.getDuration());
        entity.setCreatedDate(LocalDateTime.now());

        courseRepository.save(entity);
        dto.setId(entity.getId());

        return dto;
    }

    public CourseDTO getById(Integer id) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            throw  new ItemNotFoundException("Student not found");
        }
        CourseEntity entity = optional.get();
        return toDTO(entity);

//        Optional<StudentEntity> optional = studentRepository.findById(id);
//        return optional.map(entity -> toDTO(entity)).orElseThrow(() -> {
//            throw new ItemNotFoundException("Student not found");
//        });
    }

    public Boolean delete(Integer id) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            throw  new ItemNotFoundException("Course not found");
        }
        courseRepository.deleteById(id);
        return true;
    }

    public Boolean update(Integer id, CourseDTO course) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            throw  new ItemNotFoundException("Course not found");
        }
        courseRepository.deleteById(id);
        add(course);
        return true;
    }

    public CourseDTO toDTO(CourseEntity entity){
        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        entity.setPrice(dto.getPrice());
        entity.setDuration(dto.getDuration());
        entity.setCreatedDate(LocalDateTime.now());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
