package com.github.akalash.urlshortener.domain;

import java.util.Arrays;
import java.util.List;

/**
 * Possible roles in system
 * Required for manage permission in different operation.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
public enum Role {
    /**
     * Account which can register new urls.
     */
    REGISTRATOR;
}
