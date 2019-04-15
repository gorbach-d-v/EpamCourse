package ru.gorbach.hw22.common.business.exception.jdbc;

import ru.gorbach.hw22.common.business.exception.ReservationUncheckedException;

import static ru.gorbach.hw22.common.business.exception.CommonExceptionMeta.JDBC_RESULT_SET_MAPPING_ERROR;

public class ResultSetMappingException extends ReservationUncheckedException {
    public ResultSetMappingException(String entityClassName, Exception e) {
        super(JDBC_RESULT_SET_MAPPING_ERROR.getDescriptionAsFormatStr(entityClassName), e, JDBC_RESULT_SET_MAPPING_ERROR.getCode());
    }
}
