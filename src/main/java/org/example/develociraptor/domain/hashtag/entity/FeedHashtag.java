package org.example.develociraptor.domain.hashtag.entity;

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
import org.example.develociraptor.global.idclass.FeedHashtagId;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedHashtag {

	@EmbeddedId
	private FeedHashtagId feedHashtagId;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("feedId")
	@JoinColumn(name = "feed_id")
	private Feed feed;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("hashtagId")
	@JoinColumn(name = "hashtag_id")
	private Hashtag hashtag;
}
