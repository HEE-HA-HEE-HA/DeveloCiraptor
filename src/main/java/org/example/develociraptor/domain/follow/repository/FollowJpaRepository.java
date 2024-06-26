package org.example.develociraptor.domain.follow.repository;

import java.util.List;
import org.example.develociraptor.domain.follow.entity.Follow;
import org.example.develociraptor.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowJpaRepository extends JpaRepository<Follow, Long> {

    Follow findByFromUserAndToUser(User fromUser, User toUser);

    List<Follow> findByFromUser(User fromUser);

    List<Follow> findByToUser(User toUser);

    Integer countByToUser(User toUser);

    Integer countByFromUser(User fromUser);

}
