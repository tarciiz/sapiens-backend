package com.sapiens.sapiens.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sapiens.sapiens.domain.schoolClass.SchoolClass;
import com.sapiens.sapiens.services.SchoolClassService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/school-class")
@AllArgsConstructor
public class SchoolClassController {

    private final SchoolClassService schoolClassService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SchoolClass schoolClass) {
        return schoolClassService.save(schoolClass);
    }

    @PutMapping("/assign-students")
    public ResponseEntity<?> assignStudents(@RequestBody SchoolClass schoolClass) {
        return schoolClassService.assignStudents(schoolClass);
    }

    @PutMapping("/assign-disciplines")
    public ResponseEntity<?> updateDisciplines(@RequestBody SchoolClass schoolClass) {
        return schoolClassService.assignDisciplines(schoolClass);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable("code") String code) {
        return schoolClassService.findByCode(code);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return schoolClassService.findAll();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> findByStudentId(@PathVariable("id") Long id) {
        return schoolClassService.findByStudentId(id);
    }

    @GetMapping("/students-discipline/{code}")
    public ResponseEntity<?> findByDisciplineCode(@PathVariable("code") String code) {
        return schoolClassService.findStudentsByDisciplineCode(code);
    }

    @GetMapping("/school/{id}")
    public ResponseEntity<?> findBySchoolId(@PathVariable("id") Long id) {
        return schoolClassService.findBySchoolId(id);
    }

}
