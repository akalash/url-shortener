package com.github.akalash.urlshortener.framework;

/**
 * Helper class for represent result with possible errors.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 23.12.2017
 */
public class Result<BodyT, ErrorT> {
    private final BodyT body;

    private final ErrorT errorT;

    private final boolean isSuccess;

    private Result(BodyT body, ErrorT errorT, boolean isSuccess) {
        this.body = body;
        this.errorT = errorT;
        this.isSuccess = isSuccess;
    }

    public static <BodyT, ErrorT> Result<BodyT, ErrorT> ok(BodyT body) {
        return new Result<>(body, null, true);
    }

    public static <BodyT, ErrorT> Result<BodyT, ErrorT> error(ErrorT error) {
        return new Result<>(null, error, false);
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public BodyT getBody() {
        return body;
    }

    public ErrorT getError() {
        return errorT;
    }
}
