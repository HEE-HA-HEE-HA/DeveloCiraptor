package org.example.develociraptor.domain.position.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.example.develociraptor.domain.position.dto.PositionInfo;
import org.example.develociraptor.domain.position.dummy.PositionDummy;
import org.example.develociraptor.domain.position.repository.PositionJpaRepository;
import org.example.develociraptor.global.exception.ErrorMessage;
import org.example.develociraptor.global.exception.custom.EmptyException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PositionServiceTest {

	@Autowired
	PositionService positionService;

	@Autowired
	PositionJpaRepository positionJpaRepository;

	PositionDummy positionDummy = new PositionDummy();


	@BeforeEach
	void setUp() {
		positionJpaRepository.saveAll(positionDummy.getPositionDummys());
	}

	@AfterEach
	void tearDown() {
		positionJpaRepository.deleteAll();
	}


	@Nested
	class 조회 {

		@Test
		void 성공() {
			List<PositionInfo> positions = positionService.getPositions();

			assertThat(positions).isNotEmpty();
			assertThat(positions.size()).isEqualTo(9);
		}

		@Test
		void 리스트_0개_일_떄() {
			positionJpaRepository.deleteAll();
			
			assertThatThrownBy(
				() -> positionService.getPositions()
			).isInstanceOf(EmptyException.class)
				.hasMessageContaining(ErrorMessage.EMPTY_ERROR.getMessage());
		}
	}
}