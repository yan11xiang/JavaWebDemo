package com.cbrothercoder.demo.common.dao.mybatis;


import java.io.InputStream;


/**
 * @author trydofor
 * @since 2017-01-05.
 */
public class TemplateMybatisAutoGen {

    public static void main(String[] args) throws Exception {
        InputStream xml = TemplateMybatisAutoGen.class.getResourceAsStream("/mybatis/mybatis-autogen.xml");
        new JiayuMybatisAutoGen().autoGen(xml, true);
    }
}
