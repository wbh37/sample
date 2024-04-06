package com.hixtrip.sample.infra.db.util;


public abstract class BaseException extends RuntimeException {
    public BaseException(String errorInfo) {
        super(errorInfo);
    }

    public abstract int getErrorCode();

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
