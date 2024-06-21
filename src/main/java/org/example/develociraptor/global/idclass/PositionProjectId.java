package org.example.develociraptor.global.idclass;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@Getter
public class PositionProjectId {
	private Short positionId;
	private Long projectId;
}
