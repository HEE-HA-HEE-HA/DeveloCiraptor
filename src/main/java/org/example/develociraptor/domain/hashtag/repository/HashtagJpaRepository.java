package org.example.develociraptor.domain.hashtag.repository;

import org.example.develociraptor.domain.hashtag.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagJpaRepository extends JpaRepository<Hashtag, Long> {

}
