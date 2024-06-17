package org.example.develociraptor.domain.blacklist.repository;

import org.example.develociraptor.domain.blacklist.entity.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackJpaRepository extends JpaRepository<Blacklist, Long> {

}
