package ru.gorbach.hw14.common.business.exception;

public abstract class ReservationCheckedException extends Exception {
    protected int code;

    public ReservationCheckedException(String message, int code) {
        super(message);
        this.code = code;
    }

    public ReservationCheckedException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

}
