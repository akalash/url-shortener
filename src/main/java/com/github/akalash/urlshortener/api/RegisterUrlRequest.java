package com.github.akalash.urlshortener.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Request for register url process.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
public class RegisterUrlRequest {

    @NotNull
    private final String url;

    @Min(301)
    @Max(302)
    private final Integer redirectType;

    @JsonCreator
    public RegisterUrlRequest(@JsonProperty("url") String url, @JsonProperty("redirectType") Integer redirectType) {
        this.url = url;
        this.redirectType = redirectType;
    }

    public String getUrl() {
        return url;
    }

    public Integer getRedirectType() {
        return redirectType;
    }

    @Override
    public String toString() {
        return "RegisterUrlRequest{" +
                "url='" + url + '\'' +
                ", redirectType=" + redirectType +
                '}';
    }
}
