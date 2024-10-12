package com.sapiens.sapiens.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sapiens.sapiens.domain.teacher.Teacher;
import com.sapiens.sapiens.services.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/teacher")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Teacher teacher) {
        return teacherService.save(teacher);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Teacher teacher) {
        return teacherService.update(teacher);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable("code") String code) {
        return teacherService.findByCode(code);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
        return teacherService.findByEmail(email);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return teacherService.findAll();
    }

    @GetMapping("/school/{id}")
    public ResponseEntity<?> findBySchoolId(@PathVariable("id") Long id) {
        return teacherService.findBySchoolId(id);
    }

}
