package com.cbrothercoder.demo.common.service.sequence;

/**
 * @author trydofor
 * @since 2016-12-22.
 */
public enum SequenceEnum {

    SCHEMA("tx_schema_version"),

    ;

    private final String table;

    SequenceEnum(String table) {
        this.table = table;
    }

    public String getTable() {
        return table;
    }
}
