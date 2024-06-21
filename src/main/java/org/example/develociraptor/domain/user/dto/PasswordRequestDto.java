package org.example.develociraptor.domain.user.dto;


import org.example.develociraptor.global.annotation.ValidatePassword;
import org.springframework.security.core.parameters.P;

public record PasswordRequestDto(
	@ValidatePassword
	String currentPassword,

	@ValidatePassword
	String newPassword,

	@ValidatePassword
	String validatePassword) {
	
}
