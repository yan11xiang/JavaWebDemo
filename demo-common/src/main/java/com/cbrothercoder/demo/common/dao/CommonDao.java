package com.cbrothercoder.demo.common.dao;

/**
 * @author trydofor
 * @since 2016-12-22.
 */
public interface CommonDao<T> {

    int deleteLogicById(Long id);

    int insert(T record);

    int insertSelective(T record);

    T selectById(Long id);

    int updateByIdSelective(T record);

    int updateById(T record);
}
