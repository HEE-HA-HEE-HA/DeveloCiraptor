package org.example.develociraptor.domain.member.repository;

import org.example.develociraptor.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

}
