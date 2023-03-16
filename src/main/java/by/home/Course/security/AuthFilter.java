package by.home.Course.security;

import by.home.Course.entity.dto.CommonException;
import by.home.Course.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
    JwtUtil jwtUtil;
    UserService userService;
    ObjectMapper mapper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(authHeader) || !authHeader.contains("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String token = authHeader.split(" ")[1];
        try {
            final Claims claims = jwtUtil.validateAndGet(token);
            Long id = Long.parseLong((String) claims.get("userId"));
            Date expiration = claims.getExpiration();
            if (id == null) {
                filterChain.doFilter(request, response);
                return;
            }
            userService.getById(id)
                    .ifPresentOrElse(user -> {
                                if (expiration.getTime() - new Date().getTime() < 60000) {
                                    String updatedToken = jwtUtil.generateToken(user);
                                    response.setHeader("Authorization", updatedToken);
                                }
                                SecurityContextHolder
                                        .getContext()
                                        .setAuthentication(new UsernamePasswordAuthenticationToken(user, null, CollectionUtils.isEmpty(user.getAuthorities()) ? List.of() : user.getAuthorities()));
                            },
                            () -> SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(null, null, List.of())));
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException ex) {
            CommonException errorResponse = CommonException.builder()
                    .code(HttpStatus.FORBIDDEN.value())
                    .message("Токен истек")
                    .build();
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(mapper.writeValueAsString(errorResponse));
        }

    }
}
