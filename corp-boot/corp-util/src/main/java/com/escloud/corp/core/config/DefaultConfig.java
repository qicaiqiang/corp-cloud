package com.escloud.corp.core.config;

import com.escloud.corp.core.common.properties.Settings;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultConfig {

    @Bean
    @ConfigurationProperties(prefix = "e.conf")
    public Settings settings() {
        return new Settings();
    }

}
