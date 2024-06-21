package org.example.develociraptor.domain.techstack.repository;

import org.example.develociraptor.domain.techstack.entity.UserTechStack;
import org.example.develociraptor.global.idclass.UserTechStackId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTechStackJpaRepository extends JpaRepository<UserTechStack, UserTechStackId> {

}
