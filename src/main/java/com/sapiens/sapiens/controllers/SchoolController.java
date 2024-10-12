package com.sapiens.sapiens.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sapiens.sapiens.domain.school.School;
import com.sapiens.sapiens.services.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/school")
@AllArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody School school) {
        return schoolService.save(school);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody School school) {
        return schoolService.update(school);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return schoolService.findAll();
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<?> findByAdminId(@PathVariable("id") Long id) {
        return schoolService.findByAdminId(id);
    }

    @GetMapping("/secretariat/{id}")
    public ResponseEntity<?> findBySecretariatId(@PathVariable("id") Long id) {
        return schoolService.findBySecretariatId(id);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> findByStudentsId(@PathVariable("id") Long id) {
        return schoolService.findByStudentsId(id);
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<?> findByTeachersId(@PathVariable("id") Long id) {
        return schoolService.findByTeachersId(id);
    }

    @GetMapping("/disciplines/{code}")
    public ResponseEntity<?> findByDisciplinesCode(@PathVariable("code") String code) {
        return schoolService.findByDisciplinesCode(code);
    }

    @GetMapping("/classes/{code}")
    public ResponseEntity<?> findBySchoolClassesCode(@PathVariable("code") String code) {
        return schoolService.findBySchoolClassesCode(code);
    }

}
