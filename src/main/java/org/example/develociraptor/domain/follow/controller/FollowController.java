package org.example.develociraptor.domain.follow.controller;


import lombok.RequiredArgsConstructor;
import org.example.develociraptor.domain.follow.service.FollowService;
import org.example.develociraptor.global.dto.ResponseDto;
import org.example.develociraptor.global.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{userId}/follow")
    public ResponseEntity<ResponseDto<String>> createFollow(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long userId) {
        String message = followService.createFollow(userDetails.getUserDetailsDto().id(), userId);

        return ResponseDto.of(HttpStatus.CREATED, message);
    }

}
