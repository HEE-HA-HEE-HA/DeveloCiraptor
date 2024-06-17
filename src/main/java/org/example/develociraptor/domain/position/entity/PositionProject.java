package org.example.develociraptor.domain.position.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.develociraptor.domain.feed.entity.Feed;
import org.example.develociraptor.global.idclass.PositionProjectId;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PositionProject {

	@EmbeddedId
	private PositionProjectId id;

	@MapsId("positionId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "position_id")
	private Position position;

	@MapsId("projectId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Feed feed;
}
