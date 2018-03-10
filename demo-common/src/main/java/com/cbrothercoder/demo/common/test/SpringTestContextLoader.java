package com.cbrothercoder.demo.common.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextLoader;

import java.util.TimeZone;

/**
 * @author trydofor
 * @since 2017-03-02.
 */
public class SpringTestContextLoader implements ContextLoader {

    @Override
    public String[] processLocations(Class<?> aClass, String... strings) {
        return new String[]{"classpath*:/spring/*.xml"};
    }

    @Override
    public ApplicationContext loadContext(String... strings) throws Exception {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext();
        ctx.setConfigLocation("classpath*:/spring/*.xml");
        ctx.refresh();
        return ctx;
    }
}
