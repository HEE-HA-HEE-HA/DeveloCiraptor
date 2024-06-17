package org.example.develociraptor.domain.hashtag.repository;

import org.example.develociraptor.domain.hashtag.entity.FeedHashtag;
import org.example.develociraptor.global.idclass.FeedHashtagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedHashtagJpaRepository extends JpaRepository<FeedHashtag, FeedHashtagId> {

}
