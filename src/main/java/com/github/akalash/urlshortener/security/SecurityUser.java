package com.github.akalash.urlshortener.security;

import com.github.akalash.urlshortener.domain.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

/**
 * Wrapper of {@link User} for holding {@link Account}
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
public class SecurityUser extends User {
    private final Account account;

    protected SecurityUser(Account account) {
        super(account.getUsername(), account.getPassword(), singletonList(new SimpleGrantedAuthority(account.getRole().name())));
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
