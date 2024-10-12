package com.sapiens.sapiens.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.domain.admin.Admin;
import com.sapiens.sapiens.domain.user.User;
import com.sapiens.sapiens.domain.user.UserRole;
import com.sapiens.sapiens.repositories.AdminRepository;
import com.sapiens.sapiens.repositories.AuthRepository;
import com.sapiens.sapiens.repositories.StudentRepository;
import com.sapiens.sapiens.repositories.TeacherRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminService {

    private final AuthRepository authRepository;
    private final AdminRepository adminRepository;

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public ResponseEntity<?> save(Admin admin) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(admin.getPassword());

        admin.setPassword(encryptedPassword);
        admin.setFirstLogin(true);

        return ResponseEntity.ok().body(authRepository.save(admin));
    }

    public ResponseEntity<?> findAllAdmins() {
        return ResponseEntity.ok().body(authRepository.findByRole(UserRole.ADMIN));
    }

    public ResponseEntity<?> findUsersBySchoolId(Long id) {
        var students = studentRepository.findBySchoolId(id);
        var teachers = teacherRepository.findBySchoolId(id);

        List<User> allUsers = new ArrayList<>();
        allUsers.addAll(students);
        allUsers.addAll(teachers);

        return ResponseEntity.ok(allUsers);
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(authRepository.findAll());
    }

    public ResponseEntity<?> update(Admin admin) {
        return ResponseEntity.ok().body(adminRepository.save(admin));
    }

}
