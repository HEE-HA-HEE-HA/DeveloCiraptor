package org.example.develociraptor.domain.user.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.develociraptor.domain.user.dto.LoginRequestDto;
import org.example.develociraptor.domain.user.dto.SignupRequestDto;
import org.example.develociraptor.domain.user.dto.UserResponseDto;
import org.example.develociraptor.domain.user.entity.User;
import org.example.develociraptor.domain.user.repository.UserJpaRepository;
import org.example.develociraptor.global.jwt.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public Long signup(SignupRequestDto signupRequestDto) {
        User user = User.builder()
            .email(signupRequestDto.getEmail())
            .nickName(signupRequestDto.getNickName())
            .password(passwordEncoder.encode(signupRequestDto.getPassword()))
            .build();

        return userJpaRepository.save(user).getId();
    }

    public String login(LoginRequestDto loginRequestDto) {
        User user = findByEmail(loginRequestDto.getEmail());

        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new EntityNotFoundException("비밀번호가 일치하지 않습니다.");
        }

        return jwtUtil.createToken(user.getEmail());
    }


    public UserResponseDto getMyInfo(User user) {
        return UserResponseDto.builder()
            .id(user.getId())
            .email(user.getEmail())
            .nickName(user.getNickName())
            .introduction(user.getIntroduction())
            .build();
    }

    public UserResponseDto getUserInfo(Long userId) {
        return null;
    }

    private User findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("해당 유저를 찾을 수 없습니다."));
    }
}
