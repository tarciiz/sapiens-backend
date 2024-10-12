package com.sapiens.sapiens.domain.teacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sapiens.sapiens.domain.discipline.Discipline;
import com.sapiens.sapiens.domain.school.School;
import com.sapiens.sapiens.domain.user.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_teachers")
public class Teacher extends User {

    private String code;
    private int age;
    private String sex;

    @JsonProperty(access = Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher")
    private List<Discipline> disciplines;

    @ManyToOne(fetch = FetchType.EAGER)
    private School school;

}