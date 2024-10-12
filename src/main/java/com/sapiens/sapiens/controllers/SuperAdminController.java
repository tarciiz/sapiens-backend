package com.sapiens.sapiens.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sapiens.sapiens.domain.superadmin.SuperAdmin;
import com.sapiens.sapiens.services.SuperAdminService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/super-admin")
public class SuperAdminController {

    private final SuperAdminService superAdminService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return superAdminService.findAll();
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody SuperAdmin superAdmin) {
        return superAdminService.update(superAdmin);
    }

}
