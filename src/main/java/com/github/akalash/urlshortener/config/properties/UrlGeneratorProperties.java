package com.github.akalash.urlshortener.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Properties for short url generator.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
@PropertySource("classpath:config/url-generator.properties")
@ConfigurationProperties(prefix = "urlGenerator")
@Configuration
public class UrlGeneratorProperties {

    private Integer maxNumberGenerationAttempts;
    private Integer minUrlLength;
    private Integer attemptsPerLength;

    public Integer getMaxNumberGenerationAttempts() {
        return maxNumberGenerationAttempts;
    }

    public void setMaxNumberGenerationAttempts(Integer maxNumberGenerationAttempts) {
        this.maxNumberGenerationAttempts = maxNumberGenerationAttempts;
    }

    public Integer getMinUrlLength() {
        return minUrlLength;
    }

    public void setMinUrlLength(Integer minUrlLength) {
        this.minUrlLength = minUrlLength;
    }

    public Integer getAttemptsPerLength() {
        return attemptsPerLength;
    }

    public void setAttemptsPerLength(Integer attemptsPerLength) {
        this.attemptsPerLength = attemptsPerLength;
    }
}
