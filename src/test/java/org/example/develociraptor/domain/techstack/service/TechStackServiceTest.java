package org.example.develociraptor.domain.techstack.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.example.develociraptor.domain.techstack.dto.TechStackDto;
import org.example.develociraptor.domain.techstack.dummy.TechStackDummy;
import org.example.develociraptor.domain.techstack.repository.TechStackJpaRepository;
import org.example.develociraptor.global.exception.ErrorMessage;
import org.example.develociraptor.global.exception.custom.EmptyException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TechStackServiceTest {

	@Autowired
	TechStackJpaRepository techStackJpaRepository;

	@Autowired
	TechStackService techStackService;

	@BeforeEach
	void setUp() {
		techStackJpaRepository.saveAll(TechStackDummy.createTechStackDummys());
	}

	@AfterEach
	void tearDown() {
		techStackJpaRepository.deleteAll();
	}

	@Nested
	class 조회 {

		@Test
		void 성공() {
			List<TechStackDto> techStackDtos = techStackService.getTechStacks();

			assertThat(techStackDtos).isNotEmpty();
			assertThat(techStackDtos)
				.hasSize(TechStackDummy.createTechStackDummys().size());
		}

		@Test
		void 리스트_0개_일_때() {
			techStackJpaRepository.deleteAll();
			assertThatThrownBy(
				() -> techStackService.getTechStacks()
			).isInstanceOf(EmptyException.class)
				.hasMessageContaining(ErrorMessage.EMPTY_ERROR.getMessage());

		}
	}

}