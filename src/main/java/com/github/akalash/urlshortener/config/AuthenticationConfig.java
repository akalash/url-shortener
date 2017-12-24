package com.github.akalash.urlshortener.config;

import com.github.akalash.urlshortener.config.properties.UrlGeneratorProperties;
import com.github.akalash.urlshortener.config.properties.UrlShortenerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Custom authentication settings.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
@Configuration
public class AuthenticationConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }
}
