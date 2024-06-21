package org.example.develociraptor.domain.techstack.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.develociraptor.domain.user.entity.User;
import org.example.develociraptor.global.idclass.UserTechStackId;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserTechStack {

	@EmbeddedId
	private UserTechStackId userTechStackId;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("techStackId")
	@JoinColumn(name = "tech_stack_id")
	private TechStack techStack;
}
