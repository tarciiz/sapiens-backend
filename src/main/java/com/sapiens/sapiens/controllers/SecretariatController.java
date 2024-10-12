package com.sapiens.sapiens.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sapiens.sapiens.domain.secretariat.Secretariat;
import com.sapiens.sapiens.services.SecretariatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/secretariat")
@AllArgsConstructor
public class SecretariatController {

    private final SecretariatService secretariatService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Secretariat secretariat) {
        return secretariatService.save(secretariat);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Secretariat secretariat) {
        return secretariatService.update(secretariat);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return secretariatService.findAll();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable("name") String name) {
        return secretariatService.findByName(name);
    }

    @GetMapping("/school/{id}")
    public ResponseEntity<?> findBySchoolsId(@PathVariable("id") Long id) {
        return secretariatService.findBySchoolsId(id);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<?> findBySuperAdminId(@PathVariable("id") Long id) {
        return secretariatService.findBySuperAdminId(id);
    }

}
