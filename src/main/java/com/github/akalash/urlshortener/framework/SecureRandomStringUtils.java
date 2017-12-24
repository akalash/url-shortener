package com.github.akalash.urlshortener.framework;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Wrapper of {@link RandomStringUtils} for replace Random to SecureRandom
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
public class SecureRandomStringUtils {
    private static final Random passwordRandom = new SecureRandom();

    public static String random(int count) {
        return RandomStringUtils.random(count, 0, 0, true, true, null, passwordRandom);
    }
}
