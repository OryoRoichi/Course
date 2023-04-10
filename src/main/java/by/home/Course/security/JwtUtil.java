package by.home.Course.security;

import by.home.Course.config.ProjectConfig;
import by.home.Course.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtUtil {
    ProjectConfig config;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(config.getSecurity().getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(final User user) {
        final SecretKey key = getSecretKey();
        final Date expirationDate = Date.from(LocalDateTime.now()
                .plusHours(config.getSecurity().getTokenLifetimeHours())
                .atZone(ZoneId.systemDefault())
                .toInstant());
        return Jwts.builder()
                .setSubject("cource")
                .addClaims(Map.of("userId", user.getId().toString()))
                .setExpiration(expirationDate)
                .setIssuedAt(new Date())
                .signWith(key)
                .compact();
    }

    public Claims validateAndGet(final String token) {
        final SecretKey key = getSecretKey();
        return Jwts.parserBuilder()
                .requireSubject("cource")
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
