package com.sapiens.sapiens.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sapiens.sapiens.domain.evaluation.Evaluation;
import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    boolean existsById(Long id);

    List<Evaluation> findByDisciplineCode(String code);

    List<Evaluation> findByGradesId(Long id);

}
