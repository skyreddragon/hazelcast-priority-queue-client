package com.potapov.hazelcast.client.bpp;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ProxyFactoryConfig;
import com.potapov.hazelcast.client.config.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by днс on 16.01.2017.
 */
public class ProxyFactoryConfigAppenderBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
        if (bean instanceof ClientConfig) {
            ProxyFactoryConfig proxyFactoryConfig = new ProxyFactoryConfig();
            proxyFactoryConfig.setClassName(ProxyFactory.class.getName());
            proxyFactoryConfig.setService("priorityQueueService");
            ((ClientConfig) bean).addProxyFactoryConfig(proxyFactoryConfig);
        }
        return bean;
    }
}
