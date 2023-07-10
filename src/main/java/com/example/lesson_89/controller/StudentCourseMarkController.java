package com.example.lesson_89.controller;

import com.example.lesson_89.dto.StudentCourseMarkDTO;
import com.example.lesson_89.dto.StudentDTO;
import com.example.lesson_89.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/StudentMark")
public class StudentCourseMarkController {
    @Autowired
    StudentCourseMarkService service;

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@RequestBody StudentCourseMarkDTO dto, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.updateMark(id, dto));
    }
}
