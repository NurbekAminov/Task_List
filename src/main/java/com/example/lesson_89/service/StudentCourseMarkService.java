package com.example.lesson_89.service;

import com.example.lesson_89.dto.StudentCourseMarkDTO;
import com.example.lesson_89.dto.StudentDTO;
import com.example.lesson_89.entity.CourseEntity;
import com.example.lesson_89.entity.StudentCourseMarkEntity;
import com.example.lesson_89.entity.StudentEntity;
import com.example.lesson_89.exp.ItemNotFoundException;
import com.example.lesson_89.repository.StudentCourseMarkRepository;
import com.example.lesson_89.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
        int effectedRows = repository.update(id, markDTO.getStudentId(), markDTO.getCourseId(), markDTO.getMark());
        return effectedRows != 0;
    }

    public StudentCourseMarkDTO getById(Integer id) {
        Optional<StudentCourseMarkEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Student not found");
        }
        StudentCourseMarkEntity entity = optional.get();
        return toDTO(entity);
    }
    public Boolean delete(Integer id) {
        Optional<StudentCourseMarkEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
    public List<StudentCourseMarkDTO> getAll() {
        Iterable<StudentCourseMarkEntity> iterable = repository.findAll();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }

    public String getMarkByDate(Integer id, LocalDate date) {
        StudentCourseMarkDTO dto = getById(id);

        return null;
    }

    private StudentCourseMarkDTO toDTO(StudentCourseMarkEntity entity) {
        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
        dto.setId(entity.getId());
        dto.setStudentId(entity.getStudentId().getId());
        dto.setCourseId(entity.getCourseId().getId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(LocalDateTime.now());
        return dto;
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
