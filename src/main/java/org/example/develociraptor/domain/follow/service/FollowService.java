package org.example.develociraptor.domain.follow.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.develociraptor.domain.follow.dto.FollowerResponseDto;
import org.example.develociraptor.domain.follow.dto.FollowingResponseDto;
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

    private final FollowJpaRepository followJpaRepository;
    private final UserService userService;

    public String createFollow(Long fromId, Long toId) {
        User fromUser = userService.findById(fromId);
        User toUser = userService.findById(toId);

        Follow follow = Follow.builder()
            .fromUser(fromUser)
            .toUser(toUser)
            .build();
        followJpaRepository.save(follow);

        return "팔로우 되었습니다.";
    }

    public String deleteFollow(Long fromId, Long toId) {
        User fromUser = userService.findById(fromId);
        User toUser = userService.findById(toId);
        Follow follow = followJpaRepository.findByFromUserAndToUser(fromUser, toUser);

        followJpaRepository.delete(follow);

        return "언팔로우 되었습니다.";
    }

    public List<FollowerResponseDto> getFollowers(Long userId) {
        User user = userService.findById(userId);

        return followJpaRepository.findByToUser(user)
            .stream().map(FollowerResponseDto::new).toList();
    }

    public List<FollowingResponseDto> getFollowing(Long userId) {
        User user = userService.findById(userId);

        return followJpaRepository.findByFromUser(user)
            .stream().map(FollowingResponseDto::new).toList();
    }

    public Integer getTotalFollowers(Long userId) {
        User user = userService.findById(userId);

        return followJpaRepository.countByToUser(user);
    }
}
