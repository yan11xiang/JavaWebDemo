package com.cbrothercoder.demo.common.bussiness.enums;

public enum WeightEnum implements CodeEnum {

    千克(1, "KG"),
    磅(2, "LB");

    private final int    code;
    private final String isoCode;

    WeightEnum(int code, String isoCode) {
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
