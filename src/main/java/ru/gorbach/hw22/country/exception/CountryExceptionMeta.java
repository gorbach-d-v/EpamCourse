package ru.gorbach.hw22.country.exception;

public enum CountryExceptionMeta {
    DELETE_COUNTRY_CONSTRAINT_ERROR(1, "Error while delete country!"),
    JDBC_UNKNOWN_MONTH_ERROR(10, "Unknown month '%s'");

    private int code;
    private String description;

    CountryExceptionMeta(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionAsFormatStr(Object... args) {
        return String.format(description, args);
    }
}
