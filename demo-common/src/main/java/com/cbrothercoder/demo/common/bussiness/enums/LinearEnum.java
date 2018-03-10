package com.cbrothercoder.demo.common.bussiness.enums;

public enum LinearEnum implements CodeEnum {

    厘米(1, "CM"),
    英寸(2, "IN");

    private final int    code;
    private final String isoCode;

    LinearEnum(int code, String isoCode) {
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
