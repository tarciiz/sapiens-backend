package com.sapiens.sapiens.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sapiens.sapiens.domain.grade.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    boolean existsById(Long id);

    List<Grade> findByStudentId(Long id);

    List<Grade> findByEvaluationId(Long id);

    List<Grade> findByStudentIdAndEvaluationId(Long studentId, Long evaluationId);

}
