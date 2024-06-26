package org.example.develociraptor.domain.user.service;

import static org.example.develociraptor.global.exception.ErrorMessage.*;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.example.develociraptor.domain.position.entity.Position;
import org.example.develociraptor.domain.position.repository.PositionJpaRepository;
import org.example.develociraptor.domain.user.dto.LoginRequestDto;
import org.example.develociraptor.domain.user.dto.PasswordRequestDto;
import org.example.develociraptor.domain.user.dto.SignupRequestDto;
import org.example.develociraptor.domain.user.dto.UserRequestDto;
import org.example.develociraptor.domain.user.dto.UserResponseDto;
import org.example.develociraptor.domain.user.entity.User;
import org.example.develociraptor.domain.user.repository.UserJpaRepository;
import org.example.develociraptor.global.jwt.JwtUtil;
import org.example.develociraptor.global.security.UserDetailsDto;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

	private final UserJpaRepository userJpaRepository;
	private final PositionJpaRepository positionJpaRepository;


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

		validatePassword(user.isPasswordCheck(passwordEncoder, loginRequestDto.getPassword()));

		return jwtUtil.createToken(user.getEmail());
	}


	public UserResponseDto getMyInfo(UserDetailsDto user) {
        /*
        TODO : techStack 만들어지면 반환값 변경
         */
		return UserResponseDto.builder()
			.id(user.id())
			.email(user.email())
			.nickName(user.nickName())
			.introduction(user.introduction())
			.techStackRequestDtos(new ArrayList<>())
			.build();
	}

	public UserResponseDto getUserInfo(Long userId) {
		User user = userJpaRepository.findById(userId)
			.orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND.getMessage()));

		return createUserResponseDto(user);
	}

	@Transactional
	public UserResponseDto updateMyInfo(String email, UserRequestDto userRequestDto) {
		User user = findByEmail(email);

		Position position = positionJpaRepository.findById(userRequestDto.getPositionId())
			.orElseThrow(() -> new EntityNotFoundException(POSITION_NOT_FOUND.getMessage()));

		user.update(userRequestDto.getNickName(), userRequestDto.getIntroduction(), position);

		userJpaRepository.saveAndFlush(user);

		return createUserResponseDto(user);
	}

	@Transactional
	public String updatePassword(String email, PasswordRequestDto passwordRequestDto) {
		User user = findByEmail(email);

		boolean isNewPassword =
			passwordRequestDto.newPassword().equals(passwordRequestDto.validatePassword());

		validatePassword(
			user.isPasswordCheck(passwordEncoder, passwordRequestDto.currentPassword()));

		if (!isNewPassword) {
			throw new BadCredentialsException(PASSWORD_NOT_MATCH.getMessage());
		}

		user.updatePassword(passwordEncoder.encode(passwordRequestDto.newPassword()));

		return "비밀번호 수정이 완료되었습니다.";
	}

	@Transactional
	public String deleteUser(String email) {
		User user = findByEmail(email);
		userJpaRepository.delete(user);

		return "탈퇴가 완료되었습니다.";
	}

	private UserResponseDto createUserResponseDto(User user) {
		return UserResponseDto.builder()
			.id(user.getId())
			.email(user.getEmail())
			.nickName(user.getNickName())
			.introduction(user.getIntroduction())
			.techStackRequestDtos(new ArrayList<>())
			.build();
	}

	private User findByEmail(String email) {
		return userJpaRepository.findByEmail(email)
			.orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND.getMessage()));
	}

	public User findById(Long id) {
		return userJpaRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND.getMessage()));
	}

	private void validatePassword(boolean isPassword) {
		if (!isPassword) {
			throw new BadCredentialsException(PASSWORD_NOT_MATCH.getMessage());
		}
	}
}
