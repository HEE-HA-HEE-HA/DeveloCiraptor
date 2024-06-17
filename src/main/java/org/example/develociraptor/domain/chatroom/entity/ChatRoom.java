package org.example.develociraptor.domain.chatroom.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.develociraptor.global.base.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE chat_room SET is_deleted = true where chat_room_id = ?")
@SQLRestriction("is_deleted = false")
public class ChatRoom extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chat_room_id")
	private Long id;

	@Size(max = 50)
	@Column(nullable = false, length = 50)
	private String name;

}
