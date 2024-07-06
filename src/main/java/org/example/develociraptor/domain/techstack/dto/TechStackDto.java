package org.example.develociraptor.domain.techstack.dto;

import org.example.develociraptor.domain.techstack.entity.TechStack;

public record TechStackDto(
	Short techStackId,
	String name) {

	public TechStackDto(TechStack techStack) {
		this(techStack.getId(), techStack.getName());
	}

}
