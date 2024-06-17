package org.example.develociraptor.domain.chatroom.entity;

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
import org.example.develociraptor.global.idclass.ChatMemberId;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ChatMember {
	@EmbeddedId
	ChatMemberId chatMemberId;

	@MapsId("memberId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@MapsId("chatRoomId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chat_room_id")
	private ChatRoom chatRoom;
}
