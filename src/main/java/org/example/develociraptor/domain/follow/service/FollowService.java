package org.example.develociraptor.domain.follow.service;

import lombok.RequiredArgsConstructor;
import org.example.develociraptor.domain.follow.entity.Follow;
import org.example.develociraptor.domain.follow.repository.FollowJpaRepository;
import org.example.develociraptor.domain.user.entity.User;
import org.example.develociraptor.domain.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowService {

    private FollowJpaRepository followJpaRepository;
    private UserService userService;

    public String createFollow(Long fromId, Long toId) {
        User fromUser =  userService.findById(fromId);
        User toUser = userService.findById(toId);

        Follow follow = Follow.builder()
            .fromUser(fromUser)
            .toUser(toUser)
            .build();
        followJpaRepository.save(follow);

        return "팔로우 되었습니다.";
    }
}
