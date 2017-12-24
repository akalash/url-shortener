package com.github.akalash.urlshortener.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response for create account process.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
public class CreateAccountResponse {

    @JsonProperty("success")
    private final Boolean success;
    @JsonProperty("description")
    private final String description;
    @JsonProperty("password")
    private final String password;

    public CreateAccountResponse(Boolean success, String description, String password) {
        this.success = success;
        this.description = description;
        this.password = password;
    }

    public static CreateAccountResponse success(String password) {
        return new CreateAccountResponse(true, "account is opened", password);
    }

    public static CreateAccountResponse error(String message) {
        return new CreateAccountResponse(false, message, null);
    }

    @Override
    public String toString() {
        return "CreateAccountResponse{" +
                "success=" + success +
                ", description='" + description + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
