package org.example.develociraptor.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @Email(message = "이메일 형태가 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @Size(min = 4, max = 20, message = "비밀번호는 최소 4글자 최대 20글자만 가능합니다.")
    @Pattern(
        regexp = "^[a-zA-Z0-9!@#$]*$",
        message = "특수문자는 !@#$만 사용 가능합니다."
    )
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
}
