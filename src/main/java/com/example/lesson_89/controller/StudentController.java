package com.example.lesson_89.controller;



import com.example.lesson_89.dto.StudentDTO;
import com.example.lesson_89.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentDTO student) {
        StudentDTO response = studentService.add(student);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create/all")
    public ResponseEntity<?> create(@RequestBody List<StudentDTO> list) {
        return ResponseEntity.ok(studentService.addAll(list));
    }


    @GetMapping("/all")
    public List<StudentDTO> all() {
        return studentService.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.getById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@RequestBody StudentDTO student, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.update(id, student));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        Boolean response = studentService.delete(id);
        if (response) {
            return ResponseEntity.ok("Student deleted");
        }
        return ResponseEntity.badRequest().body("Student Not Found");
    }

    @GetMapping(value = "/name")
    public ResponseEntity<?> getByName(@RequestParam String name) {
        List<?> studentDTOList = studentService.getByName(name);
        return ResponseEntity.ok(studentDTOList);
    }

    @GetMapping(value = "/surname")
    public ResponseEntity<?> getBySurname(@RequestParam String surname) {
        List<?> studentDTOList = studentService.getBySurname(surname);
        return ResponseEntity.ok(studentDTOList);
    }

    @GetMapping(value = "/level")
    public ResponseEntity<?> getByLevel(@RequestParam Integer level) {
        List<?> studentDTOList = studentService.getByLevel(level);
        return ResponseEntity.ok(studentDTOList);
    }

    @GetMapping(value = "/age")
    public ResponseEntity<?> getByAge(@RequestParam Integer age) {
        List<?> studentDTOList = studentService.getByAge(age);
        return ResponseEntity.ok(studentDTOList);
    }

    @GetMapping("/age")
    public ResponseEntity<?> getStudentBetweenAge(@RequestParam Integer from, @RequestParam Integer to) {
        List<?> studentDTOList = studentService.findByAge(from, to);
        return ResponseEntity.ok(studentDTOList);
    }

    @GetMapping("/gender")
    public ResponseEntity<?> getByGender(@RequestParam String gender) {
        List<?> studentDTOList = studentService.getByGender(gender);
        return ResponseEntity.ok(studentDTOList);
    }

    @GetMapping(value = "/byDate")
    public ResponseEntity<?> getByDate(@RequestParam("Date") LocalDate date) {
        studentService.getByDate(date);
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/pagination")
    public ResponseEntity<?> pagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size) {
        studentService.studentPagination(page - 1, size);
        return ResponseEntity.ok().build();
    }

}
