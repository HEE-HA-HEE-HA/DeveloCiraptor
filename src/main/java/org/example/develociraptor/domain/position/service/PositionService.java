package org.example.develociraptor.domain.position.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.develociraptor.domain.position.dto.PositionInfo;
import org.example.develociraptor.domain.position.repository.PositionJpaRepository;
import org.example.develociraptor.global.exception.custom.EmptyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PositionService {

	private final PositionJpaRepository positionJpaRepository;

	public List<PositionInfo> getPositions() {
		List<PositionInfo> positionInfos = positionJpaRepository
			.findAll()
			.stream()
			.map(PositionInfo::of).toList();

		if (positionInfos.isEmpty()) {
			throw new EmptyException();
		}

		return positionInfos;
	}
}
