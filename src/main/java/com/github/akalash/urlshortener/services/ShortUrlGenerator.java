package com.github.akalash.urlshortener.services;

import com.github.akalash.urlshortener.config.properties.UrlGeneratorProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static com.github.akalash.urlshortener.framework.SecureRandomStringUtils.random;

/**
 * Service for generation short url.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
@Service
public class ShortUrlGenerator {

    private final UrlGeneratorProperties urlGeneratorProperties;

    public ShortUrlGenerator(UrlGeneratorProperties urlGeneratorProperties) {
        this.urlGeneratorProperties = urlGeneratorProperties;
    }

    /**
     * Generate short url
     *
     * @param shortUrlValidator validator wchich check correctness generated url
     * @return short url
     */
    public Optional<String> generate(Predicate<String> shortUrlValidator) {
        return IntStream.range(0, urlGeneratorProperties.getMaxNumberGenerationAttempts())
                .mapToObj(i -> random(calculateUrlLength(i)))
                .filter(shortUrlValidator)
                .findAny();
    }

    private int calculateUrlLength(int i) {
        return urlGeneratorProperties.getMinUrlLength() + i / urlGeneratorProperties.getAttemptsPerLength();
    }
}
