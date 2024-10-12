package com.sapiens.sapiens.repositories;

import com.sapiens.sapiens.domain.lesson.Lesson;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    boolean existsById(Long id);

    List<Lesson> findByDisciplineCode(String code);

    List<Lesson> findByAttendancesId(Long id);

}