package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.domain.discipline.Discipline;
import com.sapiens.sapiens.domain.discipline.DisciplineProgress;
import com.sapiens.sapiens.infra.exceptions.BusinessException;
import com.sapiens.sapiens.repositories.DisciplineRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;

    public ResponseEntity<?> save(Discipline discipline) {
        return ResponseEntity.ok().body(disciplineRepository.save(discipline));
    }

    public ResponseEntity<?> update(String code, Discipline discipline) {
        Discipline disciplineDb = disciplineRepository.getReferenceById(code);

        disciplineRepository.deleteById(code);

        disciplineDb.setCode(discipline.getCode());
        disciplineDb.setName(discipline.getName());
        disciplineDb.setManyLessons(discipline.getManyLessons());
        disciplineDb.setManyHours(discipline.getManyHours());
        disciplineDb.setTeacher(discipline.getTeacher());
        disciplineDb.setSchoolClass(discipline.getSchoolClass());

        return ResponseEntity.ok().body(disciplineRepository.save(disciplineDb));
    }

    public ResponseEntity<?> delete(String code) {
        disciplineRepository.deleteById(code);
        return ResponseEntity.ok().body("Disciplina excluída com sucesso.");
    }

    public ResponseEntity<?> findByName(String name) {
        Discipline discipline = disciplineRepository.findByName(name)
                .orElseThrow(() -> new BusinessException("Disciplina não encontrada."));

        return ResponseEntity.ok().body(discipline);
    }

    public ResponseEntity<?> findByCode(String code) {
        Discipline discipline = disciplineRepository.findByCode(code)
                .orElseThrow(() -> new BusinessException("Disciplina não encontrada."));

        return ResponseEntity.ok().body(discipline);
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(disciplineRepository.findAll());
    }

    public ResponseEntity<?> findByTeacherId(Long id) {
        return ResponseEntity.ok().body(disciplineRepository.findByTeacherId(id));
    }

    public ResponseEntity<?> findBySchoolClassCode(String code) {
        return ResponseEntity.ok().body(disciplineRepository.findBySchoolClassCode(code));
    }

    private Integer totalLessons(Discipline discipline) {
        int totalLessons = 0;
        for (var lesson : discipline.getLessons()) {
            totalLessons += lesson.getManyLessons();
        }
        return totalLessons;
    }

    public ResponseEntity<?> disciplineProgress(String code) {
        Discipline discipline = disciplineRepository.getReferenceById(code);
        var lessonsCompleted = totalLessons(discipline);

        var totalLessons = discipline.getManyLessons();
        if (totalLessons == 0)
            return ResponseEntity.ok().body(0);

        var progress = ((double) lessonsCompleted / totalLessons) * 100;
        var formattedProgress = String.format("%.2f", progress);

        var students = 0;
        if (discipline.getSchoolClass() != null) {
            students = discipline.getSchoolClass().getStudents().size();
        }

        return ResponseEntity.ok().body(
                new DisciplineProgress(totalLessons, lessonsCompleted, formattedProgress, students));
    }

    public ResponseEntity<?> findBySchoolId(Long id) {
        return ResponseEntity.ok().body(disciplineRepository.findBySchoolId(id));
    }

}
