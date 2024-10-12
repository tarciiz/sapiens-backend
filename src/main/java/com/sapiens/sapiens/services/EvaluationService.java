package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.infra.exceptions.BusinessException;
import com.sapiens.sapiens.repositories.EvaluationRepository;
import com.sapiens.sapiens.domain.evaluation.Evaluation;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;

    public ResponseEntity<?> save(Evaluation evaluation) {
        return ResponseEntity.ok().body(evaluationRepository.save(evaluation));
    }

    public ResponseEntity<?> update(Evaluation evaluation) {
        return ResponseEntity.ok().body(evaluationRepository.save(evaluation));
    }

    public ResponseEntity<?> findById(Long id) {
        Evaluation evaluation = evaluationRepository.getReferenceById(id);

        if (evaluation == null) {
            throw new BusinessException("Avaliação não encontrada.");
        }

        return ResponseEntity.ok().body(evaluation);
    }

    public ResponseEntity<?> delete(Long id) {
        evaluationRepository.deleteById(id);
        return ResponseEntity.ok().body("Avaliação excluída com sucesso.");
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(evaluationRepository.findAll());
    }

    public ResponseEntity<?> findByDisciplineCode(String code) {
        return ResponseEntity.ok().body(evaluationRepository.findByDisciplineCode(code));
    }

    public ResponseEntity<?> findByGradeId(Long id) {
        return ResponseEntity.ok().body(evaluationRepository.findByGradesId(id));
    }

}
