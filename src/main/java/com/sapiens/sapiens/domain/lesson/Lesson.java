package com.sapiens.sapiens.domain.lesson;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sapiens.sapiens.domain.attendance.Attendance;
import com.sapiens.sapiens.domain.discipline.Discipline;
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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String date;
    private int manyLessons;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    private Discipline discipline;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lesson", cascade = CascadeType.REMOVE)
    private List<Attendance> attendances;

}
