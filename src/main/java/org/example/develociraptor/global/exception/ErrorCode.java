package org.example.develociraptor.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	MEMBER_NOT_FOUND("해당 유저를 찾을 수 없습니다.");

	private String message;

}
