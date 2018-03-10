package com.cbrothercoder.demo.common.bussiness.enums;

/**
 * Created by trydofor on 11/26/15.
 */
public enum BoolEnum implements CodeEnum {

    FALSE(0),
    TRUE(1);

    public static final int N = 0;
    public static final int Y = 1;

    public static final boolean T = true;
    public static final boolean F = false;

    private final boolean   bool;
    private final int       code;

    //
    BoolEnum(int code) {
        this.code = code;
        this.bool = code == Y;
    }

    public static BoolEnum of(boolean bool) {
        return bool ? TRUE : FALSE;
    }

    public boolean bool() {
        return bool;
    }

    @Override
    public int getCode() {
        return code;
    }
}
