package org.example.develociraptor.domain.message.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.develociraptor.domain.chatroom.entity.ChatUser;
import org.example.develociraptor.global.base.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE message SET is_deleted = true where message_id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "messages")
public class Message extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "member_id"),
		@JoinColumn(name = "chat_room_id")
	})
	private ChatUser chatRoom;

	@Size(max = 1000)
	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;

	/*
		READ : 읽었을 때
		UNREAD : 안 읽었을 때
		MODIFIED : 수정했을 때
		DELETED : 삭제했을 때
	 */
	@Enumerated(EnumType.STRING)
	private MessageState state;

}
