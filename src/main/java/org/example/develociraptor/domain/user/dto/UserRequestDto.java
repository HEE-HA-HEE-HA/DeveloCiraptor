package org.example.develociraptor.domain.user.dto;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.develociraptor.domain.techstack.dto.TechStackRequestDto;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class UserRequestDto {

    private String nickName;

    private String introduction;

    private short positionId;

    private List<TechStackRequestDto> techStackRequestDtos;

}
