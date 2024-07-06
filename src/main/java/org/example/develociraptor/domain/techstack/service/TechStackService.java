package org.example.develociraptor.domain.techstack.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.develociraptor.domain.techstack.dto.TechStackDto;
import org.example.develociraptor.domain.techstack.repository.TechStackJpaRepository;
import org.example.develociraptor.global.exception.custom.EmptyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TechStackService {

	private final TechStackJpaRepository techStackJpaRepository;

	public List<TechStackDto> getTechStacks() {
		List<TechStackDto> techStackDtos = techStackJpaRepository
			.findAll()
			.stream()
			.map(TechStackDto::new)
			.toList();

		if (techStackDtos.isEmpty()) {
			throw new EmptyException();
		}

		return techStackDtos;
	}
}
