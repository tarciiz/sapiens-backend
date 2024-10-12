package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.infra.exceptions.BusinessException;
import com.sapiens.sapiens.repositories.SchoolRepository;
import com.sapiens.sapiens.domain.school.School;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public ResponseEntity<?> save(School school) {
        return ResponseEntity.ok().body(schoolRepository.save(school));
    }

    public ResponseEntity<?> update(School school) {
        return ResponseEntity.ok().body(schoolRepository.save(school));
    }

    public ResponseEntity<?> findById(Long id) {
        School school = schoolRepository.getReferenceById(id);

        if (school == null) {
            throw new BusinessException("Escola não encontrada.");
        }

        return ResponseEntity.ok().body(school);
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(schoolRepository.findAll());
    }

    public ResponseEntity<?> findByAdminId(Long id) {
        var school = schoolRepository.findByAdminId(id)
                .orElseThrow(() -> new BusinessException("Escola não encontrada."));

        return ResponseEntity.ok().body(school);
    }

    public ResponseEntity<?> findBySecretariatId(Long id) {
        var school = schoolRepository.findBySecretariatId(id)
                .orElseThrow(() -> new BusinessException("Escola não encontrada."));

        return ResponseEntity.ok().body(school);
    }

    public ResponseEntity<?> findByStudentsId(Long id) {
        var school = schoolRepository.findByStudentsId(id)
                .orElseThrow(() -> new BusinessException("Escola não encontrada."));

        return ResponseEntity.ok().body(school);
    }

    public ResponseEntity<?> findByTeachersId(Long id) {
        var school = schoolRepository.findByTeachersId(id)
                .orElseThrow(() -> new BusinessException("Escola não encontrada."));

        return ResponseEntity.ok().body(school);
    }

    public ResponseEntity<?> findByDisciplinesCode(String code) {
        var school = schoolRepository.findByDisciplinesCode(code)
                .orElseThrow(() -> new BusinessException("Escola não encontrada."));

        return ResponseEntity.ok().body(school);
    }

    public ResponseEntity<?> findBySchoolClassesCode(String code) {
        var school = schoolRepository.findBySchoolClassesCode(code)
                .orElseThrow(() -> new BusinessException("Escola não encontrada."));

        return ResponseEntity.ok().body(school);
    }

}
