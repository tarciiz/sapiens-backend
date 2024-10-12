package com.sapiens.sapiens.domain.attendance;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sapiens.sapiens.domain.lesson.Lesson;
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
@Table(name = "tb_attendances")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    private Lesson lesson;

    @JsonProperty("isPresent")
    private boolean isPresent;
    private int attendedCount;

}
