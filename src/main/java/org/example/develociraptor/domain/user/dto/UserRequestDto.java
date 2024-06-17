package org.example.develociraptor.domain.user.dto;

import java.util.List;
import org.example.develociraptor.domain.techstack.dto.TechStackRequestDto;

public class UserRequestDto {

    private String nickName;

    private String introduction;

    private short positionId;

    private List<TechStackRequestDto> techStackRequestDtos;

}
