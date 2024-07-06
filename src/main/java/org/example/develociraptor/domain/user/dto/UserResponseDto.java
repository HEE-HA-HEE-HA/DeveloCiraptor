package org.example.develociraptor.domain.user.dto;

import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.develociraptor.domain.techstack.dto.TechStackDto;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String nickName;
    private String email;
    private String introduction;
    private short positionId;
    private List<TechStackDto> techStackDtos;

    @Builder
    public UserResponseDto(
        Long id,
        String nickName,
        String email,
        String introduction,
        short positionId,
        List<TechStackDto> techStackDtos) {

        this.id = id;
        this.nickName = nickName;
        this.email = email;
        this.introduction = introduction;
        this.positionId = positionId;
        this.techStackDtos = techStackDtos;
    }
}
