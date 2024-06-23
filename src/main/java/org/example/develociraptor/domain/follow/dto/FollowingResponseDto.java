package org.example.develociraptor.domain.follow.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.develociraptor.domain.follow.entity.Follow;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class FollowingResponseDto {
    private Long id;
    private String nickName;
    private short positionId;

    @Builder
    public FollowingResponseDto(Follow follow) {
        this.id = follow.getId();
        this.nickName = follow.getFromUser().getNickName();
        this.positionId = follow.getFromUser().getPosition().getId();
    }
}
