package org.example.develociraptor.global.idclass;


import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@Getter
public class ChatMemberId implements Serializable {
	private Long memberId;
	private Long chatRoomId;
}
