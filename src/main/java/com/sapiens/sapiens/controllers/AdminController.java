package com.sapiens.sapiens.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sapiens.sapiens.domain.admin.Admin;
import com.sapiens.sapiens.services.AdminService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Admin admin) {
        return adminService.save(admin);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return adminService.findAll();
    }

    @GetMapping("/admins")
    public ResponseEntity<?> findAllAdmins() {
        return adminService.findAllAdmins();
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Admin admin) {
        return adminService.update(admin);
    }

    @GetMapping("/users/school/{id}")
    public ResponseEntity<?> findUsersBySchoolId(@PathVariable("id") Long id) {
        return adminService.findUsersBySchoolId(id);
    }
}
