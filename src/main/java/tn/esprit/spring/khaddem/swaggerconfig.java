package tn.esprit.spring.khaddem;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class swaggerconfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("spring")
                .packagesToScan("tn.esprit.spring.khaddem")
                .pathsToMatch("/**")
                .build();
    }
}
