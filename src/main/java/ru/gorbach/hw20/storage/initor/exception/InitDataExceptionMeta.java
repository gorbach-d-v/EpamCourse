package ru.gorbach.hw20.storage.initor.exception;

public enum InitDataExceptionMeta {

    PARSE_COUNTRY_DISCRIMINATOR_ERROR(1, "Unknown city discriminator '%s'."),
    PARSE_XML_EXCEPTION(2,"Error while parse file with countries data.");

    private int code;
    private String description;

    InitDataExceptionMeta(int code, String description) {
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
