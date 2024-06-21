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
public class ChatUserId implements Serializable {
	private Long userId;
	private Long chatRoomId;
}
