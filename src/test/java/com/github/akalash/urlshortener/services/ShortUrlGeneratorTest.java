package com.github.akalash.urlshortener.services;

import com.github.akalash.urlshortener.config.properties.UrlGeneratorProperties;
import org.testng.annotations.Test;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for {@link ShortUrlGenerator}
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
public class ShortUrlGeneratorTest {
    @Test
    public void should_attemptExact10times_because_maxAttemptIs10() throws Exception {
        ShortUrlGenerator shortUrlGenerator = new ShortUrlGenerator(mockUrlGeneratorProperties(1, 10, 1));

        AtomicInteger attemptCounter = new AtomicInteger(0);
        Optional<String> result = shortUrlGenerator.generate(url -> {
            attemptCounter.incrementAndGet();
            return false;
        });

        assertThat(result.isPresent(), is(false));
        assertThat(attemptCounter.get(), is(10));
    }

    @Test
    public void should_urlLengthIs8_because_attemptsPerLenngthIs2() throws Exception {
        ShortUrlGenerator shortUrlGenerator = new ShortUrlGenerator(mockUrlGeneratorProperties(2, 10, 3));

        AtomicInteger attemptCounter = new AtomicInteger(0);
        Optional<String> result = shortUrlGenerator.generate(url -> attemptCounter.incrementAndGet() == 10);

        assertThat(result.isPresent(), is(true));
        assertThat(result.get().length(), is(3 + 4));
    }

    @Test
    public void should_urlLengthIs4_because_minLengthIs4() throws Exception {
        ShortUrlGenerator shortUrlGenerator = new ShortUrlGenerator(mockUrlGeneratorProperties(1, 1, 4));

        Optional<String> result = shortUrlGenerator.generate(url -> true);

        assertThat(result.isPresent(), is(true));
        assertThat(result.get().length(), is(4));
    }

    private UrlGeneratorProperties mockUrlGeneratorProperties(int atteptsPerLength, int maxNumber, int minUrlLength) {
        UrlGeneratorProperties urlGeneratorProperties = mock(UrlGeneratorProperties.class);
        when(urlGeneratorProperties.getAttemptsPerLength()).thenReturn(atteptsPerLength);
        when(urlGeneratorProperties.getMaxNumberGenerationAttempts()).thenReturn(maxNumber);
        when(urlGeneratorProperties.getMinUrlLength()).thenReturn(minUrlLength);
        return urlGeneratorProperties;
    }

}