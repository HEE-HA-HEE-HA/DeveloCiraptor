package org.example.develociraptor.domain.feed.repository;


import org.example.develociraptor.domain.feed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedJpaRepository extends JpaRepository<Feed, Long> {

}
