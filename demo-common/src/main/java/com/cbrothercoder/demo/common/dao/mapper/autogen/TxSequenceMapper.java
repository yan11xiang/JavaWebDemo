package com.cbrothercoder.demo.common.dao.mapper.autogen;

import com.cbrothercoder.demo.common.dao.po.autogen.TxSequence;

public interface TxSequenceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_sequence
     *
     * @mbg.generated
     */
    int insert(TxSequence record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_sequence
     *
     * @mbg.generated
     */
    int insertSelective(TxSequence record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_sequence
     *
     * @mbg.generated
     */
    TxSequence selectById(String tableName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_sequence
     *
     * @mbg.generated
     */
    int updateByIdSelective(TxSequence record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_sequence
     *
     * @mbg.generated
     */
    int updateById(TxSequence record);
}
