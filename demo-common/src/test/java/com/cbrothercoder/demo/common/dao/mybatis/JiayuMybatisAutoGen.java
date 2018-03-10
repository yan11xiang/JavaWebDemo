package com.cbrothercoder.demo.common.dao.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author trydofor
 * @since 2017-01-05.
 */
public class JiayuMybatisAutoGen {

    public void autoGen() throws Exception {
        InputStream xml = this.getClass().getResourceAsStream("/mybatis/mybatis-autogen.xml");
        autoGen(xml, true);
    }

    public void autoGen(InputStream xml, boolean overwrite) throws Exception {
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(xml);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        for (String warn : warnings) {
            System.out.println(warn);
        }
    }

}
