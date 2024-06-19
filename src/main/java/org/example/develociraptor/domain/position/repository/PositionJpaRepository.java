package org.example.develociraptor.domain.position.repository;

import org.example.develociraptor.domain.position.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionJpaRepository extends JpaRepository<Position, Short> {

}
