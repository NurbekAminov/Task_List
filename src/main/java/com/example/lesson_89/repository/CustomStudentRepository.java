package com.example.lesson_89.repository;

import com.example.lesson_89.dto.StudentFilterDTO;
import com.example.lesson_89.entity.StudentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomStudentRepository {
    @Autowired
    private EntityManager entityManager;
    public List<StudentEntity> filter(StudentFilterDTO filterDTO, int page, int size) {

        StringBuilder stringBuilder = new StringBuilder("select s from StudentEntity where s.visible = true");
        Map<String, Object> params = new HashMap<>();
        if (filterDTO.getName() != null) {
            stringBuilder.append(" and s.name =:name");
            params.put("name", filterDTO.getName());
        }
        if (filterDTO.getSurname() != null) {
            stringBuilder.append(" and s.surname =:surname");
            params.put("surname", filterDTO.getSurname());
        }
        if (filterDTO.getAge() != null) {
            stringBuilder.append(" and s.age =:age");
            params.put("age", filterDTO.getAge());
        }
        if (filterDTO.getLevel() != null) {
            stringBuilder.append(" and s.level =:level");
            params.put("level", filterDTO.getLevel());
        }
        if (filterDTO.getGender() != null) {
            stringBuilder.append(" and s.gender =:gender");
            params.put("gender", filterDTO.getGender());
        }

        stringBuilder.append(" order by cratedDate");

        Query query = entityManager.createQuery(stringBuilder.toString());
        // params
        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        List<StudentEntity> entityList = query.getResultList();

        return null;
    }
}
