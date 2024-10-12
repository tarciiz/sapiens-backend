package com.sapiens.sapiens.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sapiens.sapiens.domain.school.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

    Optional<School> findByName(String name);

    Optional<School> findByAdminId(Long id);

    Optional<School> findBySecretariatId(Long id);

    Optional<School> findByStudentsId(Long id);

    Optional<School> findByTeachersId(Long id);

    Optional<School> findByDisciplinesCode(String code);

    Optional<School> findBySchoolClassesCode(String code);

}
