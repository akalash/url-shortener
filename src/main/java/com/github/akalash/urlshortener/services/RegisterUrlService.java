package com.github.akalash.urlshortener.services;

import com.github.akalash.urlshortener.config.properties.UrlShortenerProperties;
import com.github.akalash.urlshortener.domain.Account;
import com.github.akalash.urlshortener.domain.RedirectType;
import com.github.akalash.urlshortener.domain.UrlBinding;
import com.github.akalash.urlshortener.repository.UrlBindingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.nonNull;

/**
 * Service for register new binding for url
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
@Service
public class RegisterUrlService {
    private static final Logger logger = LoggerFactory.getLogger(RegisterUrlService.class);

    private final UrlBindingRepository urlBindingRepository;
    private final ShortUrlGenerator shortUrlGenerator;
    private final UrlShortenerProperties urlShortenerProperties;

    public RegisterUrlService(UrlBindingRepository urlBindingRepository, ShortUrlGenerator shortUrlGenerator, UrlShortenerProperties urlShortenerProperties) {
        this.urlBindingRepository = urlBindingRepository;
        this.shortUrlGenerator = shortUrlGenerator;
        this.urlShortenerProperties = urlShortenerProperties;
    }

    /**
     * Register new url
     *
     * @param originUrl       source url for bind
     * @param redirectTypeInt type of redirect
     * @param account         authorized account
     * @return short url or null if register was fail
     */
    public String registerUrl(String originUrl, Integer redirectTypeInt, Account account) {
        RedirectType redirectType = convert(redirectTypeInt);
        Optional<String> generatedShortUrl = shortUrlGenerator.generate((shortUrl) -> {
            try {
                urlBindingRepository.save(new UrlBinding(originUrl, shortUrl, redirectType, account));
            } catch (DataIntegrityViolationException ex) {
                logger.info("can not save url : {}", ex.getLocalizedMessage());
                return false;
            }
            return true;
        });

        return urlShortenerProperties.getBaseUrl() + generatedShortUrl.orElse(null);
    }

    private RedirectType convert(Integer redirectType) {
        if (nonNull(redirectType) && redirectType == 301) {
            return RedirectType.MOVED_PERMANENTLY;
        }
        return RedirectType.FOUND;
    }
}
