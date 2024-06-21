package org.example.develociraptor.domain.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.develociraptor.domain.techstack.entity.TechStack;
import org.example.develociraptor.domain.techstack.service.TechStackService;
import org.example.develociraptor.domain.techstack.service.UserTechStackService;
import org.example.develociraptor.domain.user.dto.LoginRequestDto;
import org.example.develociraptor.domain.user.dto.PasswordRequestDto;
import org.example.develociraptor.domain.user.dto.SignupRequestDto;
import org.example.develociraptor.domain.user.dto.UserRequestDto;
import org.example.develociraptor.domain.user.dto.UserResponseDto;
import org.example.develociraptor.domain.user.entity.User;
import org.example.develociraptor.domain.user.service.UserService;
import org.example.develociraptor.global.dto.ResponseDto;
import org.example.develociraptor.global.jwt.JwtUtil;
import org.example.develociraptor.global.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "USER API")
public class UserController {

    private final UserService userService;
    private final TechStackService techStackService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<Long>> signup(
        @Valid @RequestBody SignupRequestDto signupRequestDto) {

        Long id = userService.signup(signupRequestDto);

        return ResponseDto.of(HttpStatus.CREATED, id);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<Void>> login(
        @Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse
    ) {
        String accessToken = userService.login(loginRequestDto);
        httpServletResponse.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);
        return ResponseDto.of(HttpStatus.OK, null);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<UserResponseDto>> getMyInfo(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        UserResponseDto userResponseDto = userService.getMyInfo(userDetails.getUserDetailsDto());

        return ResponseDto.of(HttpStatus.OK, userResponseDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseDto<UserResponseDto>> getUserInfo(
        @PathVariable Long userId) {
        UserResponseDto userResponseDto = userService.getUserInfo(userId);

        return ResponseDto.of(HttpStatus.OK, userResponseDto);
    }

/*    @PutMapping
    public ResponseEntity<ResponseDto<UserResponseDto>> updateMyInfo(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody UserRequestDto userRequestDto) {

        UserResponseDto userResponseDto = userService.updateMyInfo(userDetails.getUserDetailsDto().email(), userRequestDto);
        List<TechStack> techStacks = techStackService.updateMyTechStack(userDetails.getUserDetailsDto().id(), userResponseDto.getTechStackRequestDtos());

        return ResponseDto.of(HttpStatus.OK, userResponseDto);
    }*/

    @PutMapping("/password")
    public ResponseEntity<ResponseDto<String>> updatePassword(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody PasswordRequestDto passwordRequestDto) {
        String message = userService.updatePassword(userDetails.getUserDetailsDto().email(),
            passwordRequestDto);

        return ResponseDto.of(HttpStatus.OK, message);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto<String>> deleteUser(
        @AuthenticationPrincipal UserDetailsImpl userDetails){
        String message = userService.deleteUser(userDetails.getUserDetailsDto().email());

        return ResponseDto.of(HttpStatus.OK, message);
    }

}
