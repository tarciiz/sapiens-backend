package com.sapiens.sapiens.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sapiens.sapiens.domain.attendance.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudentId(Long id);

    List<Attendance> findByLessonId(Long id);

    Optional<Attendance> findByStudentIdAndLessonId(Long studentId, Long lessonId);
}
