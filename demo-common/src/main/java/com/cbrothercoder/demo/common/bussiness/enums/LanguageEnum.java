package com.cbrothercoder.demo.common.bussiness.enums;

/**
 * Created by trydofor on 11/26/15.
 *
 * @see "ISO-639-1"
 * @see java.util.Locale#getISOLanguages()
 */
public enum LanguageEnum implements CodeEnum {

    简体中文(0, "ZH_CN"),
    美国英语(1, "EN_US");

    private int    code;
    private String isoCode;

    LanguageEnum(int code, String isoCode) {
        this.code = code;
        this.isoCode = isoCode;
    }

    @Override
    public int getCode() {
        return code;
    }

    public String getIsoCode() {
        return isoCode;
    }
}
