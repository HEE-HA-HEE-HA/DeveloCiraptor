package org.example.develociraptor.global.exception.custom;

import org.example.develociraptor.global.exception.ErrorMessage;

public class EmptyException extends RuntimeException{

	public EmptyException() {
		super(ErrorMessage.EMPTY_ERROR.getMessage());
	}
}
