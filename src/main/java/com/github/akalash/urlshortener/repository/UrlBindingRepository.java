package com.github.akalash.urlshortener.repository;

import com.github.akalash.urlshortener.domain.UrlBinding;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository of UrlBinding manage.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
public interface UrlBindingRepository extends CrudRepository<UrlBinding, Long> {

    UrlBinding findByShortUrl(String shortUrl);
}
