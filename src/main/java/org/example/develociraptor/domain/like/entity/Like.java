package org.example.develociraptor.domain.like.entity;

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
import org.example.develociraptor.domain.member.entity.Member;
import org.example.develociraptor.global.idclass.LikeId;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "likes")
public class Like {

	@EmbeddedId
	private LikeId likeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("feedId")
	@JoinColumn(name = "feed_id")
	private Feed feed;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("memberId")
	@JoinColumn(name = "member_id")
	private Member member;
}
