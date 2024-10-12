package com.sapiens.sapiens.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sapiens.sapiens.domain.discipline.Discipline;
import com.sapiens.sapiens.services.DisciplineService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/discipline")
@AllArgsConstructor
public class DisciplineController {

    private final DisciplineService disciplineService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Discipline discipline) {
        return disciplineService.save(discipline);
    }

    @PutMapping("/update/{code}")
    public ResponseEntity<?> update(@PathVariable("code") String code, @RequestBody Discipline discipline) {
        return disciplineService.update(code, discipline);
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<?> delete(@PathVariable("code") String code) {
        return disciplineService.delete(code);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable("code") String code) {
        return disciplineService.findByCode(code);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable("name") String name) {
        return disciplineService.findByName(name);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return disciplineService.findAll();
    }

    @GetMapping("/teacher/{id}")
    public ResponseEntity<?> findByTeacherId(@PathVariable("id") Long id) {
        return disciplineService.findByTeacherId(id);
    }

    @GetMapping("/class/{code}")
    public ResponseEntity<?> findBySchoolClassCode(@PathVariable("code") String code) {
        return disciplineService.findBySchoolClassCode(code);
    }

    @GetMapping("/progress/{code}")
    public ResponseEntity<?> disciplineProgress(@PathVariable("code") String code) {
        return disciplineService.disciplineProgress(code);
    }

    @GetMapping("/school/{id}")
    public ResponseEntity<?> findBySchoolId(@PathVariable("id") Long id) {
        return disciplineService.findBySchoolId(id);
    }

}
