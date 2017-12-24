package com.github.akalash.urlshortener.services;

import com.github.akalash.urlshortener.domain.RedirectType;
import com.github.akalash.urlshortener.domain.Statistic;
import com.github.akalash.urlshortener.domain.UrlBinding;
import com.github.akalash.urlshortener.repository.StatisticRepository;
import com.github.akalash.urlshortener.repository.UrlBindingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service for getRedirectInfo users by given url
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 23.12.2017
 */
@Service
public class RedirectService {
    private static final Logger logger = LoggerFactory.getLogger(RedirectService.class);
    private final UrlBindingRepository urlBindingRepository;
    private final StatisticRepository statisticRepository;

    public RedirectService(UrlBindingRepository urlBindingRepository, StatisticRepository statisticRepository) {
        this.urlBindingRepository = urlBindingRepository;
        this.statisticRepository = statisticRepository;
    }

    /**
     * Return redirect info by given short url.
     *
     * @param shortUrl url for redirect
     * @return redirect info if it was found and null otherwise
     */
    public RedirectResult getRedirectInfo(String shortUrl) {
        UrlBinding urlBinding = urlBindingRepository.findByShortUrl(shortUrl);
        if (urlBinding == null) {
            logger.info("unknown short url : shortUrl={}", shortUrl);
            return null;
        }
        statisticRepository.upsert(urlBinding.getOriginalUrl(), urlBinding.getAccount().getId());
        return new RedirectResult(urlBinding.getOriginalUrl(), urlBinding.getRedirectType());
    }

    public static class RedirectResult {
        private String url;
        private RedirectType redirectType;

        private RedirectResult(String url, RedirectType redirectType) {
            this.url = url;
            this.redirectType = redirectType;
        }

        public String getUrl() {
            return url;
        }

        public RedirectType getRedirectType() {
            return redirectType;
        }
    }

}
