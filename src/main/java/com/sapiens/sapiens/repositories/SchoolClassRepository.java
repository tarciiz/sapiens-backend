package com.sapiens.sapiens.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sapiens.sapiens.domain.schoolClass.SchoolClass;
import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, String> {

    Optional<SchoolClass> findByCode(String code);

    boolean existsByCode(String code);

    Optional<SchoolClass> findByStudentsId(Long id);

    Optional<SchoolClass> findByDisciplinesCode(String code);

    List<SchoolClass> findBySchoolId(Long id);

}
