package com.sapiens.sapiens.domain.secretariat;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sapiens.sapiens.domain.school.School;
import com.sapiens.sapiens.domain.superadmin.SuperAdmin;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "tb_secretariat")
public class Secretariat {

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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "secretariat")
    private List<School> schools;

    @OneToOne(fetch = FetchType.EAGER)
    private SuperAdmin superAdmin;
}
