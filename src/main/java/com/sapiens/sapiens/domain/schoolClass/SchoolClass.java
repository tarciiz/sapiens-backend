package com.sapiens.sapiens.domain.schoolClass;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sapiens.sapiens.domain.discipline.Discipline;
import com.sapiens.sapiens.domain.school.School;
import com.sapiens.sapiens.domain.student.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_school_classes")
public class SchoolClass {

    @Id
    private String code;

    @JsonProperty(access = Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "schoolClass")
    private List<Student> students;

    @JsonProperty(access = Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "schoolClass")
    private List<Discipline> disciplines;

    @ManyToOne(fetch = FetchType.EAGER)
    private School school;

}