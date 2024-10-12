package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.domain.superadmin.SuperAdmin;
import com.sapiens.sapiens.repositories.AuthRepository;
import com.sapiens.sapiens.repositories.SuperAdminRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SuperAdminService {

    private final AuthRepository authRepository;
    private final SuperAdminRepository superAdminRepository;

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(authRepository.findAll());
    }

    public ResponseEntity<?> update(SuperAdmin superAdmin) {
        return ResponseEntity.ok().body(superAdminRepository.save(superAdmin));
    }

}
