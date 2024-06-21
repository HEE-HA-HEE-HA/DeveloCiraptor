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
import org.example.develociraptor.domain.member.entity.Member;
import org.example.develociraptor.global.idclass.MemberTechStackId;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberTechStack {

	@EmbeddedId
	private MemberTechStackId memberTechStackId;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("memberId")
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("techStackId")
	@JoinColumn(name = "tech_stack_id")
	private TechStack techStack;
}
