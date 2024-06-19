package org.example.develociraptor.global.security;

import lombok.Builder;

@Builder
public record UserDetailsDto(Long id, String nickName, String email) {}
