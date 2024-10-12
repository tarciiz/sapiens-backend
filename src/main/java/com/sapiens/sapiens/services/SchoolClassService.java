package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.domain.schoolClass.SchoolClass;
import com.sapiens.sapiens.infra.exceptions.BusinessException;
import com.sapiens.sapiens.repositories.SchoolClassRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SchoolClassService {

    private final SchoolClassRepository schoolClassRepository;
    private final StudentService studentService;
    private final DisciplineService disciplineService;

    public ResponseEntity<?> save(SchoolClass schoolClass) {
        if (schoolClassRepository.existsByCode(schoolClass.getCode())) {
            throw new BusinessException("Turma já registrada.");
        }

        return ResponseEntity.ok().body(schoolClassRepository.save(schoolClass));
    }

    @Transactional
    public ResponseEntity<?> assignStudents(SchoolClass schoolClass) {
        var schoolClassDb = schoolClassRepository.getReferenceById(schoolClass.getCode());

        schoolClass.getStudents().forEach(student -> {
            var studentAlreadyAssigned = student.getSchoolClass() != null &&
                    !student.getSchoolClass().getCode().equals(schoolClassDb.getCode());

            if (studentAlreadyAssigned) {
                throw new BusinessException("Aluno: " + student.getName() + " já se encontra em outra turma.");
            }
        });

        schoolClassDb.getStudents().removeIf(student -> {
            var shouldRemove = !schoolClass.getStudents().contains(student);

            if (shouldRemove) {
                student.setSchoolClass(null);
                studentService.update(student);
            }

            return shouldRemove;
        });

        schoolClass.getStudents().forEach(student -> {
            student.setSchoolClass(schoolClassDb);
            studentService.update(student);
        });

        return ResponseEntity.ok().body(schoolClassRepository.save(schoolClassDb));
    }

    @Transactional
    public ResponseEntity<?> assignDisciplines(SchoolClass schoolClass) {
        var schoolClassDb = schoolClassRepository.getReferenceById(schoolClass.getCode());

        schoolClass.getDisciplines().forEach(discipline -> {
            var disciplineAlreadyAssigned = discipline.getSchoolClass() != null &&
                    !discipline.getSchoolClass().getCode().equals(schoolClassDb.getCode());

            if (disciplineAlreadyAssigned) {
                throw new BusinessException("Disciplina: " + discipline.getCode() + " já se encontra em outra turma.");
            }
        });

        schoolClassDb.getDisciplines().removeIf(discipline -> {
            var shouldRemove = !schoolClass.getDisciplines().contains(discipline);

            if (shouldRemove) {
                discipline.setSchoolClass(null);
                disciplineService.update(discipline.getCode(), discipline);
            }

            return shouldRemove;
        });

        schoolClass.getDisciplines().forEach(discipline -> {
            discipline.setSchoolClass(schoolClassDb);
            disciplineService.update(discipline.getCode(), discipline);
        });

        return ResponseEntity.ok().body(schoolClassRepository.save(schoolClassDb));
    }

    public ResponseEntity<?> findByCode(String code) {
        SchoolClass schoolClass = schoolClassRepository.findByCode(code)
                .orElseThrow(() -> new BusinessException("Turma não encontrada."));

        return ResponseEntity.ok().body(schoolClass);
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(schoolClassRepository.findAll());
    }

    public ResponseEntity<?> findByStudentId(Long id) {
        var schoolClass = schoolClassRepository.findByStudentsId(id)
                .orElseThrow(() -> new BusinessException("Turma não encontrada."));

        return ResponseEntity.ok().body(schoolClass);
    }

    public ResponseEntity<?> findStudentsByDisciplineCode(String code) {
        var schoolClass = schoolClassRepository.findByDisciplinesCode(code)
                .orElseThrow(() -> new BusinessException("Turma não encontrada."));

        return ResponseEntity.ok().body(schoolClass.getStudents());
    }

    public ResponseEntity<?> findBySchoolId(Long id) {
        return ResponseEntity.ok().body(schoolClassRepository.findBySchoolId(id));
    }

}
