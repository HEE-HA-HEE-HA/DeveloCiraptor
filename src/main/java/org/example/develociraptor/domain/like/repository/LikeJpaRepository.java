package org.example.develociraptor.domain.like.repository;

import org.example.develociraptor.domain.like.entity.Like;
import org.example.develociraptor.global.idclass.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeJpaRepository extends JpaRepository<Like, LikeId> {

}
