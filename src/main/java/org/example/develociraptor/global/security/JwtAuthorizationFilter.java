package org.example.develociraptor.global.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.develociraptor.global.dto.ResponseDto;
import org.example.develociraptor.global.jwt.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j(topic = "JWT 검증 및 인가")
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        String token = jwtUtil.getTokenFromHeader(request);

        if (Objects.nonNull(token)) {
            if (jwtUtil.validateToken(token)) {
                Claims info = jwtUtil.getUserInfoFromToken(token);

                // 인증정보에 요저정보(username) 넣기
                // username -> user 조회
                String email = info.getSubject();
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                // -> userDetails 에 담고
                UserDetailsImpl userDetails = userDetailsService.getUserDetails(email);
                // -> authentication의 principal 에 담고
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
                // -> securityContent 에 담고
                context.setAuthentication(authentication);
                // -> SecurityContextHolder 에 담고
                SecurityContextHolder.setContext(context);
                // -> 이제 @AuthenticationPrincipal 로 조회할 수 있음
            } else {
                // 인증정보가 존재하지 않을때 <- 수정
                ResponseDto commonResponseDto = ResponseDto.of(HttpStatus.BAD_REQUEST, "토큰이 유효하지 않습니다.").getBody();
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json; charset=UTF-8");
                response.getWriter().write(objectMapper.writeValueAsString(commonResponseDto));
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    // 인증 처리
    public void setAuthentication(String email) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(email);
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    // 인증 객체 생성
    private Authentication createAuthentication(String email) {
        UserDetails userDetails = userDetailsService.getUserDetails(email);
        return new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());
    }
}
