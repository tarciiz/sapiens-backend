package com.sapiens.sapiens.domain.admin;

import com.sapiens.sapiens.domain.school.School;
import com.sapiens.sapiens.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_admins")
public class Admin extends User {

    @OneToOne(fetch = FetchType.EAGER)
    private School school;

}
