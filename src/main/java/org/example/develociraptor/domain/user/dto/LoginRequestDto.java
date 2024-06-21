package org.example.develociraptor.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.example.develociraptor.global.annotation.ValidatePassword;

@Getter
public class LoginRequestDto {

    @Email(message = "이메일 형태가 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @ValidatePassword
    private String password;
}
