package com.example.lesson_89.service;

import com.example.lesson_89.dto.StudentDTO;
import com.example.lesson_89.entity.StudentEntity;
import com.example.lesson_89.exp.AppBadRequestException;
import com.example.lesson_89.exp.ItemNotFoundException;
import com.example.lesson_89.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO add(StudentDTO dto) {
        check(dto); // validate inputs

        StudentEntity entity = toEntity(dto);

        studentRepository.save(entity);
        dto.setId(entity.getId());

        return dto;
    }
    public List<StudentDTO> addAll(List<StudentDTO> list) {
        for (StudentDTO dto : list) {
            StudentEntity entity = toEntity(dto);
            studentRepository.save(entity);
            dto.setId(entity.getId());
        }
        return list;
    }
    public List<StudentDTO> getAll() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }
    public StudentDTO getById(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Student not found");
        }
        StudentEntity entity = optional.get();
        return toDTO(entity);

//        Optional<StudentEntity> optional = studentRepository.findById(id);
//        return optional.map(entity -> toDTO(entity)).orElseThrow(() -> {
//            throw new ItemNotFoundException("Student not found");
//        });
    }
    public Boolean update(Integer id, StudentDTO student) {
        check(student);
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            StudentEntity entity = optional.get();
            entity.setName(student.getName());
            entity.setSurname(student.getSurname());
            entity.setAge(student.getAge());
            entity.setLevel(student.getLevel());
            entity.setGender(student.getGender());

            studentRepository.save(entity);
            return true;
        }
        return false;
    }
    public Boolean update2(Integer id, StudentDTO student) {
        int effectedRows = studentRepository.updateNameAndSurname(id, student.getName(), student.getSurname());
        return effectedRows != 0;
    }

    public Boolean delete(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }
    public List<StudentDTO> getByName(String name) {
        Iterable<StudentEntity> iterable = studentRepository.getByName(name);
        List<StudentDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }
    public List<StudentDTO> getBySurname(String surname) {
        Iterable<StudentEntity> iterable = studentRepository.getBySurname(surname);
        List<StudentDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }
    public List<StudentDTO> getByLevel(Integer level) {
        Iterable<StudentEntity> iterable = studentRepository.getByLevel(level);
        List<StudentDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }
    public List<StudentDTO> getByAge(Integer age) {
        Iterable<StudentEntity> iterable = studentRepository.getByAge(age);
        List<StudentDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }
    public List<StudentDTO> findByAge(Integer from, Integer to) {
        List<StudentEntity> studentEntityList = studentRepository.findByAgeBetween(from, to);
        List<StudentDTO> dtoList = new LinkedList<>();
        studentEntityList.forEach(entity -> dtoList.add(toDTO(entity)));
        return dtoList;
    }
    public List<StudentDTO> getByGender(String gender) {
        Iterable<StudentEntity> iterable = studentRepository.getByGender(gender);
        List<StudentDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }
    public List<StudentDTO> getByDate(LocalDate date) {
        LocalDateTime from = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime to = LocalDateTime.of(date, LocalTime.MAX);

        Iterable<StudentEntity> iterable = studentRepository.findByCreatedDateBetween(from, to);
        List<StudentDTO> dtoList = new LinkedList<>();

        iterable.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });

        return dtoList;
    }
    public List<StudentDTO> getByDateBetween(LocalDate from, LocalDate to) {
        LocalDateTime from1 = LocalDateTime.of(from, LocalTime.MIN);
        LocalDateTime to1 = LocalDateTime.of(to, LocalTime.MAX);

        Iterable<StudentEntity> iterable = studentRepository.findByCreatedDateBetween(from1, to1);
        List<StudentDTO> dtoList = new LinkedList<>();

        iterable.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });

        return dtoList;
    }
    public PageImpl<StudentDTO> studentPagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createdDate");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<StudentEntity> pageObj = studentRepository.findAll(pageable);

        List<StudentEntity> entityList = pageObj.getContent();
        Long totalCount = pageObj.getTotalElements();

        List<StudentDTO> studentDTOList = new LinkedList<>();
        entityList.forEach(entity -> {
            studentDTOList.add(toDTO(entity));
        });

        PageImpl<StudentDTO> pageImpl = new PageImpl<StudentDTO>(studentDTOList, pageable, totalCount);
        return pageImpl;
    }
    public PageImpl<StudentDTO> studentPaginationByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<StudentEntity> pageObj = studentRepository.findByName(name, pageable);
        List<StudentDTO> studentDTOList = pageObj.stream().map(this::toDTO).collect(Collectors.toList());
        return new PageImpl<>(studentDTOList, pageable, pageObj.getTotalElements());
    }
    public List<StudentDTO> getAllDateFrom(LocalDate localDate) {
        Iterable<StudentEntity> iterable = studentRepository.findByCreatedDateAfter(LocalDateTime.of(localDate, LocalTime.MIN));
        List<StudentDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }

    /*public void test() {

        String name = "Ali";
        List<StudentEntity> studentEntityList = studentRepository.findAllByNameLike("%" + name + "%");
    }*/

    private void check(StudentDTO student) {
        if (student.getName() == null || student.getName().isBlank()) {
            throw new AppBadRequestException("Name qani?");
        }
        if (student.getSurname() == null || student.getSurname().isBlank()) {
            throw new AppBadRequestException("Surname qani?");
        }
    }
    public StudentDTO toDTO(StudentEntity entity) {
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
    public StudentEntity toEntity(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setLevel(dto.getLevel());
        entity.setGender(dto.getGender());
        entity.setCreatedDate(LocalDateTime.now());
        return entity;
    }
}
