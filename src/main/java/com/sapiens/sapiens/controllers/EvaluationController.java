package com.sapiens.sapiens.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sapiens.sapiens.domain.evaluation.Evaluation;
import com.sapiens.sapiens.services.EvaluationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/evaluation")
@AllArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Evaluation evaluation) {
        return evaluationService.save(evaluation);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Evaluation evaluation) {
        return evaluationService.update(evaluation);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return evaluationService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return evaluationService.findById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return evaluationService.findAll();
    }

    @GetMapping("/discipline/{code}")
    public ResponseEntity<?> findByDisciplineCode(@PathVariable("code") String code) {
        return evaluationService.findByDisciplineCode(code);
    }

    @GetMapping("/grade/{id}")
    public ResponseEntity<?> findByGradeId(@PathVariable("id") Long id) {
        return evaluationService.findByGradeId(id);
    }

}
