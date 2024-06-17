package org.example.develociraptor.domain.user.repository;

import java.util.Optional;
import org.example.develociraptor.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
