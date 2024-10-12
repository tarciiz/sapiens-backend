package com.sapiens.sapiens.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sapiens.sapiens.domain.admin.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
