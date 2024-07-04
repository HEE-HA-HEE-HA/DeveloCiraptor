package org.example.develociraptor.domain.position.dto;

import lombok.Builder;
import org.example.develociraptor.domain.position.entity.Position;

@Builder
public record PositionInfo(
	Short id,
	String name
) {

	public static PositionInfo of(Position position) {
		return PositionInfo.builder()
			.id(position.getId())
			.name(position.getName())
			.build();
	}

}
