/**
 * Created with IntelliJ IDEA.
 * User: 沙加
 * Date: 16/12/30
 * Time: 下午5:14
 */

package com.cbrothercoder.demo.common.dao.mapper;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;

public class MapperScannerConfigurer extends org.mybatis.spring.mapper.MapperScannerConfigurer {

    private boolean init;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
        if (init) { return; }

        init = true;
        super.postProcessBeanDefinitionRegistry(registry);
    }
}
