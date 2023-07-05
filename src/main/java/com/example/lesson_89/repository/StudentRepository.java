package com.example.lesson_89.repository;

import com.example.lesson_89.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

    Optional<StudentEntity> getByPhone(String phone);

    List<StudentEntity> getByName(String name);
    List<StudentEntity> getByNameAndSurname(String name, String surname);
}
