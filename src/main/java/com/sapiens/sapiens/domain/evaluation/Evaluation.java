package com.sapiens.sapiens.domain.evaluation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sapiens.sapiens.domain.discipline.Discipline;
import com.sapiens.sapiens.domain.grade.Grade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_evaluations")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String createdAt;
    private String deliveryAt;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.EAGER)
    private Discipline discipline;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "evaluation", cascade = CascadeType.REMOVE)
    private List<Grade> grades;

}