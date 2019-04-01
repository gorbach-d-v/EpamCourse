package ru.gorbach.hw16.storage.initor.exception.checked;

import ru.gorbach.hw16.common.business.exception.ReservationCheckedException;

public class ParseXmlException extends ReservationCheckedException {

    public ParseXmlException(String message, Exception cause,int code) {
        super(message, cause, code);
    }
}
