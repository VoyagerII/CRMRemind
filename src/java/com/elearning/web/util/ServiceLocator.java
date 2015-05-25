package com.elearning.web.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ServiceLocator implements ApplicationContextAware {

	protected static ApplicationContext context;

	public ServiceLocator() {

	}

	public static ApplicationContext getContext() {

		return context;
	}

	public static Object getBean(String beanname) {

		if (beanname == null || context == null) {
			return null;
		}
		return context.getBean(beanname);
	}

	public void setApplicationContext(ApplicationContext tcontext) throws BeansException {

		context = tcontext;
	}

}
