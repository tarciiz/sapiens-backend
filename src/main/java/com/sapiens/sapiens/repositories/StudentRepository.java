package com.sapiens.sapiens.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sapiens.sapiens.domain.student.Student;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByMatriculation(String matriculation);

    Optional<Student> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Student> findBySchoolClassCode(String code);

    List<Student> findBySchoolId(Long id);
}
