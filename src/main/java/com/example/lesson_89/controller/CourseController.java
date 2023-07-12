package com.example.lesson_89.controller;

import com.example.lesson_89.dto.CourseDTO;
import com.example.lesson_89.dto.CourseFilterDTO;
import com.example.lesson_89.dto.StudentDTO;
import com.example.lesson_89.dto.StudentFilterDTO;
import com.example.lesson_89.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody CourseDTO course) {
        CourseDTO response = courseService.add(course);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<CourseDTO> all() {
        return courseService.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(courseService.getById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@RequestBody CourseDTO course, @PathVariable("id") Integer id) {
        courseService.update(id, course);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        Boolean response = courseService.delete(id);
        if (response) {
            return ResponseEntity.ok("Student deleted");
        }
        return ResponseEntity.badRequest().body("Student Not Found");
    }

    @GetMapping("/byPrice")
    public ResponseEntity<?> GetCourseByPrice(@RequestParam("from") Integer from,
                                                  @RequestParam("to") Integer to) {
        List<?> courseDTOList = courseService.GetCourseByPrice(from, to);
        return ResponseEntity.ok(courseDTOList);
    }

    @GetMapping(value = "/byDate")
    public ResponseEntity<?> getByDate(@RequestParam("from") LocalDate from,
                                       @RequestParam("to") LocalDate to) {
        List<?> courseDTOList = courseService.getByDateBetween(from, to);
        return ResponseEntity.ok(courseDTOList);
    }

    @GetMapping(value = "/pagination")
    public ResponseEntity<PageImpl<CourseDTO>> pagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                           @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<CourseDTO> response = courseService.coursePagination(page - 1, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/pagination/price")
    public ResponseEntity<PageImpl<CourseDTO>> paginationByPrice(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                 @RequestParam(value = "size", defaultValue = "10") int size,
                                                                 @RequestParam(value = "price") Integer price) {
        PageImpl<CourseDTO> response = courseService.studentPaginationByPrice(price, page - 1, size);
        return ResponseEntity.ok(response);
    }
    @GetMapping(value = "/pagination/pricebetween")
    public ResponseEntity<PageImpl<CourseDTO>> paginationByPriceBetween(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                @RequestParam(value = "size", defaultValue = "10") int size,
                                                                @RequestParam(value = "from") Integer from,
                                                                @RequestParam(value = "to") Integer to) {
        PageImpl<CourseDTO> response = courseService.studentPaginationByPriceBetween(from, to, page - 1, size);
        return ResponseEntity.ok(response);
    }
    @PostMapping(value = "/filter")
    public ResponseEntity<PageImpl<CourseDTO>> filter(@RequestBody CourseFilterDTO filterDTO,
                                                       @RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<CourseDTO> response = courseService.filter(filterDTO, page - 1, size);
        return ResponseEntity.ok(response);
    }
}
