package org.example.develociraptor.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.develociraptor.domain.user.entity.User;
import org.example.develociraptor.global.jwt.JwtUtil;
import org.example.develociraptor.global.security.JwtAuthorizationFilter;
import org.example.develociraptor.global.security.UserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtUtil jwtUtil;
//    private final RefreshTokenRepository refreshTokenRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final ObjectMapper objectMapper;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
        throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(jwtUtil, userDetailsService, objectMapper);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement((sessionManagement) ->
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.authorizeHttpRequests((authorizeHttpRequests) ->
            authorizeHttpRequests
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll() // resources 접근 허용 설정
                .requestMatchers("/api/v1/users/signup").permitAll()
                .requestMatchers("/api/v1/users/login").permitAll()
                // 팔로워
                .requestMatchers(HttpMethod.GET,"/api/v1/users/{userId}/**").permitAll()
//                // 카카오 로그인
//                .requestMatchers("/").permitAll()
//                .requestMatchers("/v1/users/kakao/callback").permitAll()
//                .requestMatchers("/v1/users/login-page").permitAll()
//                .requestMatchers("/v1/users/signup-page").permitAll()
//                // 이메일 인증
//                .requestMatchers("v1/users/emails/authcode").permitAll()
//                .requestMatchers("v1/users/emails/authcode/verify").permitAll()
//                .requestMatchers("/v1/live-bids/auction-items/{auctionItemId}").permitAll()
                .anyRequest().authenticated() // 그 외 모든 요청 인증처리
        );

        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
