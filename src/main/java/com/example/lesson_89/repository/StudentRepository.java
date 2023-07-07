package com.example.lesson_89.repository;

import com.example.lesson_89.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer>,
        PagingAndSortingRepository<StudentEntity, Integer> {

    List<StudentEntity> getByName(String name);
    List<StudentEntity> getBySurname(String surname);
    List<StudentEntity> getByLevel(Integer level);
    List<StudentEntity> getByAge(Integer age);
    List<StudentEntity> findByAgeBetween(Integer from, Integer to);
    List<StudentEntity> getByGender(String gender);
    List<StudentEntity> findByCreatedDateBetween(LocalDateTime from, LocalDateTime to);
    List<StudentEntity> findByCreatedDateAfter(LocalDateTime date);


    List<StudentEntity> findAllByNameLike(String name);
    StudentEntity findFirstByAgeOrderByCreatedDateDesc(Integer age);
    StudentEntity findTopByAge(Integer age); // where age = ?  limit 1
    List<StudentEntity> findTop3ByAge(Integer age); // where age = ?  limit 3
    Long countAllByAge(Integer age); // select count(*) from student where age = ?
    Long countAllBy(); // select count(*) from Student


 }
