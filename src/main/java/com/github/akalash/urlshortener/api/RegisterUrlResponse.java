package com.github.akalash.urlshortener.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response for register url process.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 23.12.2017
 */
public class RegisterUrlResponse {

    @JsonProperty("shortUrl")
    private final String shortUrl;

    public RegisterUrlResponse(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public String toString() {
        return "RegisterUrlResponse{" +
                "shortUrl='" + shortUrl + '\'' +
                '}';
    }
}
