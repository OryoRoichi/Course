package by.home.Cource.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "project")

public class ProjectConfig {
    private Security security;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Security {

        private String secretKey;

        private Integer tokenLifetimeHours;
    }
}
