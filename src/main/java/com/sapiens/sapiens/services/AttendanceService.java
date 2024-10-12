package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.domain.attendance.Attendance;
import com.sapiens.sapiens.repositories.AttendanceRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public ResponseEntity<?> save(Attendance attendance) {
        return ResponseEntity.ok().body(attendanceRepository.save(attendance));
    }

    public ResponseEntity<?> saveMany(Iterable<Attendance> attendances) {

        for (Attendance attendance : attendances) {
            attendanceRepository
                    .findByStudentIdAndLessonId(attendance.getStudent().getId(), attendance.getLesson().getId())
                    .ifPresentOrElse(
                            (existingAttendance) -> {
                                existingAttendance.setAttendedCount(attendance.getAttendedCount());
                                existingAttendance.setPresent(attendance.isPresent());
                                attendanceRepository.save(existingAttendance);
                            },
                            () -> {
                                attendanceRepository.save(attendance);
                            });
        }

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> delete(Long id) {
        attendanceRepository.deleteById(id);
        return ResponseEntity.ok().body("Aula excluída com sucesso.");
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(attendanceRepository.findAll());
    }

    public ResponseEntity<?> findByStudentId(Long id) {
        return ResponseEntity.ok().body(attendanceRepository.findByStudentId(id));
    }

    public ResponseEntity<?> findByLessonId(Long id) {
        return ResponseEntity.ok().body(attendanceRepository.findByLessonId(id));
    }

}
