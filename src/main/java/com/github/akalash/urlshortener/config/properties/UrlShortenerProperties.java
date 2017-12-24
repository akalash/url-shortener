package com.github.akalash.urlshortener.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Common properties for url-shortener service
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
@PropertySource("classpath:config/url-shortener.properties")
@ConfigurationProperties(prefix = "urlShortener")
@Configuration
public class UrlShortenerProperties {
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
