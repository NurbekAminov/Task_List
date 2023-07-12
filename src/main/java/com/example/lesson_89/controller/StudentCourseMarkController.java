package com.example.lesson_89.controller;

import com.example.lesson_89.dto.StudentCourseMarkDTO;
import com.example.lesson_89.dto.StudentDTO;
import com.example.lesson_89.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/StudentMark")
public class StudentCourseMarkController {
    @Autowired
    StudentCourseMarkService service;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentCourseMarkDTO student) {
        StudentCourseMarkDTO response = service.add(student);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@RequestBody StudentCourseMarkDTO dto, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.updateMark(id, dto));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        Boolean response = service.delete(id);
        if (response) {
            return ResponseEntity.ok("Student deleted");
        }
        return ResponseEntity.badRequest().body("Student Not Found");
    }
    @GetMapping("/all")
    public List<StudentCourseMarkDTO> all() {
        return service.getAll();
    }
    @GetMapping(value = "/{id}/date")
    public ResponseEntity<String> getMarkByDate(@PathVariable("id") Integer id,
                                     @PathVariable("date") LocalDate date) {
        return ResponseEntity.ok(service.getMarkByDate(id, date));
    }
}
