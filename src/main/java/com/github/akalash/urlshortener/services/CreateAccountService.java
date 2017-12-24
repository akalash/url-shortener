package com.github.akalash.urlshortener.services;

import com.github.akalash.urlshortener.domain.Account;
import com.github.akalash.urlshortener.domain.Role;
import com.github.akalash.urlshortener.framework.Result;
import com.github.akalash.urlshortener.framework.SecureRandomStringUtils;
import com.github.akalash.urlshortener.repository.AccountRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Service for creation account.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
@Service
public class CreateAccountService {
    private static final Logger logger = LoggerFactory.getLogger(CreateAccountService.class);
    private static final int DEFAULT_PASSWORD_LENGTH = 8;

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateAccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Create account by accountId
     *
     * @param accountId account id
     * @return result with password if account was created and result with error otherwise
     */
    public Result<String, Error> createAccount(String accountId) {
        String password = SecureRandomStringUtils.random(DEFAULT_PASSWORD_LENGTH);

        try {
            accountRepository.save(new Account(accountId, passwordEncoder.encode(password), Role.REGISTRATOR));
        } catch (DataIntegrityViolationException ex) {
            logger.info("can not save account : {}", ex.getLocalizedMessage());
            return Result.error(Error.ACCOUNT_ALREADY_EXISTS);
        }
        return Result.ok(password);
    }

    /**
     * Possible errors for this service
     */
    public enum Error {
        ACCOUNT_ALREADY_EXISTS("account already exist");

        private String message;

        Error(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
