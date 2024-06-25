package org.example.develociraptor.global.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator implements ConstraintValidator<ValidatePassword, String> {

	private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@#$]*$");

	@Override
	public void initialize(ValidatePassword constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		if (password == null || password.isBlank()) {
			return customizeConstraintViolation(context, "비밀번호는 필수 입력 값입니다.");
		}

		if (password.length() < 4 || password.length() > 20) {
			return customizeConstraintViolation(context, "비밀번호는 최소 4글자 최대 20글자만 가능합니다.");
		}

		if (!PASSWORD_PATTERN.matcher(password).matches()) {
			return customizeConstraintViolation(context, "특수문자는 !@#$만 사용 가능합니다.");
		}
		return true;
	}

	private boolean customizeConstraintViolation(ConstraintValidatorContext context,
		String message) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message)
			.addConstraintViolation();
		return false;
	}
}
