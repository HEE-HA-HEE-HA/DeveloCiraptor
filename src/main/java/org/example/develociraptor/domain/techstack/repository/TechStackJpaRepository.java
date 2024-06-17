package org.example.develociraptor.domain.techstack.repository;

import org.example.develociraptor.domain.techstack.entity.TechStack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechStackJpaRepository extends JpaRepository<TechStack, Long> {

}
