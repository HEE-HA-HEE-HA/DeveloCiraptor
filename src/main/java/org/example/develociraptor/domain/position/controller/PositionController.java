package org.example.develociraptor.domain.position.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.develociraptor.domain.position.dto.PositionInfo;
import org.example.develociraptor.domain.position.service.PositionService;
import org.example.develociraptor.global.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/positions")
public class PositionController {

	private final PositionService positionService;

	@GetMapping()
	public ResponseEntity<ResponseDto<List<PositionInfo>>> getPositions() {

		List<PositionInfo> positions = positionService.getPositions();
		return ResponseDto.of(HttpStatus.OK, positions);
	}
}
