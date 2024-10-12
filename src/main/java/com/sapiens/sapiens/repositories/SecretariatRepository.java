package com.sapiens.sapiens.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sapiens.sapiens.domain.secretariat.Secretariat;
import java.util.Optional;

@Repository
public interface SecretariatRepository extends JpaRepository<Secretariat, Long> {

    Optional<Secretariat> findByName(String name);

    Optional<Secretariat> findBySchoolsId(Long id);

    Optional<Secretariat> findBySuperAdminId(Long id);

}
