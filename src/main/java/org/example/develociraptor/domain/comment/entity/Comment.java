package org.example.develociraptor.domain.comment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.develociraptor.domain.feed.entity.Feed;
import org.example.develociraptor.domain.user.entity.User;
import org.example.develociraptor.global.base.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE comment SET is_deleted = true where feed_id = ?")
@SQLRestriction("is_deleted = false")
public class Comment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "feed_id")
	private Feed feed;

	@Size(max = 1500)
	@Column(nullable = false, length = 1500, columnDefinition = "TEXT")
	private String content;

	@Column
	private Long parentId;

	@Builder
	public Comment(User user, Feed feed, String content) {
		this.user = user;
		this.feed = feed;
		this.content = content;
		this.parentId = null;
	}
}
