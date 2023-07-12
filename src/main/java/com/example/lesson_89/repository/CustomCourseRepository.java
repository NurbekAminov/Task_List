package com.example.lesson_89.repository;

import com.example.lesson_89.dto.CourseFilterDTO;
import com.example.lesson_89.dto.StudentFilterDTO;
import com.example.lesson_89.entity.CourseEntity;
import com.example.lesson_89.entity.StudentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomCourseRepository {
    @Autowired
    private EntityManager entityManager;
    public List<StudentEntity> filter(CourseFilterDTO filterDTO, int page, int size) {

        StringBuilder stringBuilder = new StringBuilder("select s from CourseEntity where s.visible = true");
        Map<String, Object> params = new HashMap<>();
        if (filterDTO.getName() != null) {
            stringBuilder.append(" and s.name =:name");
            params.put("name", filterDTO.getName());
        }
        if (filterDTO.getPrice() != null) {
            stringBuilder.append(" and s.price =:price");
            params.put("price", filterDTO.getPrice());
        }
        if (filterDTO.getDuration() != null) {
            stringBuilder.append(" and s.duration =:duration");
            params.put("duration", filterDTO.getDuration());
        }

        stringBuilder.append(" order by cratedDate");

        Query query = entityManager.createQuery(stringBuilder.toString());
        // params
        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        List<CourseEntity> entityList = query.getResultList();

        return null;
    }
}
