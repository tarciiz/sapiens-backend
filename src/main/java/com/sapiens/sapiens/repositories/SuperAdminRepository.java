package com.sapiens.sapiens.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sapiens.sapiens.domain.superadmin.SuperAdmin;

@Repository
public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long> {

}
