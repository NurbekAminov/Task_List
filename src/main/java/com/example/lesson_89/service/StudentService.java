package com.example.lesson_89.service;
import com.example.lesson_89.dto.StudentDTO;
import com.example.lesson_89.entity.StudentEntity;
import com.example.lesson_89.exp.AppBadRequestException;
import com.example.lesson_89.exp.ItemNotFoundException;
import com.example.lesson_89.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public StudentDTO add(StudentDTO dto) {
        check(dto); // validate inputs

        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        dto.setLevel(entity.getLevel());
        dto.setGender(entity.getGender());
        entity.setCreatedDate(LocalDateTime.now());

        studentRepository.save(entity);
        dto.setId(entity.getId());

        return dto;
    }

    public List<StudentDTO> addAll(List<StudentDTO> list) {
        for (StudentDTO dto : list) {
            StudentEntity entity = new StudentEntity();
            entity.setName(dto.getName());
            entity.setSurname(dto.getSurname());
            entity.setAge(dto.getAge());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            entity.setCreatedDate(LocalDateTime.now());
            studentRepository.save(entity);
            dto.setId(entity.getId());
        }
        return list;
    }

    public List<StudentDTO> getAll() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setCreatedDate(LocalDateTime.now());
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }

    public StudentDTO getById(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw  new ItemNotFoundException("Student not found");
        }
        StudentEntity entity = optional.get();
        return toDTO(entity);

//        Optional<StudentEntity> optional = studentRepository.findById(id);
//        return optional.map(entity -> toDTO(entity)).orElseThrow(() -> {
//            throw new ItemNotFoundException("Student not found");
//        });
    }

//    public StudentDTO getByPhone(String phone) {
//        Optional<StudentEntity> optional = studentRepository.getByPhone(phone);
//        if (optional.isEmpty()) {
//            throw new ItemNotFoundException("Student not found");
//        }
//        StudentEntity entity = optional.get();
//        return toDTO(entity);
//    }

    public Boolean delete(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw  new ItemNotFoundException("Student not found");
        }
        studentRepository.deleteById(id);
        return true;
    }

    public Boolean update(Integer id, StudentDTO student) {
        check(student);
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw  new ItemNotFoundException("Student not found");
        }
        studentRepository.deleteById(id);
        add(student);
        return true;
    }

    private void check(StudentDTO student) {
        if (student.getName() == null || student.getName().isBlank()) {
            throw new AppBadRequestException("Name qani?");
        }
        if (student.getSurname() == null || student.getSurname().isBlank()) {
            throw new AppBadRequestException("Surname qani?");
        }
    }

    public StudentDTO toDTO(StudentEntity entity){
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());
        dto.setLevel(entity.getLevel());
        dto.setGender(entity.getGender());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
