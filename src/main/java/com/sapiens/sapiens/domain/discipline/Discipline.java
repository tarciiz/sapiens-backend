package com.sapiens.sapiens.domain.discipline;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sapiens.sapiens.domain.evaluation.Evaluation;
import com.sapiens.sapiens.domain.lesson.Lesson;
import com.sapiens.sapiens.domain.schedule.Schedule;
import com.sapiens.sapiens.domain.school.School;
import com.sapiens.sapiens.domain.schoolClass.SchoolClass;
import com.sapiens.sapiens.domain.teacher.Teacher;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_disciplines")
public class Discipline {

    @Id
    private String code;
    private String name;

    @Column(name = "many_lessons", nullable = true)
    private Integer manyLessons;

    @Column(name = "many_hours", nullable = true)
    private Double manyHours;

    @ManyToOne(fetch = FetchType.EAGER)
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.EAGER)
    private SchoolClass schoolClass;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "discipline", cascade = CascadeType.REMOVE)
    private List<Evaluation> evaluations;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "discipline", cascade = CascadeType.REMOVE)
    private List<Schedule> schedules;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "discipline", cascade = CascadeType.REMOVE)
    private List<Lesson> lessons;

    @ManyToOne(fetch = FetchType.EAGER)
    private School school;

}