package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.infra.exceptions.BusinessException;
import com.sapiens.sapiens.repositories.LessonRepository;
import com.sapiens.sapiens.domain.lesson.Lesson;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

    public ResponseEntity<?> save(Lesson lesson) {
        return ResponseEntity.ok().body(lessonRepository.save(lesson));
    }

    public ResponseEntity<?> update(Lesson lesson) {
        return ResponseEntity.ok().body(lessonRepository.save(lesson));
    }

    public ResponseEntity<?> delete(Long id) {
        lessonRepository.deleteById(id);
        return ResponseEntity.ok().body("Aula excluída com sucesso.");
    }

    public ResponseEntity<?> findById(Long id) {
        Lesson lesson = lessonRepository.getReferenceById(id);

        if (lesson == null) {
            throw new BusinessException("Aula não encontrada.");
        }

        return ResponseEntity.ok().body(lesson);
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(lessonRepository.findAll());
    }

    public ResponseEntity<?> findByDisciplineCode(String code) {
        return ResponseEntity.ok().body(lessonRepository.findByDisciplineCode(code));
    }

    public ResponseEntity<?> findByAttendanceId(Long id) {
        return ResponseEntity.ok().body(lessonRepository.findByAttendancesId(id));
    }

}