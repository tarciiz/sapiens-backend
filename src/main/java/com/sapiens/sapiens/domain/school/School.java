package com.sapiens.sapiens.domain.school;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sapiens.sapiens.domain.admin.Admin;
import com.sapiens.sapiens.domain.discipline.Discipline;
import com.sapiens.sapiens.domain.schoolClass.SchoolClass;
import com.sapiens.sapiens.domain.secretariat.Secretariat;
import com.sapiens.sapiens.domain.student.Student;
import com.sapiens.sapiens.domain.teacher.Teacher;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_school")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phone;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.EAGER)
    private Secretariat secretariat;

    @OneToOne(fetch = FetchType.EAGER)
    private Admin admin;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    private List<Student> students;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    private List<Teacher> teachers;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    private List<SchoolClass> schoolClasses;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    private List<Discipline> disciplines;

}
