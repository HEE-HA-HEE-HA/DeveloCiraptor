package org.example.develociraptor.global.security;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.develociraptor.domain.user.entity.User;
import org.example.develociraptor.domain.user.repository.UserJpaRepository;
import org.example.develociraptor.global.exception.ErrorMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }


    @Transactional(readOnly = true)
    public UserDetailsImpl getUserDetails(String email) throws UsernameNotFoundException{
        User user = userJpaRepository.findByEmail(email).orElseThrow(
            () -> new EntityNotFoundException(ErrorMessage.MEMBER_NOT_FOUND.getMessage())
        );

        UserDetailsDto userDetailsDto = UserDetailsDto.builder()
            .id(user.getId())
            .nickName(user.getNickName())
            .email(email)
            .positionId(user.getPosition().getId())
            .introduction(user.getIntroduction())
            .build();


        return new UserDetailsImpl(userDetailsDto);
    }

}
