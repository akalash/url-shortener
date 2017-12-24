package com.github.akalash.urlshortener.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Request for create account process.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
public class CreateAccountRequest {

    @NotNull
    private final String accountId;

    @JsonCreator
    public CreateAccountRequest(@JsonProperty("accountId") String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    @Override
    public String toString() {
        return "CreateAccountRequest{" +
                "accountId='" + accountId + '\'' +
                '}';
    }
}
