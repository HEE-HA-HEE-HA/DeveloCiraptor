package org.example.develociraptor.domain.feed.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.develociraptor.domain.member.entity.Member;
import org.example.develociraptor.global.base.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE feed SET is_deleted = true where feed_id = ?")
@SQLRestriction("is_deleted = false")
@DiscriminatorColumn(name = "DTYPE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Feed extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feed_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;

	private int views;

	@Builder
	public Feed(Member member, String content) {
		this.member = member;
		this.content = content;
	}
}
