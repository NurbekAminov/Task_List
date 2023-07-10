package com.example.lesson_89.repository;

import com.example.lesson_89.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer>,
        PagingAndSortingRepository<StudentEntity, Integer> {

    @Query(" from StudentEntity as s where s.name =:name order by s.createdDate")
    List<StudentEntity> getByName(@Param("name") String name);
    @Query(" from StudentEntity as s where s.surname =:surname order by s.createdDate")
    List<StudentEntity> getBySurname(@Param("surname") String surname);
    @Query(" from StudentEntity as s where s.level =:level order by s.createdDate")
    List<StudentEntity> getByLevel(@Param("level") Integer level);
    @Query(" from StudentEntity as s where s.age =:age order by s.createdDate")
    List<StudentEntity> getByAge(@Param("age") Integer age);
    List<StudentEntity> findByAgeBetween(Integer from, Integer to);
    @Query(" from StudentEntity as s where s.gender =:gender order by s.createdDate")
    List<StudentEntity> getByGender(@Param("gender") String gender);
    @Query(" from StudentEntity as s where s.createdDate between :from and :to order by s.createdDate")
    List<StudentEntity> findByCreatedDateBetween(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    List<StudentEntity> findByCreatedDateAfter(LocalDateTime date);
    Page<StudentEntity> findByName(String name, Pageable pageable);
    @Transactional
    @Modifying
    @Query("update StudentEntity as s set s.name =:name, s.surname=:surname, s.age=:age, s.level=:level, s.gender=:gender where s.id  =:id ")
    int update(@Param("id") Integer id, @Param("name") String name, @Param("surname") String surname, @Param("age") Integer age, @Param("level") Integer level, @Param("gender") String gender);
    @Transactional
    @Modifying
    @Query("delete from StudentEntity as s where s.name =?1 and s.surname =?2 ")
    int delete(String name, String surname);
    @Query("select new StudentEntity (s.id, s.name, s.surname) from StudentEntity as s where s.name =:name ")
    List<StudentEntity> getStudentShortDetail(@Param("name") String name);
    @Query("select new StudentEntity (s.id, s.name, s.surname) from StudentEntity as s where s.name =:name  order by s.createdDate limit 1")
    List<StudentEntity> getStudentShortDetailLimit(@Param("name") String name);
    List<StudentEntity> findAllByNameLike(String name);
    StudentEntity findFirstByAgeOrderByCreatedDateDesc(Integer age);
    StudentEntity findTopByAge(Integer age); // where age = ?  limit 1
    List<StudentEntity> findTop3ByAge(Integer age); // where age = ?  limit 3
    Long countAllByAge(Integer age); // select count(*) from student where age = ?
    Long countAllBy(); // select count(*) from Student


 }
