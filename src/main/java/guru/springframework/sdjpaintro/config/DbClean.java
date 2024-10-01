package guru.springframework.sdjpaintro.config;

import org.springframework.boot.autoconfigure.flyway.*;
import org.springframework.context.annotation.*;

@Profile("clean")
@Configuration
public class DbClean {

    @Bean
    public FlywayMigrationStrategy clean() {
        return flyway -> {
            flyway.clean();
            flyway.migrate();
        };
    }
}
