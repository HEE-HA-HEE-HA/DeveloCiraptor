package org.example.develociraptor.domain.message.repository;

import org.example.develociraptor.domain.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageJpaRepository extends JpaRepository<Message, Long> {

}
