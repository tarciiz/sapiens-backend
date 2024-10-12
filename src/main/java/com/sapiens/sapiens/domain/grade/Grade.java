package com.sapiens.sapiens.domain.grade;

import com.sapiens.sapiens.domain.evaluation.Evaluation;
import com.sapiens.sapiens.domain.student.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;

    @ManyToOne(fetch = FetchType.EAGER)
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    private Evaluation evaluation;

}