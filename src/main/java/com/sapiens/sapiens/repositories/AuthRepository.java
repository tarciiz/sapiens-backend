package com.sapiens.sapiens.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import com.sapiens.sapiens.domain.user.User;
import com.sapiens.sapiens.domain.user.UserRole;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);

    Optional<User> findUserByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByRole(UserRole role);

    Optional<User> findByEmailAndPassword(String email, String password);

}
