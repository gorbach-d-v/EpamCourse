package ru.gorbach.hw22.common.business.exception.jdbc;

import ru.gorbach.hw22.common.business.exception.ReservationUncheckedException;

import static ru.gorbach.hw22.common.business.exception.CommonExceptionMeta.JDBC_KEY_GENERATION_ERROR;

public class KeyGenerationError extends ReservationUncheckedException {

    public KeyGenerationError(String generatedKey) {
        super(JDBC_KEY_GENERATION_ERROR.getDescriptionAsFormatStr(generatedKey), JDBC_KEY_GENERATION_ERROR.getCode());
    }

}
