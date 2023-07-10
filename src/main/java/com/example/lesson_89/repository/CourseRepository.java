package com.example.lesson_89.repository;

import com.example.lesson_89.entity.CourseEntity;
import com.example.lesson_89.entity.StudentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update CourseEntity as s set s.name =:name, s.price=:price, s.duration=:duration where s.id  =:id ")
    int updateNameAndSurname(@Param("id") Integer id, @Param("name") String name, @Param("price") Integer price, @Param("duration") Integer duration);

    @Query(" from CourseEntity as s where s.price between :from and :to order by s.createdDate")
    List<CourseEntity> findByPriceBetween(@Param("from") Integer from, @Param("to") Integer to);

    @Query(" from CourseEntity as s where s.createdDate between :from and :to order by s.createdDate")
    List<CourseEntity> findByCreatedDateBetween(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
