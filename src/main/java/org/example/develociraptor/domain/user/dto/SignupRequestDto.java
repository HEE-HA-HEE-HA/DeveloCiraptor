package org.example.develociraptor.domain.user.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.develociraptor.domain.techstack.dto.TechStackRequestDto;
import org.example.develociraptor.global.annotation.ValidatePassword;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class SignupRequestDto {

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Size(min = 2, max = 12, message = "닉네임은 최소 2글자 최대 12글자만 가능합니다.")
    private String nickName;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형태가 올바르지 않습니다.")
    private String email;

    @ValidatePassword
    private String password;

    private short positionId;

    private List<TechStackRequestDto> techStackRequestDtos;

}
