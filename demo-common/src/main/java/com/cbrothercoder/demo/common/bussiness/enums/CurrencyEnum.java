package com.cbrothercoder.demo.common.bussiness.enums;


/**
 * Created by trydofor on 11/26/15.
 *
 * @see "ISO-4217"
 * @see java.util.Currency
 */
public enum CurrencyEnum implements CodeEnum {

    美元(1, "$", "USD"),
    人民币(2, "¥", "CNY"),
    加元(3, "$", "CAD");

    private final int    code;
    private final String symbol;
    private final String isoCode;

    CurrencyEnum(int code, String symbol, String isoCode) {
        this.code = code;
        this.symbol = symbol;
        this.isoCode = isoCode;
    }


    @Override
    public int getCode() {
        return code;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getIsoCode() {
        return isoCode;
    }
}
