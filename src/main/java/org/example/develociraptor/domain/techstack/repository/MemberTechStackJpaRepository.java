package org.example.develociraptor.domain.techstack.repository;

import org.example.develociraptor.domain.techstack.entity.MemberTechStack;
import org.example.develociraptor.global.idclass.MemberTechStackId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTechStackJpaRepository extends JpaRepository<MemberTechStack, MemberTechStackId> {

}
