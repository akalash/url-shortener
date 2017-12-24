package com.github.akalash.urlshortener.security;

import com.github.akalash.urlshortener.domain.Account;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Helper class for convenient working with security context.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
public class SecurityHelper {
    public static Account getCurrentAccount() {
        return ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
    }
}
