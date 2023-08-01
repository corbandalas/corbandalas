package com.corbandalas.launcher.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class CustomBeanDefinitionPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        Arrays.stream(beanFactory.getBeanDefinitionNames()).forEach(s -> log.info(s));
    }
}
