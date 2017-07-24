/**
 * @(#)ApplicationContextUtils.java Copyright 2012 jointown, Inc. All rights reserved.
 */
package com.jzt.cms.util;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * @author yushi
 * @date 2017-07-24 description
 */
@Lazy(false)
@Component
public class ApplicationContextUtils implements ApplicationContextAware, Ordered, BeanPostProcessor {

	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextUtils.applicationContext = applicationContext;
	}

	public static void registBean(String beanName, BeanDefinition bean) {
		DefaultListableBeanFactory acf = (DefaultListableBeanFactory) applicationContext
				.getAutowireCapableBeanFactory();
		acf.registerBeanDefinition(beanName, bean);
	}

	public static Object getService(String beanName) {

		return applicationContext.getBean(beanName);
	}

	public static <T> T getService(Class<T> type) {
		return applicationContext.getBean(type);
	}

	public static <T> Map<String, T> getServiceBeans(Class<T> type) {
		return applicationContext.getBeansOfType(type);
	}

	public static String[] getServiceNameArray(Class<?> type) {
		return applicationContext.getBeanNamesForType(type);
	}

	public static void reload(Resource resource) {

	}

	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

}
