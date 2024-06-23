package org.example.develociraptor.domain.follow.repository;

import org.example.develociraptor.domain.follow.entity.Follow;
import org.example.develociraptor.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowJpaRepository extends JpaRepository<Follow, Long> {

    Follow findByFromUserAndToUser(User fromUser, User toUser);

}
