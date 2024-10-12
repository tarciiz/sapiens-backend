package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.infra.exceptions.BusinessException;
import com.sapiens.sapiens.repositories.GradeRepository;
import com.sapiens.sapiens.domain.grade.Grade;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;

    public ResponseEntity<?> save(Grade grade) {
        return ResponseEntity.ok().body(gradeRepository.save(grade));
    }

    public ResponseEntity<?> saveMany(Iterable<Grade> grades) {
        return ResponseEntity.ok().body(gradeRepository.saveAll(grades));
    }

    public ResponseEntity<?> findById(Long id) {
        Grade grade = gradeRepository.getReferenceById(id);

        if (grade == null) {
            throw new BusinessException("Nota não encontrada.");
        }

        return ResponseEntity.ok().body(grade);
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(gradeRepository.findAll());
    }

    public ResponseEntity<?> findByStudentId(Long id) {
        return ResponseEntity.ok().body(gradeRepository.findByStudentId(id));
    }

    public ResponseEntity<?> findByEvaluationId(Long id) {
        return ResponseEntity.ok().body(gradeRepository.findByEvaluationId(id));
    }

}
