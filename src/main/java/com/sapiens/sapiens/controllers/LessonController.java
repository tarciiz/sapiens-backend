package com.sapiens.sapiens.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sapiens.sapiens.domain.lesson.Lesson;
import com.sapiens.sapiens.services.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequestMapping("/api/lesson")
public class LessonController {

    private final LessonService lessonService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Lesson lesson) {
        return lessonService.save(lesson);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Lesson lesson) {
        return lessonService.update(lesson);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return lessonService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return lessonService.findById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return lessonService.findAll();
    }

    @GetMapping("/discipline/{code}")
    public ResponseEntity<?> findByDisciplineCode(@PathVariable("code") String code) {
        return lessonService.findByDisciplineCode(code);
    }

    @GetMapping("/attendance/{id}")
    public ResponseEntity<?> findByAttendanceId(@PathVariable("id") Long id) {
        return lessonService.findByAttendanceId(id);
    }

}
