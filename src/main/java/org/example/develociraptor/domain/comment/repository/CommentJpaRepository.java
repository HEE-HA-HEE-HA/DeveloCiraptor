package org.example.develociraptor.domain.comment.repository;

import org.example.develociraptor.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

}
