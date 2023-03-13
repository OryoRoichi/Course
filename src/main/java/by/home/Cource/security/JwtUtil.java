package by.home.Cource.security;

import by.home.Cource.config.ProjectConfig;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class JwtUtil {
    ProjectConfig config;
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(config.getSecurity().getSecretKey().getBytes(StandardCharsets.UTF_8));
    }


}
