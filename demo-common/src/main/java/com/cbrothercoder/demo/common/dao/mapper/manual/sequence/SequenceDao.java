package com.cbrothercoder.demo.common.dao.mapper.manual.sequence;

/**
 * @author trydofor
 * @since 2016-12-22.
 */
public interface SequenceDao {

    Long selectForUpdate(String tableName);

    int updateIncrease(String tableName);
}
