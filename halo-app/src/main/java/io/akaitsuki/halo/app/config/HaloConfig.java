package io.akaitsuki.halo.app.config;

import io.akaitsuki.halo.core.Bootstrap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HaloConfig {

    @Bean
    public Bootstrap bootstrap() {
        Bootstrap bootstrap = new Bootstrap();
        return bootstrap;
    }

}
