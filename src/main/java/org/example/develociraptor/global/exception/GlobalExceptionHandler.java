package org.example.develociraptor.global.exception;

import jakarta.persistence.EntityNotFoundException;
import java.util.Objects;
import org.example.develociraptor.global.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDto<String>> validExceptionHandler(
		MethodArgumentNotValidException e) {
		return createResponse(HttpStatus.BAD_REQUEST,
			Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
	}

	@ExceptionHandler({
		EntityNotFoundException.class,
		BadCredentialsException.class
	})
	public ResponseEntity<ResponseDto<String>> badRequestExceptionHandler(RuntimeException e) {
		return createResponse(HttpStatus.BAD_REQUEST, e.getMessage());
	}

	private ResponseEntity<ResponseDto<String>> createResponse(HttpStatus status, String message) {
		return ResponseDto.of(status, message);
	}

}

