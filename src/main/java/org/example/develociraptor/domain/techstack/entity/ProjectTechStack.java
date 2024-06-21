package org.example.develociraptor.domain.techstack.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.develociraptor.domain.feed.entity.Feed;
import org.example.develociraptor.global.idclass.ProjectTechStackId;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProjectTechStack {

	@EmbeddedId
	private ProjectTechStackId projectTechStackId;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("feedId")
	@JoinColumn(name = "feed_id")
	private Feed feed;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("techStackId")
	@JoinColumn(name = "tech_stack_id")
	private TechStack techStack;

}
