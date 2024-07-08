package org.example.develociraptor.domain.techstack.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.develociraptor.domain.techstack.dto.TechStackDto;
import org.example.develociraptor.domain.techstack.service.TechStackService;
import org.example.develociraptor.global.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tech-stack")
public class TechStackController {

	private final TechStackService techStackService;

	@GetMapping
	public ResponseEntity<ResponseDto<List<TechStackDto>>> getTechStacks() {

		List<TechStackDto> techStackDtos = techStackService.getTechStacks();

		return ResponseDto.of(HttpStatus.OK, techStackDtos);
	}

}
