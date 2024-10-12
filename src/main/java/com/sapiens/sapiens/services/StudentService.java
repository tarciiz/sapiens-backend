package com.sapiens.sapiens.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.domain.attendance.Attendance;
import com.sapiens.sapiens.domain.discipline.Discipline;
import com.sapiens.sapiens.domain.grade.Grade;
import com.sapiens.sapiens.domain.lesson.Lesson;
import com.sapiens.sapiens.domain.student.Report;
import com.sapiens.sapiens.domain.student.Student;
import com.sapiens.sapiens.domain.student.Subject;
import com.sapiens.sapiens.domain.student.SubjectGrade;
import com.sapiens.sapiens.infra.exceptions.BusinessException;
import com.sapiens.sapiens.repositories.StudentRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public ResponseEntity<?> save(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new BusinessException("E-mail já registrado.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(student.getPassword());

        student.setPassword(encryptedPassword);
        student.setFirstLogin(true);

        return ResponseEntity.ok().body(studentRepository.save(student));
    }

    public ResponseEntity<?> update(Student student) {
        return ResponseEntity.ok().body(studentRepository.save(student));
    }

    public ResponseEntity<?> findByMatriculation(String matriculation) {
        Student student = studentRepository.findByMatriculation(matriculation)
                .orElseThrow(() -> new BusinessException("Estudante não encontrado."));

        return ResponseEntity.ok().body(student);
    }

    public ResponseEntity<?> findByEmail(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Estudante não encontrado."));

        return ResponseEntity.ok().body(student);
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(studentRepository.findAll());
    }

    public ResponseEntity<?> findBySchoolClassCode(String code) {
        return ResponseEntity.ok().body(studentRepository.findBySchoolClassCode(code));
    }

    public ResponseEntity<?> findBySchoolId(Long id) {
        return ResponseEntity.ok().body(studentRepository.findBySchoolId(id));
    }

    private int getCompletedLessons(Discipline discipline) {
        return discipline.getLessons().stream()
                .mapToInt(lesson -> lesson.getManyLessons())
                .sum();
    }

    private int getLessonsAttendedByStudent(Discipline discipline, Student student) {
        int lessonsAttendedByStudent = 0;

        List<Lesson> allLessons = discipline.getLessons();

        Map<Long, Integer> studentAttendancesMap = new HashMap<>();
        for (Attendance attendance : student.getAttendances()) {
            studentAttendancesMap.put(attendance.getLesson().getId(), attendance.getAttendedCount());
        }

        for (Lesson lesson : allLessons) {
            Integer attendanceCount = studentAttendancesMap.get(lesson.getId());

            if (attendanceCount != null) {
                lessonsAttendedByStudent += attendanceCount;
            }

        }

        return lessonsAttendedByStudent;
    }

    private double calculateFinalGrade(Student student, Discipline discipline) {
        var studentGrades = discipline.getEvaluations().stream()
                .flatMap(evaluation -> evaluation.getGrades().stream())
                .filter(grade -> grade.getStudent().getName().equals(student.getName()))
                .mapToDouble(Grade::getValue)
                .toArray();

        double finalGrade = Arrays.stream(studentGrades).average().orElse(0.0);

        return finalGrade;
    }

    public ResponseEntity<?> report(Long id) {
        Student student = studentRepository.getReferenceById(id);

        if (student == null) {
            throw new BusinessException("Estudante não encontrado.");
        }

        var disciplines = student.getSchoolClass().getDisciplines();

        List<SubjectGrade> studentGrades = student.getGrades().stream()
                .map(grade -> new SubjectGrade(grade.getId(), grade.getValue(), grade.getEvaluation())).toList();

        var subjects = disciplines.stream().map(discipline -> {
            int manyLessons = discipline.getManyLessons();

            int completedLessonsByDiscipline = getCompletedLessons(discipline);
            int lessonsAttendedByStudent = getLessonsAttendedByStudent(discipline, student);

            var isCompleted = manyLessons == completedLessonsByDiscipline;

            String status = isCompleted ? "Concluído" : "Cursando";
            double finalGrade = isCompleted ? calculateFinalGrade(student, discipline) : 0.0;

            int lessonsMissed = completedLessonsByDiscipline - lessonsAttendedByStudent;

            double attendancePercentage = 100.0 - ((double) lessonsMissed / manyLessons * 100.0);

            return new Subject(
                    discipline.getCode(),
                    discipline.getName(),
                    manyLessons,
                    completedLessonsByDiscipline,
                    lessonsAttendedByStudent,
                    lessonsMissed,
                    attendancePercentage,
                    status,
                    finalGrade,
                    studentGrades);
        }).toList();

        var report = new Report(student.getName(), student.getMatriculation(), subjects);

        return ResponseEntity.ok().body(report);
    }

}
