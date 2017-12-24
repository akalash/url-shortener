package com.github.akalash.urlshortener.repository;

import com.github.akalash.urlshortener.domain.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository of account manage.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByUsername(String username);
}
