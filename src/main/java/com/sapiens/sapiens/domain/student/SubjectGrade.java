package com.sapiens.sapiens.domain.student;

import com.sapiens.sapiens.domain.evaluation.Evaluation;

public record SubjectGrade(Long id, Double value, Evaluation evaluation) {

}
