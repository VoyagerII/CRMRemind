package com.elearning.web.applicationContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * TODO 以SpringContextHolder.getBean('xxxx')的静态方法得到spring bean对象
 * 
 * @author xinglt
 * @date 2014年7月30日 下午7:08:41
 *
 */
public class ApplicationContextHolder implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {

		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		ApplicationContextHolder.applicationContext = applicationContext;
	}

	/**
	 * 根据BeanName获取ApplicationContext静态变量中的Bean
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {

		return (T) ApplicationContextHolder.applicationContext.getBean(beanName);
	}

	/**
	 * 清除ApplicationContext静态变量
	 * 
	 */
	public static void cleanApplicationContext() {

		ApplicationContextHolder.applicationContext = null;
	}
}
