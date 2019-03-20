package ru.gorbach.hw12.common.business.exception;

public abstract class ReservationUncheckedException extends RuntimeException {
    protected int code;

    public ReservationUncheckedException(String message, int code) {
        super(message);
        this.code = code;
    }

    public ReservationUncheckedException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }
}
