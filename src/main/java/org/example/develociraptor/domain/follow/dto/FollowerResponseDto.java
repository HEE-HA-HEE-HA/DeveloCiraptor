package org.example.develociraptor.domain.follow.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.develociraptor.domain.follow.entity.Follow;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class FollowerResponseDto {
    private Long id;
    private String nickName;
    private short positionId;

    @Builder
    public FollowerResponseDto(Follow follow) {
        this.id = follow.getId();
        this.nickName = follow.getToUser().getNickName();
        this.positionId = follow.getToUser().getPosition().getId();
    }
}
